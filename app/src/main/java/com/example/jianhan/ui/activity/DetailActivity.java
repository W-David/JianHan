package com.example.jianhan.ui.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.jianhan.R;
import com.example.jianhan.app.JianHanApplication;
import com.example.jianhan.contract.DetailContract;
import com.example.jianhan.di.component.ApplicationComponent;
import com.example.jianhan.di.component.DaggerDetailComponent;
import com.example.jianhan.di.module.ActivityModule;
import com.example.jianhan.di.module.DetailModule;
import com.example.jianhan.model.bean.CosDatum;
import com.example.jianhan.model.bean.GamerDatum;
import com.example.jianhan.model.bean.Info;
import com.example.jianhan.model.bean.MoeDatum;
import com.example.jianhan.ui.adapter.DetailAdapter;
import com.example.jianhan.util.IntentUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity
        implements DetailContract.View ,View.OnClickListener{

    @Inject
    DetailContract.Presenter detailPresenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.thumbnail_detail)
    ImageView thumbnailDetail;
    @BindView(R.id.fab_detail_info)
    FloatingActionButton fabDetail;
    @BindView(R.id.detail_recycler_view)
    RecyclerView recyclerView;


    private Parcelable parcelable;
    private DetailAdapter detailAdapter;
    private Info info;
    private boolean hasMore;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setToolbar(toolbar);
        injectDependencies();
        detailPresenter.attachView(this);
        // attach intent
        Intent intent = getIntent();
        parcelable = intent.getParcelableExtra(IntentUtil.EXTRA_DETAIL);
        // init recyclerview
        detailAdapter = new DetailAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(detailAdapter);
        progressDialog = new ProgressDialog(this);
        handlerDetailData();
        //is show detail button
        if(!hasMore) fabDetail.setVisibility(View.INVISIBLE);
        else fabDetail.setOnClickListener(this);
        // handler intent data, init title and thumbnail
        // load detail image
        detailPresenter.refresh();
    }

    private void injectDependencies(){
        ApplicationComponent applicationComponent = ((JianHanApplication) getApplication()).getApplicationComponent();
        DaggerDetailComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(new ActivityModule(this))
                .detailModule(new DetailModule())
                .build()
                .inject(this);
    }
    @Override
    public void hideProgressBar() {
        Runnable runnable = () -> progressDialog.dismiss();
        runOnUiThread(runnable);
    }

    @Override
    public void showProgressBar() {
        Runnable runnable = () -> {
            progressDialog.setMessage("loading");
            progressDialog.setCancelable(false);
            progressDialog.show();
        };
        runOnUiThread(runnable);
    }

    public void handlerDetailData(){
        info = new Info();
        if(parcelable instanceof MoeDatum){
            hasMore = false;
            MoeDatum moeDatum = (MoeDatum) parcelable;
            info.setTitle(moeDatum.getTitle());
            info.setThumbnail(moeDatum.getThumbnail());
            info.setImgUrls(moeDatum.getImgUrls());
        }else if(parcelable instanceof CosDatum){
            hasMore = true;
            CosDatum cosDatum  = (CosDatum) parcelable;
            info.setTitle(cosDatum.getTitle());
            info.setThumbnail(cosDatum.getThumbnail());
            info.setImgUrls(cosDatum.getImgUrls());
            info.setAuthor(cosDatum.getAuthor());
            info.setEditor(cosDatum.getEditor());
            info.setDateTime(cosDatum.getDatetime());
            info.setSource(cosDatum.getSource());
        }else{
            hasMore = false;
            GamerDatum gamerDatum = (GamerDatum) parcelable;
            info.setTitle(gamerDatum.getTitle());
            info.setThumbnail(gamerDatum.getThumbnail());
            info.setImgUrls(gamerDatum.getImgUrls());
        }
    }
    @Override
    public void initDetailImg() {
        Runnable runnable = () ->
            setSupportActionBarTitle(info.getTitle());
            Glide.with(this).load(info.getThumbnail()).into(thumbnailDetail);
            detailAdapter.setItems(info.getImgUrls());
        runOnUiThread(runnable);
    }

    @Override
    public void showError(String ErrorString) {

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fab_detail_info){
            new AlertDialog.Builder(this)
                    .setTitle(info.getTitle())
                    .setMessage(info.toString())
                    .setPositiveButton("OK", null)
                    .show();
        }
    }
}
