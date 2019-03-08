package com.mall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传接口
 * Created by Yunkang Wu
 */
public interface IFileService {

    String upload(MultipartFile file, String path);
}
