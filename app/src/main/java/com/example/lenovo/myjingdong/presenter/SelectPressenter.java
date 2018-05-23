package com.example.lenovo.myjingdong.presenter;

import android.util.Log;

import com.example.lenovo.myjingdong.bean.SelectBean;
import com.example.lenovo.myjingdong.model.SelectModel;
import com.example.lenovo.myjingdong.view.interfaces.ISelectView;

import java.io.IOException;

import okhttp3.Call;

public class SelectPressenter {
    private ISelectView iSelectView;
    private SelectModel selectModel;

    public SelectPressenter(ISelectView iSelectView) {
        this.iSelectView = iSelectView;
        selectModel=new SelectModel();
    }
    public void ShowData(String keywords){
        selectModel.SelectData(keywords);
        selectModel.setiSelectModel(new SelectModel.ISelectModel() {
            @Override
            public void success(SelectBean selectBean) {
                iSelectView.selectData(selectBean);
            }

            @Override
            public void failure(Call call, IOException e) {

            }
        });
    }
}
