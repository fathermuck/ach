package com.achieve.service;

import org.springframework.web.multipart.MultipartFile;


public interface IFileHandleService {

    String FileUpload(MultipartFile file);
}
