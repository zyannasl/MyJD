package com.example.lenovo.myjingdong.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.myjingdong.R;
import com.example.lenovo.myjingdong.bean.JiuGongGeBean;

import java.util.List;

public class JiuGongGeAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<JiuGongGeBean.DataBean> list;

    public JiuGongGeAdapter(Context context, List<JiuGongGeBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.item_jiugongge,null);
        return new MyHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolderView viewHolder=(MyHolderView)holder;
        Glide.with(context).load(list.get(position).getIcon()).into(viewHolder.img);
        viewHolder.text.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyHolderView extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView text;
        public MyHolderView(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.jiu_img);
            text=itemView.findViewById(R.id.jiu_tv);
        }
    }
}
