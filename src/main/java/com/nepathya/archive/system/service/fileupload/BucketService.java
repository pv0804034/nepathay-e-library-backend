package com.nepathya.archive.system.service.fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;


@Service
public class BucketService {
	@Autowired
	private FileMetaDataService fileMetaDataService;

	private AmazonS3 s3client;

	@Value("${s3.endpointUrl}")
	private String endpointUrl;

	@Value("${s3.bucketName}")
	private String bucketName;

	@Value("${s3.accessKeyId}")
	private String accessKeyId;

	@Value("${s3.secretKey}")
	private String secretKey;

	@Value("${s3.region}")
	private String region;

	@PostConstruct
	private void initializeAmazon() {
		AWSCredentials credentials = new BasicAWSCredentials(this.accessKeyId, this.secretKey);
		this.s3client = AmazonS3ClientBuilder.standard().withRegion(region)
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
	}

	public String uploadFile(MultipartFile multipartFile) throws Exception {
		File file = convertMultiPartToFile(multipartFile);
		FileMetaData fileMetaData = saveDocumentMetaData(multipartFile);
		return commonUpload(fileMetaData, file);
	}

	private String commonUpload(FileMetaData fileMetaData, File file) {
		String fileName = fileName(fileMetaData);
		uploadFileTos3bucket(fileName, file);
		file.delete();
		return fileName;
	}

	private void uploadFileTos3bucket(String fileName, File file) {
		s3client.putObject(bucketName, fileName, file);
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	private FileMetaData saveDocumentMetaData(MultipartFile multipartFile) {
		FileMetaData fileMetaData = new FileMetaData(multipartFile);
		return fileMetaDataService.addMetaData(fileMetaData);
	}

	private String fileName(FileMetaData fileMetaData) {
		return fileMetaData.getName();
	}
}

