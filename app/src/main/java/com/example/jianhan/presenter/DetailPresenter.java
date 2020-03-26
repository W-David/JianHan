package com.example.jianhan.presenter;


import com.example.jianhan.contract.DetailContract;
import com.example.jianhan.util.L;


public class DetailPresenter implements DetailContract.Presenter {

    private static final String TAG = "MoeDetailPresenter";
    private DetailContract.View view;

    @Override
    public void refresh() {
        view.initDetailImg();
        view.hideProgressBar();
    }

    @Override
    public void attachView(DetailContract.View view) {
        this.view  = view;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }
}
