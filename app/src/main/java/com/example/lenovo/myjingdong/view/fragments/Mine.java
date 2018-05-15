package com.example.lenovo.myjingdong.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.myjingdong.R;
import com.example.lenovo.myjingdong.view.activitys.LoginActivity;

public class Mine extends Fragment implements View.OnClickListener{

    private ImageView img;
    private TextView login;
    String token=null;
    private boolean token1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.mine_fragment,container,false);
        img = view.findViewById(R.id.mine_image);
        login = view.findViewById(R.id.mine_login);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        img.setOnClickListener(this);
        SharedPreferences sp = getContext().getSharedPreferences("token", Context.MODE_PRIVATE);
        token1 = sp.getBoolean("token", false);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mine_image:
                if(token1){

                }else{
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivityForResult(intent,100);
                }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==getActivity().RESULT_OK){
            String mobile = data.getStringExtra("mobile");
            login.setText(mobile);

        }
    }
}
