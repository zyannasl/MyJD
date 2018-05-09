package com.example.lenovo.myjingdong.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.myjingdong.R;
import com.example.lenovo.myjingdong.utils.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;

public class Home extends Fragment {

    private Banner banner;
    private ArrayList<Object> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.home_fragment,container,false);
        banner = view.findViewById(R.id.home_banner);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(list);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }
    private void initData() {
        list = new ArrayList<>();
        list.add("https://www.zhaoapi.cn//images//quarter//ad1.png");
        list.add("https://www.zhaoapi.cn//images//quarter//ad2.png");
        list.add("https://www.zhaoapi.cn//images//quarter//ad3.png");
        list.add("https://www.zhaoapi.cn//images//quarter//ad4.png");
    }
}
