package com.example.lenovo.myjingdong.model;

import android.content.Context;

import com.example.lenovo.myjingdong.bean.ClassifyYouBean;
import com.example.lenovo.myjingdong.http.OkHttpUtils;
import com.example.lenovo.myjingdong.utils.Constant;
import com.google.gson.Gson;

import java.util.HashMap;

public class ClassifyModel {
    private IClassifyModel iClassifyModel;
    private OkHttpUtils okHttpUtils;
    private Context context;

    public void setiClassifyModel(IClassifyModel iClassifyModel){
        this.iClassifyModel=iClassifyModel;
        this.context=context;
    }
    public void ClassifyData(String cid){
        okHttpUtils=OkHttpUtils.getInstance(context);
        okHttpUtils.setiCallback(new OkHttpUtils.ICallback() {
            @Override
            public void getData(String result) {
                Gson gson = new Gson();
                ClassifyYouBean classifyYouBean = gson.fromJson(result, ClassifyYouBean.class);
                iClassifyModel.success(classifyYouBean);
            }
        });
        HashMap<String,String> params = new HashMap<>();
        params.put("cid",cid);
        okHttpUtils.post(Constant.CLASSIFY,params);
    }

    public interface IClassifyModel{
        void success(ClassifyYouBean classifyYouBean);
    }
}
