package com.example.lenovo.myjingdong.view.update;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.myjingdong.R;
import com.example.lenovo.myjingdong.presenter.UpdateNickNamePresenter;
import com.example.lenovo.myjingdong.view.activitys.MsgActivity;
import com.example.lenovo.myjingdong.view.activitys.MyMsg;
import com.example.lenovo.myjingdong.view.interfaces.IUpdateNichengView;

public class UpdateNicheng extends AppCompatActivity implements IUpdateNichengView ,View.OnClickListener{

    private TextView fanhui;
    private TextView sure;
    private EditText nicheng;
    private UpdateNickNamePresenter updateNickNamePresenter;
    private String nickname;
    private SharedPreferences user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nicheng);
        initView();
        initData();

    }

    private void initData() {

        updateNickNamePresenter = new UpdateNickNamePresenter(this);

    }

    private void initView() {
        fanhui = findViewById(R.id.nicheng_fanhui);
        fanhui.setOnClickListener(this);
        sure = findViewById(R.id.nicheng_sure);
        sure.setOnClickListener(this);
        nicheng=findViewById(R.id.nicheng_et);
    }


    @Override
    public void updateData(String string) {
        Toast.makeText(this, ""+ string, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void updateErrer(String errer) {
        Toast.makeText(this, ""+ errer, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nicheng_fanhui:
                finish();
                break;
            case R.id.nicheng_sure:
                String s = nicheng.getText().toString();

                Log.e("eeeeeee",s+"");
                updateNickNamePresenter.getUpdateNickName(s);
                Intent intent = new Intent(UpdateNicheng.this, MyMsg.class);
                startActivity(intent);
                break;
        }
    }
}
