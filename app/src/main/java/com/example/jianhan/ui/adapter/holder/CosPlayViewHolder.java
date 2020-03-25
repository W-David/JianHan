package com.example.jianhan.ui.adapter.holder;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jianhan.R;
import com.example.jianhan.model.bean.CosDatum;
import com.example.jianhan.util.IntentUtil;

public class CosPlayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private CardView cardView;
    private ImageView thumbnail;
    private TextView title;
    private CosDatum cosDatum;
    private Context context;

    public CosPlayViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.thumb_card_view);
        thumbnail = itemView.findViewById(R.id.thumbnail);
        title = itemView.findViewById(R.id.image_title);
        cardView.setOnClickListener(this);
        context = itemView.getContext();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.thumb_card_view){
            IntentUtil.intentToDetailActivity((Activity) v.getContext(),cosDatum);
        }
    }

    public void bindData(CosDatum cosDatum){
        this.cosDatum = cosDatum;
        title.setText(cosDatum.getTitle());
        Glide.with(context).load(cosDatum.getThumbnail()).into(thumbnail);
    }
}
