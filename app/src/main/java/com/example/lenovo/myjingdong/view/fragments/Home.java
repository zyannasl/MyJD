package com.example.lenovo.myjingdong.view.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.lenovo.myjingdong.R;
import com.example.lenovo.myjingdong.utils.GlideImageLoader;
import com.example.lenovo.myjingdong.view.activitys.MsgActivity;
import com.example.lenovo.myjingdong.view.activitys.QueryActivity;
import com.example.lenovo.myjingdong.view.activitys.SaoActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.youth.banner.Banner;
import java.util.ArrayList;

public class Home extends Fragment implements View.OnClickListener{

    private Banner banner;
    private ArrayList<Object> list;
    private ImageView saoyisao;
    private ImageView selectIv;
    private ImageView msg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.home_fragment,container,false);
        banner = view.findViewById(R.id.home_banner);
        saoyisao =view. findViewById(R.id.sao_iv);
        saoyisao.setOnClickListener(this);
        selectIv = view.findViewById(R.id.select_iv);
        selectIv.setOnClickListener(this);
        msg = view.findViewById(R.id.msg_iv);
        msg.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sao_iv:
                IntentIntegrator integrator = new IntentIntegrator(getActivity());
                // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setCaptureActivity(SaoActivity.class);
                integrator.setPrompt("请扫描二维码"); //底部的提示文字，设为""可以置空
                integrator.setCameraId(0); //前置或者后置摄像头
                integrator.setBeepEnabled(true); //扫描成功的「哔哔」声，默认开启
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
                break;
            case R.id.select_iv:
                Intent intent = new Intent(getActivity(),QueryActivity.class);
                startActivity(intent);
                break;
            case R.id.msg_iv:
                Intent intent2 = new Intent(getActivity(), MsgActivity.class);
                startActivity(intent2);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            String result = scanResult.getContents();
            Log.e("HYN", result);
            Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
        }
    }
}
