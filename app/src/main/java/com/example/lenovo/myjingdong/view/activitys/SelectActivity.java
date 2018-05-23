package com.example.lenovo.myjingdong.view.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.myjingdong.R;
import com.example.lenovo.myjingdong.adapters.SelectLineAdapter;
import com.example.lenovo.myjingdong.bean.SelectBean;
import com.example.lenovo.myjingdong.presenter.SelectPressenter;
import com.example.lenovo.myjingdong.view.interfaces.ISelectView;

public class SelectActivity extends BaseActivity implements View.OnClickListener,ISelectView{

    private ImageView fanhui;
    private EditText select_et;
    private ImageView select_et_lin;
    private SelectPressenter selectPressenter;
    private String keywords;
    private RecyclerView select_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        initView();
        initData();
    }

    private void initData() {
        //取得从上一个Activity当中传递过来的Intent对象
        Intent intent = getIntent();
        //从Intent当中根据key取得value
        if (intent != null) {
            keywords = intent.getStringExtra("keywords");
        }
        select_et.setText(keywords);

        selectPressenter = new SelectPressenter(this);
        selectPressenter.ShowData(keywords);
        Log.e("view",keywords+"");

    }

    private void initView() {
        fanhui = findViewById(R.id.select_fanhui);
        select_et = findViewById(R.id.select_et);
        select_et_lin = findViewById(R.id.select_iv_lin);
        select_rv =findViewById(R.id.select_rv);
        fanhui.setOnClickListener(this);
        select_et_lin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select_fanhui:
                finish();
                break;
            case R.id.select_iv_lin:

                break;
        }
    }

    @Override
    public void selectData(SelectBean selectBean) {
        SelectLineAdapter selectLineAdapter = new SelectLineAdapter(SelectActivity.this, selectBean.getData());
        select_rv.setLayoutManager(new GridLayoutManager(this,1, LinearLayoutManager.VERTICAL,false));
        select_rv.setAdapter(selectLineAdapter);
    }
}
