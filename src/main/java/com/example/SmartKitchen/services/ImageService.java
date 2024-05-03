package com.example.SmartKitchen.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class ImageService {

    @Value("${image-path}")
    private String imageFolder;

    public String uploadImage(MultipartFile file) throws IOException {
        String filename = UUID.randomUUID() + file.getOriginalFilename();
        file.transferTo(new File(imageFolder + filename));

        return filename;
    }

    public byte[] downloadImage(String fileName) throws IOException {
        return Files.readAllBytes(new File(imageFolder + fileName).toPath());
    }
}
