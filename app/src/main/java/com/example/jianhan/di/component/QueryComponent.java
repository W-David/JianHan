package com.example.jianhan.di.component;


import com.example.jianhan.di.module.ActivityModule;
import com.example.jianhan.di.module.QueryModule;
import com.example.jianhan.di.scope.PreActivity;
import com.example.jianhan.ui.activity.QueryActivity;

import dagger.Component;

@PreActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class, QueryModule.class})
public interface QueryComponent {
    void inject(QueryActivity queryActivity);
}
