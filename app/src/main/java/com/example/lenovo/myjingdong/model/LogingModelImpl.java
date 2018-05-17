package com.example.lenovo.myjingdong.model;


import android.content.Context;
import android.os.Looper;
import android.util.Log;

import com.example.lenovo.myjingdong.bean.LoginBean;
import com.example.lenovo.myjingdong.http.OkHttpUtils;
import com.example.lenovo.myjingdong.utils.Constant;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class LogingModelImpl{
   private ILoginModel iLoginModel;
    private OkHttpUtils okHttpUtils;
    private Context context;

    public void setiLoginModel(ILoginModel iLoginModel) {
        this.iLoginModel = iLoginModel;
        this.context=context;
    }

    public void Login(String mobile,String pwd){
        okHttpUtils = OkHttpUtils.getInstance(context);
        okHttpUtils.setiCallback(new OkHttpUtils.ICallback() {
            @Override
            public void getData(String result) {
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(result, LoginBean.class);
                Log.e("loginBean",loginBean.getData().getMobile());
                iLoginModel.success(loginBean);

            }
        });
        HashMap<String,String> params = new HashMap<>();
        params.put("mobile",mobile);
        params.put("password",pwd);
        okHttpUtils.post(Constant.LOGIN,params);

    }
    public interface ILoginModel {
        void success(LoginBean loginBean);
        void failure(Call call, IOException e);
    }

}
