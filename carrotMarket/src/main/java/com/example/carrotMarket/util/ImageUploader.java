package com.example.carrotMarket.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.example.carrotMarket.service.ImageFileService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ImageUploader implements ImageFileService {

	private final BlobServiceClient blobServiceClient;

	private static final String PROTOCOL = "https://";
	private static final String DIR = "/";

	@Value("${azure.storage.container}")
	private String containerName;

	@Value("${azure.storage.address}")
	private String address;

	@Value("${azure.storage.account}")
	private String account;

	@Override
	public String uploadImage(MultipartFile multipartFile, String convertedFilename) {
		BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
		try {
			containerClient.getBlobClient(convertedFilename)
				.upload(multipartFile.getInputStream(), multipartFile.getSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return makeUploadImagePath(convertedFilename);
	}

	private String makeUploadImagePath(String convertedFilename) {
		return PROTOCOL + account + address + DIR + containerName + DIR + convertedFilename;
	}
}
