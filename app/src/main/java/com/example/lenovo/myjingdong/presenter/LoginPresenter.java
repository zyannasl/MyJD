package com.example.lenovo.myjingdong.presenter;

import android.widget.Toast;

import com.example.lenovo.myjingdong.bean.LoginBean;
import com.example.lenovo.myjingdong.model.impl.LogingModelImpl;
import com.example.lenovo.myjingdong.utils.Common;
import com.example.lenovo.myjingdong.utils.Constant;
import com.example.lenovo.myjingdong.view.interfaces.ILoginView;

import java.io.IOException;

import okhttp3.Call;

public class LoginPresenter {
    private ILoginView iLoginView;
    private LogingModelImpl mLoginModelImpl;
    public LoginPresenter(ILoginView iLoginView){
        this.iLoginView=iLoginView;
        mLoginModelImpl=new LogingModelImpl();
    }
    public void login(String mobile,String pwd){
       if(!Common.isMobileNO(mobile)){
           iLoginView.mobileError();
           return;
       }
       if(pwd.length()<6){
           iLoginView.pwdError();
       }
       mLoginModelImpl.Login(mobile,pwd);
       mLoginModelImpl.setiLoginModel(new LogingModelImpl.ILoginModel() {
           @Override
           public void success(LoginBean loginBean) {
               iLoginView.loginSuccess(loginBean);
           }

           @Override
           public void failure(Call call, IOException e) {
                iLoginView.loginError("e");
           }
       });
    }
}
