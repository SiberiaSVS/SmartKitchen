package com.example.SmartKitchen.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.example.SmartKitchen.dto.ImageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Service
@Slf4j
public class ImageService {
    @Value("${cloud.aws.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public ImageDTO uploadImage(MultipartFile file) throws IOException {
        String filename = UUID.randomUUID() + file.getOriginalFilename();

        File fileObj = convertMultipartFileToFile(file);

        s3Client.putObject(new PutObjectRequest(bucketName, filename, fileObj));
        fileObj.delete();
        return ImageDTO.builder().imageName(filename).build();
    }

    public byte[] downloadImage(String fileName)  {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteImage(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return true;
    }

    private File convertMultipartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("error converting multipart to file", e);
        }
        return convertedFile;
    }
}
