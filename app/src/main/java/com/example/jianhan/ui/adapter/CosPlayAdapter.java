package com.example.jianhan.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jianhan.R;
import com.example.jianhan.model.bean.CosDatum;
import com.example.jianhan.model.bean.Cosplay;
import com.example.jianhan.ui.adapter.holder.BottomViewHolder;
import com.example.jianhan.ui.adapter.holder.CosPlayViewHolder;
import com.example.jianhan.ui.adapter.holder.EmptyResultViewHolder;
import com.example.jianhan.ui.adapter.holder.FooterViewHolder;
import com.example.jianhan.util.L;

import java.util.ArrayList;
import java.util.List;

public class CosPlayAdapter extends BaseAdapter<CosPlayAdapter.Item,RecyclerView.ViewHolder> {


    private static final int NORMAL = 1;
    private static final int FOOTER = 2;
    private static final int EMPTY =  3;
    private static final int BOTTOM = 4;

    private static final String TAG = "CosPlayAdapter";

    public CosPlayAdapter(List<Item> dataSet){
       super(dataSet);
    }

    public void setItems(Cosplay cosplay){
        dataSet.clear();
        addItems(cosplay);
    }

    public void addItems(Cosplay cosplay){
        for(CosDatum cosDatum: cosplay.getData()) {
            Item item = new Item();
            item.setType(NORMAL);
            item.setCosDatum(cosDatum);
            dataSet.add(item);
        }
        notifyDiff();
    }

    public void addEmpty(){
        dataSet.clear();
        Item item = new Item();
        item.setType(EMPTY);
        dataSet.add(item);
        notifyDiff();
    }

    public void addFooter(){
        Item item = new Item();
        item.setType(FOOTER);
        dataSet.add(item);
        notifyDiff();
    }

    public void removeFooter(){
        dataSet.remove(dataSet.size()-1);
        notifyDiff();
    }

    public void addBottom(){
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
            return new CosPlayViewHolder(view);
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
        L.i(TAG,"bind data: " + position);
        if(holder instanceof CosPlayViewHolder){
            ((CosPlayViewHolder) holder).bindData(dataSet.get(position).getCosDatum());
        }
    }

    @Override
    protected boolean areItemsTheSame(Item oldItem, Item newItem) {
        if(oldItem.getType() != newItem.getType()) {
            return false;
        } else if(oldItem.getType() == NORMAL){
            return oldItem.getCosDatum().getSort().equals(newItem.getCosDatum().getSort());
        }else{
            return true;
        }
    }

    @Override
    protected boolean areContentsTheSame(Item oldItem, Item newItem) {
        if(oldItem.getType() == NORMAL){
            return oldItem.getCosDatum().getThumbnail().equals(newItem.getCosDatum().getThumbnail());
        }
        return true;
    }


    @Override
    public int getItemViewType(int position) {
        return dataSet.get(position).getType();
    }

    static class Item{

        private int type;
        private CosDatum cosDatum;

        int getType() {
            return type;
        }

        void setType(int type) {
            this.type = type;
        }

        CosDatum getCosDatum() {
            return cosDatum;
        }

        void setCosDatum(CosDatum cosDatum) {
            this.cosDatum = cosDatum;
        }
    }
}
