package com.example.lenovo.myjingdong.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.example.lenovo.myjingdong.bean.ClassifyYouBean;

import java.util.ArrayList;
import java.util.List;

public class MyExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<ClassifyYouBean.DataBean.ListBean> clist=new ArrayList<>();
    private List<ClassifyYouBean.DataBean> plist=new ArrayList<>();
    @Override
    public int getGroupCount() {
        return plist.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return clist.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return plist;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
