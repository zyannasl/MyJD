package com.example.lenovo.myjingdong.view.activitys;

import com.example.lenovo.myjingdong.R;
import com.example.lenovo.myjingdong.bean.RegBean;
import com.example.lenovo.myjingdong.presenter.RegisterPresenter;
import com.example.lenovo.myjingdong.view.interfaces.IRegisterView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ZhuceActivity extends BaseActivity implements IRegisterView,View.OnClickListener{

    private ImageView fanhui;
    private EditText moible;
    private EditText pwd;
    private Button button;
    private RegisterPresenter mRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        initView();
        initData();
    }

    private void initData() {
        mRegisterPresenter = new RegisterPresenter(this);
    }

    private void initView() {
        fanhui = findViewById(R.id.zhuce_fanhui);
        fanhui.setOnClickListener(this);
        moible = findViewById(R.id.zhuce_number);
        pwd = findViewById(R.id.zhuce_pwd);
        button = findViewById(R.id.zhuce_button);
        button.setOnClickListener(this);
    }

    @Override
    public void mobileError() {
        Toast.makeText(this, "手机号有误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void passError() {
        Toast.makeText(this, "密码不能为空或者不合法", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void success(RegBean regBean) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void fail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void serverError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zhuce_fanhui:
                finish();
                break;
            case R.id.zhuce_button:
                mRegisterPresenter.register(moible.getText().toString(),pwd.getText().toString());
                break;
        }

    }
}
