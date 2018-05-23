package com.example.lenovo.myjingdong.model;


import android.content.Context;
import android.util.Log;

import com.example.lenovo.myjingdong.bean.SelectBean;
import com.example.lenovo.myjingdong.http.OkHttpUtils;
import com.example.lenovo.myjingdong.utils.Constant;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;

public class SelectModel {
    private ISelectModel iSelectModel;
    private OkHttpUtils okHttpUtils;
    private Context context;

    public void setiSelectModel(ISelectModel iSelectModel){
        this.iSelectModel=iSelectModel;
    }
    public void SelectData(String keywords){
        Log.e("modle",keywords+"");
        okHttpUtils=new OkHttpUtils(context);
        okHttpUtils.setiCallback(new OkHttpUtils.ICallback() {
            @Override
            public void getData(String result) {
                Gson gson = new Gson();
                SelectBean selectBean = gson.fromJson(result, SelectBean.class);
                iSelectModel.success(selectBean);
            }
        });
        HashMap<String,String> params = new HashMap<>();
        params.put("keywords",keywords);
        okHttpUtils.post(Constant.SELECT,params);
    }

    public interface ISelectModel{
            void success(SelectBean selectBean);
            void failure(Call call, IOException e);
        }





}
