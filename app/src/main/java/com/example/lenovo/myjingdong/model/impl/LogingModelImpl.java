package com.example.lenovo.myjingdong.model.impl;


import android.os.Looper;
import android.util.Log;
import com.example.lenovo.myjingdong.http.HttpUtilsCallBack;
import com.example.lenovo.myjingdong.http.OkHttp;
import com.example.lenovo.myjingdong.bean.LoginBean;
import com.example.lenovo.myjingdong.utils.Constant;
import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class LogingModelImpl{
   private ILoginModel iLoginModel;

    public void setiLoginModel(ILoginModel iLoginModel) {
        this.iLoginModel = iLoginModel;
    }

    public void Login(String mobile,String pwd){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request=new Request.Builder().url(Constant.LOGIN+"?mobile+"+mobile+"&password="+pwd).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iLoginModel.failure(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Looper.prepare();

                String result = response.body().string();
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(result, LoginBean.class);
                iLoginModel.success(loginBean);

                Looper.loop();

            }
        });//异步请求
    }
    public interface ILoginModel {
        void success(LoginBean loginBean);
        void failure(Call call, IOException e);
    }

}
