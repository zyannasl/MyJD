package com.example.lenovo.myjingdong.view.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.media.DrmInitData;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.lenovo.myjingdong.R;
import com.example.lenovo.myjingdong.view.fragments.Classify;
import com.example.lenovo.myjingdong.view.fragments.Finds;
import com.example.lenovo.myjingdong.view.fragments.Home;
import com.example.lenovo.myjingdong.view.fragments.Mine;
import com.example.lenovo.myjingdong.view.fragments.ShoppingCart;
import com.hjm.bottomtabbar.BottomTabBar;

public class HomePagerActivity extends BaseActivity implements View.OnClickListener{

    private BottomTabBar bottomTabBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pager);
        initView();
        initData();

    }
    private void initView() {
        bottomTabBar = findViewById(R.id.bottomTabBar);

    }

    private void initData() {
        //底部导航栏
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(200,150)
                .setFontSize(0)
                .setChangeColor(Color.RED,Color.GRAY)
                .addTabItem("",R.mipmap.ac1s2,R.mipmap.ac0s1, Home.class)
                .addTabItem("",R.mipmap.abxf2,R.mipmap.abwf1, Classify.class)
                .addTabItem("",R.mipmap.abzfa2,R.mipmap.abyfa1, Finds.class)
                .addTabItem("",R.mipmap.abvg2,R.mipmap.abug1, ShoppingCart.class)
                .addTabItem("",R.mipmap.ac3w2,R.mipmap.ac2w1, Mine.class)
                .isShowDivider(false);

    }


    @Override
    public void onClick(View v) {

    }
}
