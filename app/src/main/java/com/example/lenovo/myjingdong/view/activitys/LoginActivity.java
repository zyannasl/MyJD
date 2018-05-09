package com.example.lenovo.myjingdong.view.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.myjingdong.R;
import com.example.lenovo.myjingdong.view.interfaces.ILoginView;

public class LoginActivity extends AppCompatActivity implements ILoginView,View.OnClickListener{

    private TextView fanhui;
    private TextView zhanghaodenglu;
    private TextView shoujidenglu;
    private EditText name;
    private EditText password;
    private Button login_button;
    private TextView zhuce;
    private TextView gengduo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
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
        zhuce = findViewById(R.id.login_zhuce);
        zhuce.setOnClickListener(this);
        gengduo = findViewById(R.id.login_gengduo);
        gengduo.setOnClickListener(this);

    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this,HomePagerActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginError(String mag) {
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_X:
                finish();
                break;
            case R.id.login_zhanghaodenglu:

                break;
            case R.id.login_shoujidenglu:

                break;
            case R.id.login_button:

                break;
            case R.id.login_zhuce:

                break;
            case R.id.login_gengduo:

                break;
        }
    }
}
