package com.example.carrotMarket.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageFileService {
	String uploadImage(MultipartFile multipartFile, String convertedFilename);
}
