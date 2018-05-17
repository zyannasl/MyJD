package com.example.lenovo.myjingdong.presenter;

import android.util.Log;

import com.example.lenovo.myjingdong.bean.UserMsgBean;
import com.example.lenovo.myjingdong.model.MyMsgModel;
import com.example.lenovo.myjingdong.view.interfaces.IMyMsgView;

import java.io.IOException;

import okhttp3.Call;

public class MyMsgPresenter {
    private IMyMsgView iMyMsg;
    private MyMsgModel myMsgModel;

    public MyMsgPresenter(IMyMsgView iMyMsg){
        this.iMyMsg=iMyMsg;
        myMsgModel = new MyMsgModel();
    }
    public void userData(){
        myMsgModel.getUserData();
        myMsgModel.setiMyMsgModel(new MyMsgModel.IMyMsgModel() {
            @Override
            public void success(UserMsgBean userMsgBean) {
                Log.e("2",userMsgBean.getData().getMobile()+"");
                iMyMsg.userMsg(userMsgBean);
            }

            @Override
            public void failure(Call call, IOException e) {

            }
        });

    }
}
