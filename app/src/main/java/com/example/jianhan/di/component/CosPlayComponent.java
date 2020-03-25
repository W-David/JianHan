package com.example.jianhan.di.component;

import com.example.jianhan.di.module.ActivityModule;
import com.example.jianhan.di.module.CosPlayModule;
import com.example.jianhan.di.scope.PreActivity;
import com.example.jianhan.ui.fragment.CosPlayFragment;

import dagger.Component;

@PreActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class, CosPlayModule.class})
public interface CosPlayComponent {
    void inject(CosPlayFragment cosPlayFragment);
}
