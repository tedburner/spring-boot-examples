package com.springboot.sample.service.okhttp.impl;

import com.springboot.sample.service.okhttp.OkHttpService;
import com.springboot.sample.util.okhttp.HttpException;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author lingjun.jlj
 * @data 2018/5/10
 * @Description:
 */

public class OkHttpServiceImpl implements OkHttpService {

    public final MediaType MEDIA_JSON = MediaType.parse("application/json; charset=utf-8");

    @Autowired
    private OkHttpClient okHttpClient;

    @Override
    public OkHttpClient CreateClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

    @Override
    public byte[] GET(String baseUrl, Map<String, String> queryParams) throws IOException {
        //拼装param
        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder();
        for (Map.Entry<String, String> item : queryParams.entrySet()) {
            urlBuilder.addQueryParameter(item.getKey(), item.getValue());
        }
        HttpUrl httpUrl = urlBuilder.build();
        //发送请求
        Request request = new Request.Builder()
                .url(httpUrl.toString()).get()
                .build();
        Response response = ReqExecute(request);
        if (!response.isSuccessful()) {
            throw new HttpException(response, "exception code:" + response.code());
        }
        return response.body().bytes();
    }

    @Override
    public byte[] POST(String url, String jsonBody) throws HttpException, IOException {
        RequestBody body = RequestBody.create(MEDIA_JSON, jsonBody);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = ReqExecute(request);
        if (!response.isSuccessful()) {
            throw new HttpException(response, "exception code:" + response.code());
        }
        return response.body().bytes();
    }

    /**
     * 请求方法
     *
     * @param request
     * @return
     * @throws IOException
     * @author lingjun.jlj 2018年5月10日
     */
    @Override
    public Response ReqExecute(Request request) throws IOException {
        return ReqExecuteCall(request).execute();
    }

    /**
     * 构造CALL方法
     *
     * @param request
     * @return
     * @author wangdong 2016年7月17日
     */
    @Override
    public Call ReqExecuteCall(Request request) {
        return okHttpClient.newCall(request);
    }
}
