package com.example.jianhan.base;

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void onCreate();

    void onStart();

    void onPause();

    void onStop();
}
