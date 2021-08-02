package com.nepathya.archive.system.service.fileupload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileMetaDataService {
	
	@Autowired
	private FileMetaDataRepository fileMetaDataRepository;
	
	public FileMetaData addMetaData(FileMetaData fileMetaData) {
		fileMetaDataRepository.save(fileMetaData);
		return fileMetaData;
	}
}
