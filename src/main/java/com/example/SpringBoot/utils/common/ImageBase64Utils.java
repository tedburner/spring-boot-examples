package com.example.SpringBoot.utils.common;

import sun.misc.BASE64Decoder;

/**
 * @author lingjun.jlj
 * @data 2018/5/7
 * @Description: 图片base64工具类
 */
public class ImageBase64Utils {

    /**
     * @param imgStr
     * @return
     * @author lingjun.jlj
     * @Description: base64转图片
     * @date 2018/5/7 上午10:37
     */
    public static byte[] GenerateImage(String imgStr) {
        //对字节数组字符串进行Base64解码并生成图片
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = null;
        try {
            //Base64解码
            b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
}