package com.example.jianhan.di.component;

import com.example.jianhan.di.module.ActivityModule;
import com.example.jianhan.di.module.MoeImgModule;
import com.example.jianhan.di.scope.PreActivity;
import com.example.jianhan.ui.fragment.MoeImgFragment;

import dagger.Component;

@PreActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class, MoeImgModule.class})
public interface MoeImgComponent {
    void inject(MoeImgFragment moeImgFragment);
}
