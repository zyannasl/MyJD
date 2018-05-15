package com.example.lenovo.myjingdong.view.activitys;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.lenovo.myjingdong.R;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    int count=3;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            text.setText(getCount()+"s");
            handler.sendEmptyMessageDelayed(0,1000);
        }
    };

    private int getCount() {
        count--;
        if(count==0){
            Intent intent = new Intent(this, HomePagerActivity.class);
            startActivity(intent);
            finish();
        }
        return count;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去除标题
      requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.welcome_text);
        handler.sendEmptyMessageDelayed(0,1000);
        //沉浸式标题栏
        View decorView = getWindow().getDecorView();
        int option=View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
       ActionBar supportActionBar = getSupportActionBar();
       supportActionBar.hide();

    }
}
