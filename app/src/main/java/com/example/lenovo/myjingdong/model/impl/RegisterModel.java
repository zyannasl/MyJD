package com.example.lenovo.myjingdong.model.impl;

import com.example.lenovo.myjingdong.bean.RegBean;
import com.example.lenovo.myjingdong.utils.Constant;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterModel {
    private IRegisterModel iRegisterModel;
    public void setiRegisterModel(IRegisterModel iRegisterModel){
        this.iRegisterModel=iRegisterModel;
    }
    /**
     * 注册逻辑
     * */
    public void register(String mobile,String pwd) {
        //okhttpclient对象，使用构建者模式
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        //创建请求对象
        Request request = new Request.Builder().url(Constant.REG + "?mobile=" + mobile + "&password=" + pwd).build();


        //http协议，请求和响应

        //创建请求回调对象
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iRegisterModel.failure(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                RegBean regBean = gson.fromJson(result, RegBean.class);
                iRegisterModel.success(regBean);

            }
        });
    }






    public interface IRegisterModel {
        void success(RegBean regBean);
        void failure(Call call, IOException e);
}

}

