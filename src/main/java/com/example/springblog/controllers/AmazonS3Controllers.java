
package com.example.springblog.controllers;

import com.example.springblog.services.AmazonS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/storage/")
public class AmazonS3Controllers {

    private final AmazonS3Service amazonS3Service;

    @Autowired
    public AmazonS3Controllers(AmazonS3Service amazonS3Service) {
        this.amazonS3Service = amazonS3Service;
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return amazonS3Service.uploadFile(file);
    }

    @PostMapping("/deleteFile")
    public String deleteFile(@RequestBody String fileURL) {
        return amazonS3Service.deleteFileFromBucket(fileURL);
    }
}
