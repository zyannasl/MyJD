package com.example.lenovo.myjingdong.view.activitys;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.myjingdong.R;
import com.example.lenovo.myjingdong.bean.UserMsgBean;
import com.example.lenovo.myjingdong.presenter.MyMsgPresenter;
import com.example.lenovo.myjingdong.view.interfaces.IMyMsgView;
import com.example.lenovo.myjingdong.view.update.UpdateBrithday;
import com.example.lenovo.myjingdong.view.update.UpdateNicheng;
import com.example.lenovo.myjingdong.view.update.UpdateSex;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;

import java.io.File;

public class MyMsg extends BaseActivity implements View.OnClickListener,IMyMsgView {

    private ImageView image;
    private TextView nicheng;
    private TextView sex;
    private TextView brthday;
    private Button tuichu;
    private TextView name;
    private SharedPreferences sharedPreferences;
    private MyMsgPresenter myMsgPresenter;
    private PopupWindow mPopWindow;
    private String path= Environment.getExternalStorageDirectory()+"/jingdong.png";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_msg);
        initData();
        initView();
        initClick();

    }

    private void initData() {
        myMsgPresenter = new MyMsgPresenter(this);
        myMsgPresenter.userData();
    }


    /**
     * 初始化控件
     */
    private void initView() {
        image = findViewById(R.id.mymsg_image);
        name = findViewById(R.id.mymsg_name);
        nicheng = findViewById(R.id.mymsg_nicheng);
        sex = findViewById(R.id.mymsg_sex);
        brthday = findViewById(R.id.mymsg_brthday);
        tuichu=findViewById(R.id.mymsg_unlogin);
    }

    /**
     * 初始化点击事件
     */
    private void initClick() {
        image.setOnClickListener(this);
        name.setOnClickListener(this);
        nicheng.setOnClickListener(this);
        sex.setOnClickListener(this);
        brthday.setOnClickListener(this);
        tuichu.setOnClickListener(this);

    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mymsg_image:
                showPopupVindow();
                break;
            case R.id.mymsg_nicheng:
                Intent nicheng = new Intent(MyMsg.this, UpdateNicheng.class);
                startActivity(nicheng);
                break;
            case R.id.mymsg_name:
                Toast.makeText(this, "用户名不支持修改哦···", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mymsg_sex:
                Intent sex = new Intent(MyMsg.this, UpdateSex.class);
                startActivity(sex);
                break;
            case R.id.mymsg_brthday:
                Intent brthday = new Intent(MyMsg.this, UpdateBrithday.class);
                startActivity(brthday);
                break;
            case R.id.mymsg_unlogin:
                sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
                sharedPreferences.edit().clear().commit();
                Intent intent = new Intent(MyMsg.this, HomePagerActivity.class);
                startActivity(intent);
                break;
            case R.id.xiangji:
                Intent xiangji = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                xiangji.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
                startActivityForResult(xiangji,100);
                mPopWindow.dismiss();
                break;
            case R.id.xiangce:
                Intent xiangce = new Intent(Intent.ACTION_PICK);
                xiangce.setType("image/*");
                startActivityForResult(xiangce,1000);
                mPopWindow.dismiss();
                break;
            case R.id.quxiao:
                mPopWindow.dismiss();
                break;

        }
    }

    private void showPopupVindow() {
        //设置contentView
        View contentView = LayoutInflater.from(MyMsg.this).inflate(R.layout.item_popupwindow, null);
        mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        Button xiangji = contentView.findViewById(R.id.xiangji);
        Button xiangce = contentView.findViewById(R.id.xiangce);
        Button quxiao = contentView.findViewById(R.id.quxiao);
        xiangji.setOnClickListener(this);
        xiangce.setOnClickListener(this);
        quxiao.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(MyMsg.this).inflate(R.layout.activity_main, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 获取用户信息
     */

    @Override
    public void userMsg(UserMsgBean userMsgBean) {
        if(userMsgBean.getData().getIcon()==null){
            Glide.with(this).load(R.mipmap.user).into(image);
        }else{
            Glide.with(this).load(userMsgBean.getData().getIcon()).into(image);
        }

        name.setText(userMsgBean.getData().getUsername());
        if(userMsgBean.getData().getNickname()==null){
            nicheng.setText("还没有哦");
        }else{
            nicheng.setText(userMsgBean.getData().getNickname());
        }

        if(userMsgBean.getData().getGender()==0){
            sex.setText("男");
        }else if(userMsgBean.getData().getGender()==1){
            sex.setText("女");

        }else{
            sex.setText("保密");
        }
        brthday.setText(userMsgBean.getData().getAge()+"");


    }

    /**
     * 上传头像
     */

    @Override
    public void lodingImg() {

    }

    /**
     * 相机回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100&&resultCode==RESULT_OK){
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(Uri.fromFile(new File(path)),"image/*");
            intent.putExtra("CROP",true);
            intent.putExtra("aspectX",1);
            intent.putExtra("aspectY",1);
            intent.putExtra("outputX",250);
            intent.putExtra("outputY",250);
            intent.putExtra("return-data",true);
            startActivityForResult(intent,200);
        }
        if(requestCode==200&&resultCode==RESULT_OK){
            Bitmap bitmap = data.getParcelableExtra("data");
            image.setImageBitmap(bitmap);
        }
    }
}
