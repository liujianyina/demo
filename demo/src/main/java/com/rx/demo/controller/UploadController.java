package com.rx.demo.controller;

import com.rx.demo.constant.CONSTANT;
import com.rx.demo.utils.AjaxResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController extends BaseController {

    @Value("${upload-path}")
    private String uploadPath;

    @PostMapping("/upload_json")
    public AjaxResult upload(MultipartFile file){
        String filename = CONSTANT.TABLE_PREFIX + System.nanoTime() + "_" + file.getOriginalFilename();
        File des = new File(uploadPath + filename);
        try {
            file.transferTo(des);
            return AjaxResult.success(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return AjaxResult.error("上传失败");
    }

}
