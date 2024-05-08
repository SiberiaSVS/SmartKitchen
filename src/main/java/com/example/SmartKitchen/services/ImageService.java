package com.example.SmartKitchen.services;

import com.example.SmartKitchen.dto.ImageDTO;
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

    public ImageDTO uploadImage(MultipartFile file) throws IOException {
        String filename = UUID.randomUUID() + file.getOriginalFilename();
        file.transferTo(new File(imageFolder + filename));

        return ImageDTO.builder().imageName(filename).build();
    }

    public byte[] downloadImage(String fileName) throws IOException {
        return Files.readAllBytes(new File(imageFolder + fileName).toPath());
    }

    public boolean deleteImage(String fileName) {
        return new File(imageFolder + fileName).delete();
    }
}
