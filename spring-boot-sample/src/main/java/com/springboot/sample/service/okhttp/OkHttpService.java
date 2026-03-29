package com.springboot.sample.service.okhttp;

import com.springboot.sample.util.okhttp.HttpException;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

/**
 * @author kiturone
 * @data 2018/5/10
 * @Description:
 */
public interface OkHttpService {

    /**
     * 创建httpclient
     */
    OkHttpClient CreateClient();

    byte[] GET(String baseUrl, Map<String, String> queryParams) throws HttpException, IOException;

    byte[] POST(String url, String jsonBody) throws HttpException, IOException;

    Response ReqExecute(Request request) throws IOException;

    Call ReqExecuteCall(Request request);
}
