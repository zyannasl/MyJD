package com.example.lenovo.myjingdong.view.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.myjingdong.R;
import com.example.lenovo.myjingdong.custom.FlowLayout;
import com.example.lenovo.myjingdong.custom.MyView;

public class QueryActivity extends BaseActivity{
    private String mNames[] = {
            "好好学习","天天向上","我要疯了",
            "学不会啊,怎么办呢","感觉天书一样","神啊,救救我吧!!!!!",
            "安卓就是一个坑","跳下来就是一望无际的黑暗",
            "老司机开车,请系好安全套","你一万多的假包真好看","腾讯视频",
            "错误的方式","天道酬勤,恒者能胜","抖音专辑","不想干了 , 我要休息!!!!!!"
    };
    private FlowLayout mFlowLayout;
    private MyView myview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        initChildViews();
        myview = findViewById(R.id.myview);
        myview.setCallBackInterface(new MyView.CallBackInterface() {
            @Override
            public void onSearchClick(String keywords) {
                Toast.makeText(QueryActivity.this, ""+keywords, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(QueryActivity.this, SelectActivity.class);
                intent.putExtra("keywords",keywords);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick() {
                Toast.makeText(QueryActivity.this, "清空了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initChildViews() {
        mFlowLayout = findViewById(R.id.flowLayout);
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 10;
        lp.topMargin = 10;
        lp.bottomMargin = 5;
        for(int i = 0; i < mNames.length; i ++){
            TextView view = new TextView(this);
            view.setText(mNames[i]);
            view.setTextColor(Color.BLACK);
            view.setBackgroundColor(Color.GRAY);
            view.setTextSize(18);
            mFlowLayout.addView(view,lp);

        }
    }


}
