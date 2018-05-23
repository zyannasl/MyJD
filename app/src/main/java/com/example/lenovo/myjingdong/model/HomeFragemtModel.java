package com.example.lenovo.myjingdong.model;

import android.content.Context;

import com.example.lenovo.myjingdong.bean.JiuGongGeBean;
import com.example.lenovo.myjingdong.http.OkHttpUtils;
import com.example.lenovo.myjingdong.utils.Constant;
import com.google.gson.Gson;

public class HomeFragemtModel {
    private IHomeFragnemtModel iHomeFragnemtModel;
    private Context context;
    private OkHttpUtils okHttpUtils;

    public void setiHomeFragnemtModel(IHomeFragnemtModel iHomeFragnemtModel){
        this.iHomeFragnemtModel=iHomeFragnemtModel;
        this.context=context;
    }
    public void getJiuGonggeData(){
        okHttpUtils = OkHttpUtils.getInstance(context);
        okHttpUtils.setiCallback(new OkHttpUtils.ICallback() {
            @Override
            public void getData(String result) {
                Gson gson = new Gson();
                JiuGongGeBean jiuGongGeBean = gson.fromJson(result, JiuGongGeBean.class);
                iHomeFragnemtModel.success(jiuGongGeBean);
            }
        });
        okHttpUtils.getData(Constant.JIUGONGGE);

    }





    public interface IHomeFragnemtModel{
        void success(JiuGongGeBean jiuGongGeBean);
    }
}
