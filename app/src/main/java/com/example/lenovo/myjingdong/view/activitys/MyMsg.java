package com.example.lenovo.myjingdong.view.activitys;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
    private static final int IMAGE_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int RESIZE_REQUEST_CODE = 2;
    private static final String IMAGE_FILE_NAME = "header.jpg";

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
                if (isSdcardExisting()) {
                    Intent cameraIntent = new Intent(
                            "android.media.action.IMAGE_CAPTURE");
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, getImageUri());
                    cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
                } else {
                    Toast.makeText(v.getContext(), "请插入sd卡", Toast.LENGTH_LONG)
                            .show();
                }
                Toast.makeText(this, "打开相机", Toast.LENGTH_SHORT).show();

                    mPopWindow.dismiss();

                break;
            case R.id.xiangce:
                Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                intent1.addCategory(Intent.CATEGORY_OPENABLE);
                intent1.setType("image/*");
                startActivityForResult(intent1, IMAGE_REQUEST_CODE);
                Toast.makeText(this, "打开相册", Toast.LENGTH_SHORT).show();
                    mPopWindow.dismiss();

                break;
            case R.id.quxiao:
                mPopWindow.dismiss();
                break;

        }
    }
    //获取图片的URI
    private Uri  getImageUri() {
        return Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                IMAGE_FILE_NAME));
    }
    //该方法是用于放回相应结果的
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            switch (requestCode) {
                case IMAGE_REQUEST_CODE:
                    resizeImage(data.getData());
                    break;
                case CAMERA_REQUEST_CODE:
                    if(isSdcardExisting()){
                        resizeImage(getImageUri());
                    }else{
                        Toast.makeText(MyMsg.this, "未找到存储卡，无法存储照片！",
                                Toast.LENGTH_LONG).show();
                    }
                    break;
                case RESIZE_REQUEST_CODE:
                    if (data != null) {
                        showResizeImage(data);
                    }
                    break;

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void showResizeImage(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(photo);
            image.setImageDrawable(drawable);

        }
    }

    /**
     * 对图片进行处理
     * @param uri
     */
    private void resizeImage(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESIZE_REQUEST_CODE);
    }

    /**
     * 判断sd卡是否存在
     *
     * @return
     */
    private boolean isSdcardExisting() {
        final String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
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
  /*  @Override
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
        if(requestCode==1000&&resultCode==RESULT_OK){
            Uri uri=data.getData();
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri,"image/*");
            intent.putExtra("aspectX",1);
            intent.putExtra("aspectY",1);
            intent.putExtra("outputX",250);
            intent.putExtra("outputY",250);
            intent.putExtra("return-data",true);
            startActivityForResult(intent,2000);

        }
        if(requestCode==2000&&resultCode==RESULT_OK){
            Bitmap bitmap = data.getParcelableExtra("data");
            image.setImageBitmap(bitmap);
        }
    }*/
}
