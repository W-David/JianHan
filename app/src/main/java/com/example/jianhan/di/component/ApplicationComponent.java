package com.example.jianhan.di.component;

import android.app.Application;

import com.example.jianhan.app.JianHanApplication;
import com.example.jianhan.di.module.ApplicationModule;
import com.example.jianhan.di.module.NetworkModule;
import com.example.jianhan.di.scope.PreApplication;
import com.example.jianhan.model.net.repository.Repository;

import dagger.Component;

@PreApplication
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    JianHanApplication jianHanApplication();

    Application application();

    Repository repository();
}
