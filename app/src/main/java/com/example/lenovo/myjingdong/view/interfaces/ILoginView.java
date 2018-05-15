package com.example.lenovo.myjingdong.view.interfaces;

import com.example.lenovo.myjingdong.bean.LoginBean;

public interface ILoginView {
    //登录成功
    void loginSuccess(LoginBean loginBean);
    //登录失败
    void loginError(String mag);

    void mobileError();

    void pwdError();
}
