package com.example.jianhan.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jianhan.R;
import com.example.jianhan.ui.adapter.holder.DetailImgViewHolder;

import java.util.ArrayList;
import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> items;

    public DetailAdapter(){
        items = new ArrayList<>();
    }

    public void setItems(List<String> imgUrls){
        items.clear();
        items.addAll(imgUrls);
        notifyItemRangeChanged(0,items.size());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_detail_item,parent,false);
        return new DetailImgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((DetailImgViewHolder) holder).bindData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
