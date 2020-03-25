package com.example.jianhan.di.module;

import android.app.Activity;
import android.content.Context;

import com.example.jianhan.di.scope.PreActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @PreActivity
    Context provideContext(){
        return mActivity;
    }
}
