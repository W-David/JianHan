package com.example.jianhan.ui.adapter.holder;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.example.jianhan.R;
import com.example.jianhan.model.bean.MoeDatum;
import com.example.jianhan.util.IntentUtil;

public class QueryImgViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private CardView cardView;
    private ImageView imageView;
    private MoeDatum moeDatum;
    private Context context;

    public QueryImgViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.search_thumb_card_view);
        imageView = itemView.findViewById(R.id.search_item_thumbnail);
        this.context = itemView.getContext();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.search_thumb_card_view){
            IntentUtil.intentToDetailActivity((Activity) v.getContext(), moeDatum);
        }
    }

    public void bindData(MoeDatum moeDatum){
        this.moeDatum = moeDatum;
        DrawableCrossFadeFactory fadeFactory = new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();
        Glide.with(context)
                .load(moeDatum.getThumbnail())
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade(fadeFactory))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.color.white)
                .into(imageView);
    }

}
