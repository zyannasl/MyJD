package com.example.lenovo.myjingdong.presenter;

import com.example.lenovo.myjingdong.bean.JiuGongGeBean;
import com.example.lenovo.myjingdong.model.HomeFragemtModel;
import com.example.lenovo.myjingdong.view.interfaces.IHomeFragment;

public class HomeFragmentPresenter {
    private IHomeFragment iHomeFragment;
    private HomeFragemtModel homeFragemtModel;

    public HomeFragmentPresenter(IHomeFragment iHomeFragment) {
        this.iHomeFragment = iHomeFragment;
        homeFragemtModel=new HomeFragemtModel();
    }
    public void jiuGongGeData(){
        homeFragemtModel.getJiuGonggeData();;
        homeFragemtModel.setiHomeFragnemtModel(new HomeFragemtModel.IHomeFragnemtModel() {
            @Override
            public void success(JiuGongGeBean jiuGongGeBean) {
                iHomeFragment.showJGG(jiuGongGeBean);
            }
        });
    }
}
