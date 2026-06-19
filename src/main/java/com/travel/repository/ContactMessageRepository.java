package com.travel.repository;

import com.travel.entity.ContactMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {

    long countByStatus(String status);

    // Method cũ giữ lại nếu chỗ khác đang dùng
    List<ContactMessage> findByStatusOrderByCreatedAtDesc(String status);

    List<ContactMessage> findAllByOrderByCreatedAtDesc();

    // Method mới dùng cho pagination
    Page<ContactMessage> findByStatusOrderByCreatedAtDesc(
            String status,
            Pageable pageable
    );

    Page<ContactMessage> findAllByOrderByCreatedAtDesc(
            Pageable pageable
    );
}