package com.example.carrotMarket.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

@Configuration
public class AzureBlobConfig {

	@Value("${azure.storage.connection-string}")
	private String azureStorageConnection;

	@Bean
	public BlobServiceClient blobServiceClient() {
		return new BlobServiceClientBuilder()
			.connectionString(azureStorageConnection)
			.buildClient();
	}
}
