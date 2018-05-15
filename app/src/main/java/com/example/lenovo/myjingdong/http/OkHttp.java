package com.example.lenovo.myjingdong.http;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.util.HashMap;

import java.util.Map;
import java.util.Set;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttp{

    private static OkHttp okHttp;
    private final int SUCCESS = 0;
    private final int ERROR = 1;
    private MyHandler myHandler = new MyHandler();
    private HttpUtilsCallBack httpUtilsCallBack;


    public static OkHttp getInstance(){
        if(okHttp ==null){
            okHttp =new OkHttp();
        }
        return okHttp;
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    //成功
                    String json = (String) msg.obj;
                    httpUtilsCallBack.onSuccess(json);
                    break;


                case ERROR:
                    //失败
                    String error = (String) msg.obj;
                    httpUtilsCallBack.onFail(error);
                    break;
            }
        }
    }
    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");

    public void doGet(String url){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();


        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);


        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = myHandler.obtainMessage();
                message.what = ERROR;
                message.obj = e.getMessage();
                myHandler.sendMessage(message);
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = myHandler.obtainMessage();
                message.what = SUCCESS;
                message.obj = response.body().string();
                myHandler.sendMessage(message);
            }
        });
    }


    public void sethttpUtilsCallBack(String url, Map<String, String> params) {
        this.httpUtilsCallBack = httpUtilsCallBack;
    }
    public void doPost(String path, HashMap<String,String> map) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();


        FormBody.Builder builder = new FormBody.Builder();
        Set<String> keySet = map.keySet();
        for (String key :
                keySet) {
            String value = map.get(key);
            builder.add(key, value);
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder().url(path).post(formBody).build();
        Call call = okHttpClient.newCall(request);


        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = myHandler.obtainMessage();
                message.what = ERROR;
                message.obj = e.getMessage();
                myHandler.sendMessage(message);
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = myHandler.obtainMessage();
                message.what = SUCCESS;
                message.obj = response.body().string();
                myHandler.sendMessage(message);
            }
        });


    }


}
