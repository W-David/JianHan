package com.example.jianhan.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jianhan.R;
import com.example.jianhan.app.JianHanApplication;
import com.example.jianhan.contract.GamerSkyContract;
import com.example.jianhan.di.component.DaggerGamerSkyComponent;
import com.example.jianhan.di.module.ActivityModule;
import com.example.jianhan.di.module.GamerSkyModule;
import com.example.jianhan.model.bean.GamerSky;
import com.example.jianhan.ui.adapter.GamerSkyAdapter;
import com.example.jianhan.widget.LoadMoreRecyclerView;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GamerSkyFragment extends Fragment implements GamerSkyContract.View {

    private static final String TAG = "GamerSkyFragment";
    @Inject
    GamerSkyContract.Presenter gamerSkyPresenter;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.thumb_recycler_view)
    LoadMoreRecyclerView recyclerView;

    private GamerSkyAdapter adapter;
    private void injectDependencies(){
        DaggerGamerSkyComponent.builder()
                .applicationComponent(((JianHanApplication) Objects.requireNonNull(getActivity()).getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(getActivity()))
                .gamerSkyModule(new GamerSkyModule())
                .build()
                .inject(this);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        gamerSkyPresenter.attachView(this);
        adapter = new GamerSkyAdapter();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark, android.R.color.holo_blue_light,
                android.R.color.holo_green_dark, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(() -> gamerSkyPresenter.refresh());
        recyclerView.setLoadMoreListener(() -> {
            recyclerView.setLoadingMore(true);
            gamerSkyPresenter.loadMore();
            recyclerView.setLoadingMore(false);
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeRefreshLayout.post(() -> gamerSkyPresenter.refresh());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void hideRefreshing() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showRefreshing() {
        Runnable runnable = () -> {
            swipeRefreshLayout.setRefreshing(true);
        };
        Objects.requireNonNull(getActivity()).runOnUiThread(runnable);
    }

    @Override
    public void hideLoadingMore() {
        adapter.removeFooter();
    }

    @Override
    public void showLoadingMore() {
        adapter.addFooter();
    }

    @Override
    public void showGamerSky(GamerSky gamerSky) {
        if(gamerSky.getData().size() == 0){
            adapter.addBottom();
        }else{
            adapter.setItems(gamerSky);
        }
    }

    @Override
    public void appendGamerSky(GamerSky gamerSky) {
        if(gamerSky.getData().size() == 0 ){
            adapter.addBottom();
        }else{
            adapter.addItems(gamerSky);
        }
    }

    @Override
    public void showError(String ErrorString) {
        Toast.makeText(getContext(), ErrorString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        gamerSkyPresenter.onStop();
        super.onDestroy();
    }

}
