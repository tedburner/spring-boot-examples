package com.springboot.sample.service.image.impl;

import com.springboot.sample.common.constants.Constants;
import com.springboot.sample.service.image.ImageUploadService;
import com.springboot.sample.service.image.Qiniu.Qiniu;
import com.kit.common.util.common.MD5Utils;
import com.qiniu.http.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lucifer
 * @data 2018/5/7
 * @Description:
 */
@Service
public class ImageUploadServiceImpl implements ImageUploadService {

    private static Logger log = LoggerFactory.getLogger(ImageUploadServiceImpl.class);

    //@Value("${qiniu.public.bucket}")
    private String Bucket;
    //@Value("${qiniu.public.domain}")
    private String QiniuDomain;

    @Override
    public Map<String, Object> uploadBase64Img(String base64Code, String fileName) throws Exception {
        Map<String, Object> result = new HashMap<>();
        byte[] data = new BASE64Decoder().decodeBuffer(base64Code);
        if (data.length > Constants.IMAGE_UPLOAD_SIZE_LIMIT / 8) {
            log.error("uploadBase64Img error, length > sizeLimit");
            throw new RuntimeException("");
        }
        String postfix = "";
        if (!fileName.isEmpty() && fileName.lastIndexOf(".") != -1) {
            postfix = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        if (postfix.isEmpty()) {
            postfix = "png";
        }
        String imgKey = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + MD5Utils.selectRandom(6) + "." + postfix;

        Response uploadResponse = Qiniu.upload(Bucket, imgKey, data);
        if (uploadResponse == null || !uploadResponse.isOK()) {
            log.error("uploadBase64Img error, uploadResponse == null || !uploadResponse.isOK())");
            throw new RuntimeException("");
        }
        //生成七牛url
        String imgUrl = Qiniu.downloadHttps(QiniuDomain, imgKey);

        result.put("imgKey", imgKey);
        result.put("imgUrl", imgUrl);
        return result;

    }

    @Override
    public Map<String, Object> uploadImg(MultipartFile img) throws Exception {
        Map<String, Object> result = new HashMap<>();
        String postfix = "";
        if (!img.getName().isEmpty() && img.getName().lastIndexOf(".") != -1) {
            postfix = img.getName().substring(img.getName().lastIndexOf(".") + 1);
        }
        if (postfix.isEmpty()) {
            postfix = "png";
        }
        String imgKey = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + MD5Utils.selectRandom(6) + "." + postfix;
        System.out.println("imgKey" + imgKey);
        File tmpFile = File.createTempFile("image", imgKey);
        img.transferTo(tmpFile);
        Qiniu.upload(Bucket, imgKey, tmpFile);
        //生成七牛url
        String imgUrl = Qiniu.downloadHttps(QiniuDomain, imgKey);

        result.put("imgKey", imgKey);
        result.put("imgUrl", imgUrl);
        return result;
    }
}
