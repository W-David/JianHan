package com.example.jianhan.ui.adapter.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jianhan.R;

public class FooterViewHolder extends RecyclerView.ViewHolder {

    private LinearLayout loadingLayout;

    public FooterViewHolder(@NonNull View itemView) {
        super(itemView);
        loadingLayout = itemView.findViewById(R.id.loading);
    }
}
