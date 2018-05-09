package com.example.lenovo.myjingdong.http;



import com.example.lenovo.myjingdong.model.listeneer.LoginListener;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttp implements Callback {

    private static OkHttp INSTANCE;
    private final OkHttpClient okHttpClient;



    public OkHttp() {
        okHttpClient = new OkHttpClient.Builder().build();
    }
    public static OkHttp getInstance(){
        if(INSTANCE ==null){
            INSTANCE =new OkHttp();
        }
        return INSTANCE;
    }
    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");

    public void doGet(String url){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(this);
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {


    }
}
