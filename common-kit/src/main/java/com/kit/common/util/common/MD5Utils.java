package com.kit.common.util.common;

import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.util.Random;

/**
 * @author lingjun.jlj
 * @data 2018/5/7
 * @Description:
 */
public class MD5Utils {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MD5Utils.class);

    public final static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4',
                '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] btInput = s.getBytes();
            //获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            //使用指定的字节更新摘要
            mdInst.update(btInput);
            //获得密文
            byte[] md = mdInst.digest();
            //把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            log.error("MD5", e);
            return null;
        }
    }

    /**
     * 获取随机数
     *
     * @param numLength 随机数长度
     * @return
     */
    public static String selectRandom(int numLength) {
        String val = "";

        Random random = new Random();
        for (int i = 0; i < numLength; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

            if ("char".equalsIgnoreCase(charOrNum)) // 字符串
            {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) // 数字
            {
                val += String.valueOf(random.nextInt(10));
            }
        }

        return val;
    }

    public static String selectRandomNum(int numLength) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < numLength; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }

    /**
     * 用户验证密码加密
     *
     * @param password  未加密的密码
     * @param randomNum 随机数
     * @return
     */
    public static String EncryptionPassword(String password, String randomNum) {
        password = MD5(password + randomNum);
        return password;
    }

    /**
     * 用户密码加密
     */
    public static String EncryptionPassword(String password) {
        password = MD5(password);
        return password;
    }
}
