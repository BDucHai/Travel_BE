package com.travel.controller;

import com.travel.dto.ContactMessageResponse;
import com.travel.dto.PaginationResponse;
import com.travel.service.ContactMessageService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/contact-messages")
@CrossOrigin(origins = "*")
public class AdminContactMessageController {

    private final ContactMessageService contactMessageService;

    public AdminContactMessageController(ContactMessageService contactMessageService) {
        this.contactMessageService = contactMessageService;
    }

    @GetMapping
    public PaginationResponse<ContactMessageResponse> getContactMessages(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer limit
    ) {
        return contactMessageService.getContactMessagesForAdmin(
                status,
                page,
                limit
        );
    }

    @GetMapping("/{id}")
    public ContactMessageResponse getContactMessageById(@PathVariable Long id) {
        return contactMessageService.getContactMessageById(id);
    }

    @PatchMapping("/{id}/status")
    public ContactMessageResponse updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> request
    ) {
        return contactMessageService.updateStatus(id, request.get("status"));
    }

    @DeleteMapping("/{id}")
    public String deleteContactMessage(@PathVariable Long id) {
        contactMessageService.deleteContactMessage(id);
        return "Delete contact message successfully";
    }
}