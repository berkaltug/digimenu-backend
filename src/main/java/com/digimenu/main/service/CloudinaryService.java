package com.digimenu.main.service;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {
    String uploadFile(MultipartFile file);
    String updateFile(MultipartFile newFile,String oldPublicId);
    void deleteFile(String publicId);
}
