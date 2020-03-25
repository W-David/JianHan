package com.example.jianhan.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.example.jianhan.BuildConfig;
import com.example.jianhan.di.component.ApplicationComponent;
import com.example.jianhan.di.component.DaggerApplicationComponent;
import com.example.jianhan.di.module.ApplicationModule;
import com.example.jianhan.di.module.NetworkModule;
import com.tbruyelle.rxpermissions2.RxPermissions;

public class JianHanApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private ApplicationComponent applicationComponent;

    public static Context getContext(){
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        setupInjector();
//        setStrictMode();
    }

    private void setupInjector(){
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(this))
                .build();
    }

    private void setStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
        }
    }

    public ApplicationComponent getApplicationComponent(){return applicationComponent;}
}
