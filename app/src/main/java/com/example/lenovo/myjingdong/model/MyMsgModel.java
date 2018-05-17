package com.example.lenovo.myjingdong.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.lenovo.myjingdong.bean.UserMsgBean;
import com.example.lenovo.myjingdong.http.OkHttpUtils;
import com.example.lenovo.myjingdong.utils.Constant;
import com.example.lenovo.myjingdong.utils.ContextApplication;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;

public class MyMsgModel {
    private IMyMsgModel iMyMsgModel;
    private Context context;
    private OkHttpUtils okHttpUtils;

    public void setiMyMsgModel(IMyMsgModel iMyMsgModel){
        this.iMyMsgModel=iMyMsgModel;
        this.context=context;
    }

    /**
     * 获取信息
     */
    public void getUserData(){
        okHttpUtils = OkHttpUtils.getInstance(context);
        okHttpUtils.setiCallback(new OkHttpUtils.ICallback() {
            @Override
            public void getData(String result) {
                Gson gson = new Gson();
                UserMsgBean userMsgBean = gson.fromJson(result, UserMsgBean.class);
                Log.e("1",userMsgBean.getData().getMobile());
                iMyMsgModel.success(userMsgBean);
            }
        });
        //获取用户uid
        //为了保证在项目的任何地方可以拿到context 所有, 创建了ContextApplication类 并在清单文件中进行注册
        SharedPreferences user = ContextApplication.getAppContext().getSharedPreferences("User", Context.MODE_PRIVATE);
        String suid = user.getString("uid", null);
        Log.e("用户id",suid+"");
        HashMap<String,String> params = new HashMap<>();
        params.put("uid",suid);
        okHttpUtils.post(Constant.USER_MSG,params);
    }



    public interface IMyMsgModel {
        void success(UserMsgBean userMsgBean);
        void failure(Call call, IOException e);
    }
}
