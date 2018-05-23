package com.example.lenovo.myjingdong.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.lenovo.myjingdong.http.OkHttpUtils;
import com.example.lenovo.myjingdong.utils.Constant;
import com.example.lenovo.myjingdong.ContextApplication;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;

public class UpdateNichengModel {
    private IUpdateNichengModle iUpdateNichengModle;
    private OkHttpUtils okHttpUtils;
    private Context context;

    public void setUpdateNichengModel(IUpdateNichengModle iUpdateNichengModle){
        this.iUpdateNichengModle=iUpdateNichengModle;
        this.context=context;
    }

    public void updateNickName(String nickname){
        Log.e("um",nickname+"");
        okHttpUtils = OkHttpUtils.getInstance(context);
        okHttpUtils.setiCallback(new OkHttpUtils.ICallback() {
            @Override
            public void getData(String result) {
                iUpdateNichengModle.success(result);
            }
        });
        //获取用户uid
        //为了保证在项目的任何地方可以拿到context 所有, 创建了ContextApplication类 并在清单文件中进行注册
        SharedPreferences user = ContextApplication.getAppContext().getSharedPreferences("User", Context.MODE_PRIVATE);
        String suid = user.getString("uid", null);
        String stoken = user.getString("token", null);
        HashMap<String,String> params = new HashMap<>();
        Log.e("mmmmsssss",suid+"-----"+nickname+"------"+stoken);
        params.put("uid",suid);
        params.put("nickname",nickname);
        params.put("token",stoken);
        okHttpUtils.post(Constant.UPDATE_NICKNAME,params);
    }







    public interface IUpdateNichengModle{
        void success(String string);
        void failure(Call call, IOException e);
    }

}
