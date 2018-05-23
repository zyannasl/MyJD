package com.example.lenovo.myjingdong.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.lenovo.myjingdong.R;
import com.example.lenovo.myjingdong.bean.JiuGongGeBean;
import java.util.List;

public class ClassifyAdapter extends RecyclerView.Adapter{
    private Context context;
    private List<JiuGongGeBean.DataBean> list;
    private OnItemClickListener clickListener;

    public ClassifyAdapter(Context context, List<JiuGongGeBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.item_list,null);
        return new ClassifyAdapter.MyHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        MyHolderView viewHolder=(MyHolderView)holder;
        viewHolder.text.setText(list.get(position).getName());
        viewHolder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int layoutPosition = holder.getLayoutPosition();
                clickListener.onClick(v,layoutPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static interface OnItemClickListener{
        void onClick(View view,int position);
    }
    public void setClickListener(OnItemClickListener clickListener){
        this.clickListener=clickListener;
    }
    class MyHolderView extends RecyclerView.ViewHolder{
        private TextView text;
        public MyHolderView(View itemView) {
            super(itemView);
            text=itemView.findViewById(R.id.item_textview);
        }
    }
}
