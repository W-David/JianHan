package com.example.jianhan.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jianhan.R;
import com.example.jianhan.model.bean.MoeDatum;
import com.example.jianhan.model.bean.MoeImg;
import com.example.jianhan.ui.adapter.holder.BottomViewHolder;
import com.example.jianhan.ui.adapter.holder.FooterViewHolder;
import com.example.jianhan.ui.adapter.holder.MoeImgViewHolder;
import com.example.jianhan.util.L;

import java.util.ArrayList;
import java.util.List;

public class MoeImgAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int NORMAL = 1;
    private static final int FOOTER = 2;
    private static final int BOTTOM = 3;

    private static final String TAG = "MoeImgAdapter";
    private List<Item> items;

    public MoeImgAdapter(){
        items = new ArrayList<>();
    }

    public void setItems(MoeImg moeImg){
        int prevSize = items.size();
        items.clear();
        notifyItemRangeRemoved(0,prevSize);
        addItems(moeImg);
    }

    public void addItems(MoeImg moeImg){
        int startPosition = items.size();
        for(MoeDatum moeDatum: moeImg.getData()){
            Item item = new Item();
            item.setType(NORMAL);
            item.setMoeDatum(moeDatum);
            items.add(item);
        }
        L.i(TAG,"size: " + startPosition + " to: " + items.size());
        notifyItemRangeInserted(startPosition, items.size() - startPosition);
        notifyItemRangeChanged(startPosition,items.size() - startPosition);
    }

    public void addFooter(){
        Item item = new Item();
        item.setType(FOOTER);
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    public void removeFooter(){
        items.remove(items.size() - 1);
        notifyItemRemoved(items.size());
    }

    public void addBottom(){
        Item item = new Item();
        item.setType(BOTTOM);
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if(getItemViewType(position) == NORMAL) return 1;
                    else return gridLayoutManager.getSpanCount();
                }
            });
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == NORMAL){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_recycler_item,parent,false);
            return new MoeImgViewHolder(view);
        }else if(viewType == FOOTER){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_footer,parent,false);
            return new FooterViewHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_recycler_bottom,parent,false);
            return new BottomViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MoeImgViewHolder){
            ((MoeImgViewHolder) holder).bindData(items.get(position).getMoeDatum());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private static class Item{

        private int type;
        private MoeDatum moeDatum;

        int getType() {
            return type;
        }

        void setType(int type) {
            this.type = type;
        }

        MoeDatum getMoeDatum(){
            return moeDatum;
        }

        void setMoeDatum(MoeDatum moeDatum){
            this.moeDatum = moeDatum;
        }
    }
}

