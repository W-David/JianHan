package com.example.jianhan.di.component;

import com.example.jianhan.di.module.ActivityModule;
import com.example.jianhan.di.module.GamerSkyModule;
import com.example.jianhan.di.scope.PreActivity;
import com.example.jianhan.ui.fragment.GamerSkyFragment;

import dagger.Component;

@PreActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class, GamerSkyModule.class})
public interface GamerSkyComponent {
    void inject(GamerSkyFragment gamerSkyFragment);
}
