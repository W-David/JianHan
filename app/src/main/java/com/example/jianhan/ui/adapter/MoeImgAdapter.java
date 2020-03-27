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
import com.example.jianhan.ui.adapter.holder.EmptyResultViewHolder;
import com.example.jianhan.ui.adapter.holder.FooterViewHolder;
import com.example.jianhan.ui.adapter.holder.MoeImgViewHolder;
import java.util.List;

public class MoeImgAdapter extends BaseAdapter<MoeImgAdapter.Item,RecyclerView.ViewHolder> {

    private static final int NORMAL = 1;
    private static final int FOOTER = 2;
    private static final int EMPTY = 3;
    private static final int BOTTOM = 4;

    private static final String TAG = "MoeImgAdapter";

    public MoeImgAdapter(List<Item> dataSet){
        super(dataSet);
    }

    public void setItems(MoeImg moeImg){
        dataSet.clear();
        addItems(moeImg);
    }

    public void addItems(MoeImg moeImg){
        for(MoeDatum moeDatum: moeImg.getData()){
            Item item = new Item();
            item.setType(NORMAL);
            item.setMoeDatum(moeDatum);
            dataSet.add(item);
        }
        notifyDiff();
    }

    public void addFooter(){
        Item item = new Item();
        item.setType(FOOTER);
        dataSet.add(item);
        notifyDiff();
    }

    public void removeFooter(){
        if(dataSet.get(dataSet.size()-1).getType() == BOTTOM) return;
        dataSet.remove(dataSet.size() - 1);
        notifyDiff();
    }

    public void addEmpty(){
        dataSet.clear();
        Item item = new Item();
        item.setType(EMPTY);
        dataSet.add(item);
        notifyDiff();
    }
    public void addBottom(){
        if(dataSet.get(dataSet.size()-1).getType() == BOTTOM) return;
        Item item = new Item();
        item.setType(BOTTOM);
        dataSet.add(item);
        notifyDiff();
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
        }else if(viewType == BOTTOM){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_not_more,parent,false);
            return new BottomViewHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_empty,parent,false);
            return new EmptyResultViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MoeImgViewHolder){
            ((MoeImgViewHolder) holder).bindData(dataSet.get(position).getMoeDatum());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return dataSet.get(position).getType();
    }

    @Override
    protected boolean areItemsTheSame(Item oldItem, Item newItem) {
        if(oldItem.getType() != newItem.getType()){
            return false;
        }else if(oldItem.getType() == NORMAL){
            return oldItem.getMoeDatum().getSort().equals(newItem.getMoeDatum().getSort());
        }else{
            return true;
        }
    }

    @Override
    protected boolean areContentsTheSame(Item oldItem, Item newItem) {
        if(oldItem.getType() == NORMAL){
            return oldItem.getMoeDatum().getThumbnail().equals(newItem.getMoeDatum().getThumbnail());
        }
        return true;
    }

    static class Item{

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

