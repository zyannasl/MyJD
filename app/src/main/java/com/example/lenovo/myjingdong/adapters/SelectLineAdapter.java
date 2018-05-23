package com.example.lenovo.myjingdong.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.myjingdong.R;
import com.example.lenovo.myjingdong.bean.SelectBean;
import java.util.List;

public class SelectLineAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<SelectBean.DataBean> list;

    public SelectLineAdapter(Context context, List<SelectBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.item_select_line,null);
        return new SelectLineAdapter.MyHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        MyHolderView viewHolder=(MyHolderView)holder;
        Glide.with(context).load(list.get(position).getImages()).into(viewHolder.image);
        viewHolder.title.setText(list.get(position).getTitle());
        viewHolder.prices.setText(list.get(position).getPrice());
        viewHolder.subhead.setText(list.get(position).getSubhead());

    }

    @Override
    public int getItemCount() {
        Log.e("adapter",list.size()+"");
        return list.size();
    }


    class MyHolderView extends RecyclerView.ViewHolder{
        private TextView subhead;
        private TextView title;
        private ImageView image;
        private TextView prices;

        public MyHolderView(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.select_line_iv);
            title=itemView.findViewById(R.id.select_line_title);
            prices= itemView.findViewById(R.id.select_line_prices);
            subhead=itemView.findViewById(R.id.select_line_subhead);
        }
    }
}
