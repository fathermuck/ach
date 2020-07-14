package com.achieve.service.impl;

import com.achieve.controller.UploadController;
import com.achieve.service.IFileHandleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileHandleServiceImpl implements IFileHandleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @Override
    public String FileUpload(MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        String filePath = "C:/Users/97929/Desktop/file/";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            LOGGER.info("上传成功");
            return "success";
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return "false";
    }

}
