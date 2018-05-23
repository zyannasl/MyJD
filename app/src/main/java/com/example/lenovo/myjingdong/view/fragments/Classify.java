package com.example.lenovo.myjingdong.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.lenovo.myjingdong.R;
import com.example.lenovo.myjingdong.adapters.ClassifyAdapter;
import com.example.lenovo.myjingdong.adapters.JiuGongGeAdapter;
import com.example.lenovo.myjingdong.bean.ClassifyYouBean;
import com.example.lenovo.myjingdong.bean.JiuGongGeBean;
import com.example.lenovo.myjingdong.presenter.ClassifyPressenter;
import com.example.lenovo.myjingdong.presenter.HomeFragmentPresenter;
import com.example.lenovo.myjingdong.view.interfaces.IClassifyFragmentView;
import com.example.lenovo.myjingdong.view.interfaces.IHomeFragment;

import java.util.ArrayList;

public class Classify extends Fragment implements IHomeFragment,IClassifyFragmentView {

    private RecyclerView zuo;
    private HomeFragmentPresenter homeFragmentPresenter;
    private ExpandableListView you;
    private ClassifyPressenter classifyPressenter;
    private int cid;
    private ArrayList<String> plist;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.classify_fragment,container,false);
        zuo = view.findViewById(R.id.classify_zuo);
        you = view.findViewById(R.id.classify_you);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homeFragmentPresenter = new HomeFragmentPresenter(this);
        homeFragmentPresenter.jiuGongGeData();
        /*classifyPressenter = new ClassifyPressenter(this);
        classifyPressenter.classifyData("1");*/
    }

    /**
     * 分类页面左边条目
     * @param jiuGongGeBean
     */
    @Override
    public void showJGG(final JiuGongGeBean jiuGongGeBean) {
        ClassifyAdapter classifyAdapter = new ClassifyAdapter(getContext(), jiuGongGeBean.getData());
        zuo.setLayoutManager(new GridLayoutManager(getActivity(),1, LinearLayoutManager.VERTICAL,false));
        zuo.setAdapter(classifyAdapter);
        classifyAdapter.setClickListener(new ClassifyAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getActivity(), jiuGongGeBean.getData().get(position).getName(), Toast.LENGTH_SHORT).show();
                cid = jiuGongGeBean.getData().get(position).getCid();

            }
        });
    }

    /**
     * 分类页面右边二级列表
     * @param classifyYouBean
     */
    @Override
    public void showClassifyYou(ClassifyYouBean classifyYouBean) {
        classifyPressenter = new ClassifyPressenter(this);
        classifyPressenter.classifyData("1");
        plist = new ArrayList<>();
        for(int i=0;i<classifyYouBean.getData().size();i++){
            plist.add(classifyYouBean.getData().get(i).getName());
        }


    }
}
