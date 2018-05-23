package com.example.lenovo.myjingdong.presenter;

import com.example.lenovo.myjingdong.bean.ClassifyYouBean;
import com.example.lenovo.myjingdong.model.ClassifyModel;
import com.example.lenovo.myjingdong.view.interfaces.IClassifyFragmentView;

public class ClassifyPressenter {
    private IClassifyFragmentView iClassifyFragmentView;
    private ClassifyModel classifyModel;

    public ClassifyPressenter(IClassifyFragmentView iClassifyFragmentView) {
        this.iClassifyFragmentView = iClassifyFragmentView;
        classifyModel=new ClassifyModel();
    }
    public void classifyData(String cid){
        classifyModel.ClassifyData(cid);
        classifyModel.setiClassifyModel(new ClassifyModel.IClassifyModel() {
            @Override
            public void success(ClassifyYouBean classifyYouBean) {
                iClassifyFragmentView.showClassifyYou(classifyYouBean);
            }
        });
    }

}
