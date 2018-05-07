package com.example.SpringBoot.service.image;

import com.qiniu.http.Response;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

/**
 * @author lingjun.jlj
 * @data 2018/5/7
 * @Description:
 */
public interface ImageUploadService {

    /**
     * base64上传图片
     */
    Map<String, Object> uploadBase64Img(String base64Code, String fileName) throws Exception;

    Map<String,Object> uploadImg(MultipartFile file) throws Exception;
}
