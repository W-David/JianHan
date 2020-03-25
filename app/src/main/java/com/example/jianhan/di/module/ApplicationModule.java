package com.example.jianhan.di.module;

import android.app.Application;

import com.example.jianhan.app.JianHanApplication;
import com.example.jianhan.di.scope.PreApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final JianHanApplication mApplication;

    public ApplicationModule(JianHanApplication application) {
        this.mApplication = application;
    }

    @Provides
    @PreApplication
    JianHanApplication provideJianHanApplication(){
        return mApplication;
    }

    @Provides
    @PreApplication
    Application provideApplication(){
        return mApplication;
    }
}
