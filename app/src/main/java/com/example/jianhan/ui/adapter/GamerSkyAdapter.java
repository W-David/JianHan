package com.example.jianhan.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jianhan.R;
import com.example.jianhan.model.bean.GamerDatum;
import com.example.jianhan.model.bean.GamerSky;
import com.example.jianhan.ui.adapter.holder.BottomViewHolder;
import com.example.jianhan.ui.adapter.holder.FooterViewHolder;
import com.example.jianhan.ui.adapter.holder.GamerSkyViewHolder;

import java.util.ArrayList;
import java.util.List;

public class GamerSkyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int NORMAL = 1;
    private static final int FOOTER = 2;
    private static final int BOTTOM = 3;

    private List<Item> items;

    public GamerSkyAdapter(){
        items = new ArrayList<>();
    }

    public void setItems(GamerSky gamerSky){
        int prevSize = items.size();
        items.clear();
        notifyItemRangeRemoved(0,prevSize);
        addItems(gamerSky);
    }

    public void addItems(GamerSky gamerSky){
        int startPosition = items.size();
        for(GamerDatum gamerDatum: gamerSky.getData()){
            Item item = new Item();
            item.setType(NORMAL);
            item.setGamerDatum(gamerDatum);
            items.add(item);
        }
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
            return new GamerSkyViewHolder(view);
        }else if(viewType == FOOTER){
            View view = LayoutInflater.from(parent.getContext()) .inflate(R.layout.recycler_footer,parent,false);
            return new FooterViewHolder(view);
        }else{
            View view = LayoutInflater.from(parent.getContext()) .inflate(R.layout.fragment_recycler_bottom,parent,false);
            return new BottomViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof GamerSkyViewHolder){
            ((GamerSkyViewHolder) holder).bindData(items.get(position).getGamerDatum());
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
        private GamerDatum gamerDatum;

        int getType() {
            return type;
        }

        void setType(int type) {
            this.type = type;
        }

        GamerDatum getGamerDatum() {
            return gamerDatum;
        }

        void setGamerDatum(GamerDatum gamerDatum) {
            this.gamerDatum = gamerDatum;
        }
    }
}
