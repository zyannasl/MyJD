package com.example.lenovo.myjingdong.model.minterfaces;

import com.example.lenovo.myjingdong.model.listeneer.LoginListener;

public interface ILoginModel {
    //登录
    void login(String name, String password, LoginListener loginListener);
}
