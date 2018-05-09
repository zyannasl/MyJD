package com.example.lenovo.myjingdong.view.activitys;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.lenovo.myjingdong.R;
import com.example.lenovo.myjingdong.view.fragments.Classify;
import com.example.lenovo.myjingdong.view.fragments.Finds;
import com.example.lenovo.myjingdong.view.fragments.Home;
import com.example.lenovo.myjingdong.view.fragments.Mine;
import com.example.lenovo.myjingdong.view.fragments.ShoppingCart;
import com.hjm.bottomtabbar.BottomTabBar;

public class HomePagerActivity extends AppCompatActivity {

    private BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pager);
        bottomTabBar = findViewById(R.id.bottomTabBar);
        //沉浸式标题栏
        if(Build.VERSION.SDK_INT>=21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN |View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();

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
}
