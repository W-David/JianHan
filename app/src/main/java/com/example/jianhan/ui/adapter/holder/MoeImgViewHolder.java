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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.example.jianhan.R;
import com.example.jianhan.model.bean.MoeDatum;
import com.example.jianhan.util.IntentUtil;
import com.example.jianhan.util.L;

public class MoeImgViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    private static final String TAG = "MoeImgViewHolder";
    private CardView cardView;
    private ImageView thumbnail;
    private TextView title;
    private MoeDatum meoDatum;
    private Context context;

    public MoeImgViewHolder(@NonNull View itemView) {
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
            L.i(TAG,"clicked: " + v.getId());
            IntentUtil.intentToDetailActivity((Activity) v.getContext(),meoDatum);
        }
    }

    public void bindData(MoeDatum moeDatum){
        this.meoDatum = moeDatum;
        title.setText(meoDatum.getTitle());
        DrawableCrossFadeFactory fadeFactory = new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();
        Glide.with(context)
                .load(moeDatum.getThumbnail())
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade(fadeFactory))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.color.white)
                .into(thumbnail);
    }
}
