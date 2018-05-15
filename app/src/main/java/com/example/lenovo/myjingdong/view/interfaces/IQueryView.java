package com.example.lenovo.myjingdong.view.interfaces;

import com.example.lenovo.myjingdong.bean.QueryBean;

import java.util.List;

public interface IQueryView {
    //显示商品也

    void showGoodsList(List<QueryBean.DataBean> list);

    //获取输入框内容
    String getContent();
}
