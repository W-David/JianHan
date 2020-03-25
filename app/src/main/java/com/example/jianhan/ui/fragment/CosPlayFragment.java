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
import com.example.jianhan.contract.CosPlayContract;
import com.example.jianhan.di.component.ApplicationComponent;
import com.example.jianhan.di.component.DaggerCosPlayComponent;
import com.example.jianhan.di.module.ActivityModule;
import com.example.jianhan.di.module.CosPlayModule;
import com.example.jianhan.model.bean.Cosplay;
import com.example.jianhan.ui.adapter.CosPlayAdapter;
import com.example.jianhan.widget.LoadMoreRecyclerView;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CosPlayFragment extends Fragment implements CosPlayContract.View {


    @Inject
    CosPlayContract.Presenter cosplayPresenter;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.thumb_recycler_view)
    LoadMoreRecyclerView recyclerView;

    private CosPlayAdapter adapter;

    private void injectDependencies(){
        ApplicationComponent applicationComponent = ((JianHanApplication) Objects.requireNonNull(getActivity()).getApplication()).getApplicationComponent();
        DaggerCosPlayComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(new ActivityModule(getActivity()))
                .cosPlayModule(new CosPlayModule())
                .build()
                .inject(this);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        cosplayPresenter.attachView(this);
        adapter = new CosPlayAdapter();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) { super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark, android.R.color.holo_blue_light,
                android.R.color.holo_green_dark, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(() -> cosplayPresenter.refresh());
        recyclerView.setLoadMoreListener(() -> {
            recyclerView.setLoadingMore(true);
            cosplayPresenter.loadMore();
            recyclerView.setLoadingMore(false);
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeRefreshLayout.post(() -> cosplayPresenter.refresh());
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
        Runnable runnable = () -> swipeRefreshLayout.setRefreshing(true);
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
    public void showCosPlay(Cosplay cosplay) {
        if(cosplay.getData().size() == 0) {
            adapter.addBottom();
        }else{
            adapter.setItems(cosplay);
        }
    }

    @Override
    public void appendCosPlay(Cosplay cosplay) {
        if(cosplay.getData().size() == 0){
            adapter.addBottom();
        }else{
            adapter.addItems(cosplay);
        }
    }

    @Override
    public void showError(String ErrorString) {
        Toast.makeText(getContext(), ErrorString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        cosplayPresenter.onStop();
        super.onDestroy();
    }
}
