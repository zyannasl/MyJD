package com.example.lenovo.myjingdong.presenter;

import android.widget.Toast;

import com.example.lenovo.myjingdong.bean.RegBean;
import com.example.lenovo.myjingdong.model.impl.RegisterModel;
import com.example.lenovo.myjingdong.utils.Common;
import com.example.lenovo.myjingdong.view.interfaces.IRegisterView;

import java.io.IOException;

import okhttp3.Call;

public class RegisterPresenter {
    private IRegisterView iRegisterView;
    private RegisterModel mRegisterModel;
    public RegisterPresenter(IRegisterView iRegisterView){
        this.iRegisterView=iRegisterView;
        mRegisterModel=new RegisterModel();
    }
    public void register(String mobile,String pwd){
        if(!Common.isMobileNO(mobile)){
         iRegisterView.mobileError();
         return;
        }
        if(pwd.length()<6){
            iRegisterView.passError();
            return;
        }
        mRegisterModel.register(mobile,pwd);
        mRegisterModel.setiRegisterModel(new RegisterModel.IRegisterModel() {
            @Override
            public void success(RegBean regBean) {

                iRegisterView.success(regBean);

            }

            @Override
            public void failure(Call call, IOException e) {

                iRegisterView.fail("服务器有异常，请稍后再试");

            }
        });
    }
}
