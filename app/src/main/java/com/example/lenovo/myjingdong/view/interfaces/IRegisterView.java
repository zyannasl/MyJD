package com.example.lenovo.myjingdong.view.interfaces;

import com.example.lenovo.myjingdong.bean.LoginBean;
import com.example.lenovo.myjingdong.bean.RegBean;

public interface IRegisterView {
    void mobileError();
    void passError();

    void success(RegBean regBean);//注册成功 code 0
    void fail(String msg);//注册失败 code 1
    void serverError(String msg);//异常情况
}
