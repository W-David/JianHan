package com.example.jianhan.di.component;

import com.example.jianhan.di.module.ActivityModule;
import com.example.jianhan.di.module.DetailModule;
import com.example.jianhan.di.scope.PreActivity;
import com.example.jianhan.ui.activity.DetailActivity;

import dagger.Component;

@PreActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class, DetailModule.class})
public interface DetailComponent {
    void inject(DetailActivity detailActivity);
}
