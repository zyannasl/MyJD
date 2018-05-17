package com.example.lenovo.myjingdong.view.activitys;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.myjingdong.R;
import com.example.lenovo.myjingdong.bean.LoginBean;
import com.example.lenovo.myjingdong.presenter.LoginPresenter;
import com.example.lenovo.myjingdong.view.fragments.Mine;
import com.example.lenovo.myjingdong.view.interfaces.ILoginView;

public class LoginActivity extends BaseActivity implements ILoginView,View.OnClickListener{

    private TextView fanhui;
    private TextView zhanghaodenglu;
    private TextView shoujidenglu;
    private EditText name;
    private EditText password;
    private Button login_button;
    private TextView zhuce;
    private TextView wangjimima;
    private ImageView weixin;
    private ImageView qq;
    private LoginPresenter loginPresenter;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    private void initData() {
        loginPresenter = new LoginPresenter(this);
    }

    private void initView() {
        fanhui = findViewById(R.id.login_X);
        fanhui.setOnClickListener(this);
        zhanghaodenglu = findViewById(R.id.login_zhanghaodenglu);
        zhanghaodenglu.setOnClickListener(this);
        shoujidenglu = findViewById(R.id.login_shoujidenglu);
        shoujidenglu.setOnClickListener(this);
        name = findViewById(R.id.login_name);
        password = findViewById(R.id.login_password);
        login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(this);
        wangjimima = findViewById(R.id.login_wangjimima);
        wangjimima.setOnClickListener(this);
        zhuce=findViewById(R.id.login_zhuce);
        zhuce.setOnClickListener(this);
        weixin = findViewById(R.id.login_weixin);
        weixin.setOnClickListener(this);
        qq = findViewById(R.id.login_qq);
        qq.setOnClickListener(this);

    }


    @Override
    public void loginSuccess(LoginBean loginBean) {
       Log.e("------------",loginBean.getData().getMobile()+"");
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
         //记录登录状态
                //获取sp对象
                sp = getSharedPreferences("User", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("mobile",loginBean.getData().getMobile());
                edit.putString("password",loginBean.getData().getPassword());
                edit.putString("token",loginBean.getData().getToken());
                edit.putString("uid",loginBean.getData().getUid()+"");
                edit.putBoolean("have",true);
                edit.commit();



    }

    @Override
    public void loginError(String mag) {
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mobileError() {
        Toast.makeText(this, "手机号码输入错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void pwdError() {
        Toast.makeText(this, "密码不得小于六位", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_X:
                finish();
                break;

            case R.id.login_button:
                loginPresenter.login(name.getText().toString(),password.getText().toString());
                Log.e("xxxxx",""+name.getText().toString());
                //跳转
                Intent data = new Intent(LoginActivity.this,HomePagerActivity.class);
                startActivity(data);
                finish();

                break;
            case R.id.login_zhuce:
                Intent intent1 = new Intent(LoginActivity.this, ZhuceActivity.class);
                startActivity(intent1);
                break;
            case R.id.login_weixin:

                break;
            case R.id.login_qq:

                break;

        }
    }
}
