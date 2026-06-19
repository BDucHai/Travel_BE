package com.travel.service;

import com.travel.dto.BlogInquiryRequest;
import com.travel.dto.ContactMessageRequest;
import com.travel.dto.ContactMessageResponse;
import com.travel.dto.PaginationResponse;
import com.travel.entity.Blog;
import com.travel.entity.ContactMessage;
import com.travel.repository.BlogRepository;
import com.travel.repository.ContactMessageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;
    private final BlogRepository blogRepository;

    public ContactMessageService(
            ContactMessageRepository contactMessageRepository,
            BlogRepository blogRepository
    ) {
        this.contactMessageRepository = contactMessageRepository;
        this.blogRepository = blogRepository;
    }

    // Public API: khách gửi contact form thường
    public ContactMessageResponse createContactMessage(ContactMessageRequest request) {
        ContactMessage contactMessage = new ContactMessage();

        contactMessage.setFullName(request.getFullName());
        contactMessage.setEmail(request.getEmail());
        contactMessage.setPhoneNumber(request.getPhoneNumber());
        contactMessage.setSubject(request.getSubject());
        contactMessage.setMessage(request.getMessage());
        contactMessage.setStatus("NEW");

        ContactMessage savedMessage = contactMessageRepository.save(contactMessage);

        return mapToResponse(savedMessage);
    }

    // Public API: khách gửi form Ask Our Travel Specialist trong blog detail
    public ContactMessageResponse createBlogInquiry(BlogInquiryRequest request) {
        Blog blog = null;

        if (request.getBlogId() != null) {
            blog = blogRepository.findById(request.getBlogId())
                    .orElseThrow(() -> new RuntimeException("Blog not found"));
        }

        ContactMessage contactMessage = new ContactMessage();

        contactMessage.setFullName(request.getFullName());
        contactMessage.setEmail(request.getEmail());
        contactMessage.setPhoneNumber(request.getPhoneNumber());
        contactMessage.setSubject("Blog Inquiry");
        contactMessage.setMessage(request.getMessage());
        contactMessage.setBlog(blog);
        contactMessage.setStatus("NEW");

        ContactMessage saved = contactMessageRepository.save(contactMessage);

        return mapToResponse(saved);
    }

    // Admin API: lấy tất cả hoặc lọc theo status + pagination
    public PaginationResponse<ContactMessageResponse> getContactMessagesForAdmin(
            String status,
            Integer page,
            Integer limit
    ) {
        int pageNumber = page == null || page < 0 ? 0 : page;
        int pageSize = limit == null || limit <= 0 ? 10 : limit;

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<ContactMessage> messagePage;

        if (status != null && !status.isBlank()) {
            messagePage = contactMessageRepository.findByStatusOrderByCreatedAtDesc(
                    status.toUpperCase(),
                    pageable
            );
        } else {
            messagePage = contactMessageRepository.findAllByOrderByCreatedAtDesc(pageable);
        }

        List<ContactMessageResponse> data = messagePage.getContent()
                .stream()
                .map(this::mapToResponse)
                .toList();

        return new PaginationResponse<>(
                data,
                messagePage.getNumber(),
                messagePage.getSize(),
                messagePage.getTotalElements(),
                messagePage.getTotalPages(),
                messagePage.isFirst(),
                messagePage.isLast()
        );
    }

    // Method cũ giữ lại nếu chỗ khác đang gọi
    public List<ContactMessageResponse> getContactMessagesForAdmin(String status) {
        List<ContactMessage> messages;

        if (status != null && !status.isBlank()) {
            messages = contactMessageRepository.findByStatusOrderByCreatedAtDesc(status.toUpperCase());
        } else {
            messages = contactMessageRepository.findAllByOrderByCreatedAtDesc();
        }

        return messages.stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Admin API: xem detail
    public ContactMessageResponse getContactMessageById(Long id) {
        ContactMessage contactMessage = contactMessageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact message not found"));

        return mapToResponse(contactMessage);
    }

    // Admin API: update status
    public ContactMessageResponse updateStatus(Long id, String status) {
        ContactMessage contactMessage = contactMessageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact message not found"));

        contactMessage.setStatus(status.toUpperCase());

        ContactMessage updated = contactMessageRepository.save(contactMessage);

        return mapToResponse(updated);
    }

    // Admin API: xóa message
    public void deleteContactMessage(Long id) {
        ContactMessage contactMessage = contactMessageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact message not found"));

        contactMessageRepository.delete(contactMessage);
    }

    private ContactMessageResponse mapToResponse(ContactMessage contactMessage) {
        Blog blog = contactMessage.getBlog();

        return new ContactMessageResponse(
                contactMessage.getId(),
                contactMessage.getFullName(),
                contactMessage.getEmail(),
                contactMessage.getPhoneNumber(),
                contactMessage.getSubject(),
                contactMessage.getMessage(),
                contactMessage.getStatus(),
                contactMessage.getCreatedAt(),
                blog != null ? blog.getId() : null,
                blog != null ? blog.getTitleEn() : null
        );
    }
}