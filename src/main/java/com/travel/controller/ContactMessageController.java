package com.travel.controller;

import com.travel.dto.ContactMessageRequest;
import com.travel.dto.ContactMessageResponse;
import com.travel.dto.BlogInquiryRequest;
import com.travel.service.ContactMessageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact-messages")
@CrossOrigin(origins = "*")
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    public ContactMessageController(ContactMessageService contactMessageService) {
        this.contactMessageService = contactMessageService;
    }

    @PostMapping
    public ContactMessageResponse createContactMessage(
            @RequestBody ContactMessageRequest request
    ) {
        return contactMessageService.createContactMessage(request);
    }
    
    @PostMapping("/blog-inquiry")
    public ContactMessageResponse createBlogInquiry(
            @RequestBody BlogInquiryRequest request
    ) {
        return contactMessageService.createBlogInquiry(request);
    }
}