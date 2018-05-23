package com.example.lenovo.myjingdong.presenter;

import com.example.lenovo.myjingdong.model.UpdateNichengModel;
import com.example.lenovo.myjingdong.view.interfaces.IUpdateNichengView;

import java.io.IOException;

import okhttp3.Call;

public class UpdateNickNamePresenter {
    private UpdateNichengModel updateNichengModel;
    private IUpdateNichengView iUpdateNichengView;

    public UpdateNickNamePresenter(IUpdateNichengView iUpdateNichengView) {
        this.iUpdateNichengView = iUpdateNichengView;
        updateNichengModel=new UpdateNichengModel();
    }
    public void getUpdateNickName(String nickname){
        updateNichengModel.updateNickName(nickname);
        updateNichengModel.setUpdateNichengModel(new UpdateNichengModel.IUpdateNichengModle() {
            @Override
            public void success(String string) {
                iUpdateNichengView.updateData(string);
            }

            @Override
            public void failure(Call call, IOException e) {
                iUpdateNichengView.updateErrer(e+"");
            }
        });
    }
}
