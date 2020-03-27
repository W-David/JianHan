package com.example.jianhan.ui.activity;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.jianhan.R;
import com.example.jianhan.app.JianHanApplication;
import com.example.jianhan.contract.QueryContract;
import com.example.jianhan.di.component.DaggerQueryComponent;
import com.example.jianhan.di.module.ActivityModule;
import com.example.jianhan.di.module.QueryModule;
import com.example.jianhan.model.bean.MoeImg;
import com.example.jianhan.ui.adapter.QueryImgAdapter;
import com.example.jianhan.widget.LoadMoreRecyclerView;
import com.google.android.material.button.MaterialButton;
import com.jaeger.library.StatusBarUtil;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QueryActivity extends BaseActivity implements
        MaterialSearchBar.OnSearchActionListener,
        QueryContract.View,
        View.OnClickListener{

    @Inject
    QueryContract.Presenter queryPresenter;
    @BindView(R.id.search_bar)
    MaterialSearchBar searchBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.query_progressbar)
    ProgressBar progressBar;
    @BindView(R.id.query_recyclerview)
    LoadMoreRecyclerView recyclerView;
    @BindView(R.id.perv_show_text)
    TextView textView;


    private QueryImgAdapter adapter;
    private static String prevSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        StatusBarUtil.setTransparent(this);
        ButterKnife.bind(this);
        // toolbar setting
        setToolbar(toolbar);
        setSupportActionBarTitle("搜索");
        injectDependencies(); // bind view and presenter
        queryPresenter.attachView(this);
        // adapter
        adapter = new QueryImgAdapter(new ArrayList<>());
        //search bar setting
        searchBar.setOnSearchActionListener(this);
        searchBar.setCardViewElevation(12);
        // recyclerview setting
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
        recyclerView.setLoadMoreListener(() -> {
            recyclerView.setLoadingMore(true);
            queryPresenter.loadMore();
            recyclerView.setLoadingMore(false);
        });
    }


    private void injectDependencies(){
        DaggerQueryComponent.builder()
                .applicationComponent(((JianHanApplication) getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .queryModule(new QueryModule())
                .build()
                .inject(this);
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {
        if(!enabled){
            Toast.makeText(this,"要走了么..",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        if(text.length() == 0){
            Toast.makeText(this,"你倒是给点东西啊w(ﾟДﾟ)w",Toast.LENGTH_SHORT).show();
        }else{
            if(prevSearch != text.toString()){
                queryPresenter.refresh(text.toString());
            }
            prevSearch = text.toString();
        }
    }

    @Override
    public void onButtonClicked(int buttonCode) {
        if(buttonCode == MaterialSearchBar.BUTTON_BACK){
            searchBar.closeSearch();
        }
    }

    @Override
    public void showRefreshing() {
        Runnable runnable = () ->{
            textView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        };
        runOnUiThread(runnable);
    }

    @Override
    public void hideRefreshing() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingMore() {
        adapter.addFooter();
    }

    @Override
    public void hideLoadingMore() {
        adapter.removeFooter();
    }

    @Override
    public void showQueryImg(MoeImg moeImg) {
        adapter.setItems(moeImg);
    }

    @Override
    public void appendQueryImg(MoeImg moeImg) {
        adapter.addItems(moeImg);
    }

    @Override
    public void showEmptyResult() {
        adapter.addEmpty();
    }

    @Override
    public void showBottom() {
        adapter.addBottom();
    }

    @Override
    public void showError(String ErrorString) {
        Toast.makeText(this,ErrorString,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

    }
}
