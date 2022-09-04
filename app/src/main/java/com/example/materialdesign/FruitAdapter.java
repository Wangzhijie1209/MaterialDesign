package com.example.materialdesign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> mFruitList;
    private Context mContext;

    public FruitAdapter(List<Fruit> mFruitList) {
        this.mFruitList = mFruitList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext==null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false);
        //返回一个ViewHolder 并将item布局传入
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitAdapter.ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruit_name.setText(fruit.getName());
        //with 可以传入一个Context Activity 或 Fragment参数
        //然后调用load()方法去加载图片 可以是一个URL地址,也可以是一个本地路径 或者是一个资源id
        //最后调用into()方法将图片设置到具体某一个ImageView中就可以了
        Glide.with(mContext).load(fruit.getImageId()).into(holder.fruit_image);
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        private final ImageView fruit_image;
        private final TextView fruit_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            fruit_image = itemView.findViewById(R.id.fruit_image);
            fruit_name = itemView.findViewById(R.id.fruit_name);
        }
    }
}
