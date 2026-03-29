package com.springboot.sample.util.common;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: kiturone
 * @date: 2018-12-29 13:46
 * @description:
 */
@Slf4j
public class VacationUtils {


    /**
     * 判断今天是否是工作日
     *
     * @description: true 是工作日 false 节假日
     */
    public static Boolean isHoliday() {
        String dc = "http://tool.bitefu.net/jiari/?d=";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateFlag = getHoliday(dc + sdf.format(new Date()));
        if ("1".equals(dateFlag) || "2".equals(dateFlag)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 方法描述：方法描述：获取节假日 访问接口，根据返回值判断当前日期是否为工作日，
     * 返回结果：检查具体日期是否为节假日，工作日对应结果为 0, 休息日对应结果为 1, 节假日对应的结果为 2；
     * 2018年4月3日上午11:26:40
     */
    public static String getHoliday(String httpUrl) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {

        }
        return result;
    }
}
