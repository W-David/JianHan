package com.example.jianhan.di.module;

import com.example.jianhan.contract.DetailContract;
import com.example.jianhan.presenter.DetailPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailModule {

    @Provides
    DetailContract.Presenter provideDetailPresenter(){
        return new DetailPresenter();
    }
}
