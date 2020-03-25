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
import com.example.jianhan.ui.adapter.holder.FooterViewHolder;
import com.example.jianhan.util.L;

import java.util.ArrayList;
import java.util.List;

public class CosPlayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int NORMAL = 1;
    private static final int FOOTER = 2;
    private static final int BOTTOM = 3;

    private static final String TAG = "CosPlayAdapter";
    private List<Item> items;

    public CosPlayAdapter(){
        items = new ArrayList<>();
    }

    public void setItems(Cosplay cosplay){
        int prevSize = items.size();
        items.clear();
        notifyItemRangeRemoved(0,prevSize);
        addItems(cosplay);
    }

    public void addItems(Cosplay cosplay){
        int startPosition = items.size();
        for(CosDatum cosDatum: cosplay.getData()) {
            Item item = new Item();
            item.setType(NORMAL);
            item.setCosDatum(cosDatum);
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
        L.i(TAG,"add footer,cur size: " + items.size());
        notifyItemInserted(items.size() - 1);
    }

    public void removeFooter(){
        items.remove(items.size() - 1);
        L.i(TAG,"remove footer,cur size: " + items.size());
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
            return new CosPlayViewHolder(view);
        }else if(viewType == FOOTER){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_recycler_footer,parent,false);
            return new FooterViewHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_recycler_bottom,parent,false);
            return new BottomViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        L.i(TAG,"bind data: " + position);
        if(holder instanceof CosPlayViewHolder){
            ((CosPlayViewHolder) holder).bindData(items.get(position).getCosDatum());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
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
