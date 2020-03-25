package com.example.jianhan.ui.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jianhan.R;

public class DetailImgViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {

    private String imgUrl;
    private ImageView DetailImg;
    private Context context;

    public DetailImgViewHolder(@NonNull View itemView) {
        super(itemView);
        CardView cardView = itemView.findViewById(R.id.detail_card_view);
        DetailImg = itemView.findViewById(R.id.detail_image);
        cardView.setOnClickListener(this);
        cardView.setOnLongClickListener(this);
        context = itemView.getContext();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.detail_card_view) {
            Toast.makeText(context, "展示功能开发中，敬请期待...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if(v.getId() == R.id.detail_card_view){
            Toast.makeText(context, "下载功能开发中，敬请期待...", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void bindData(String imgUrl){
        this.imgUrl = imgUrl;
        Glide.with(context).load(imgUrl).into(DetailImg);
    }

}
