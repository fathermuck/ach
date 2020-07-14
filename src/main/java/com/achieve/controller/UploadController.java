package com.achieve.controller;

import com.achieve.service.IFileHandleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("file")
public class UploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private IFileHandleService fileHandleService;

    @RequestMapping("/upload")
    public String upload() {
        return "upload";
    }

    @RequestMapping("/uploadFile")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        System.out.println(file.getName());
        return fileHandleService.FileUpload(file);
    }
}
