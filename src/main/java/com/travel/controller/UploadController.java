package com.travel.controller;

import com.travel.dto.ImageUploadResponse;
import com.travel.service.CloudinaryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = "*")
public class UploadController {

    private final CloudinaryService cloudinaryService;

    public UploadController(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping("/image")
    public ImageUploadResponse uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam(defaultValue = "travel-website") String folder
    ) {
        return cloudinaryService.uploadImage(file, folder);
    }
}