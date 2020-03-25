package com.example.jianhan.di.module;

import com.example.jianhan.contract.GamerSkyContract;
import com.example.jianhan.model.net.http.FetchMoreGamerSkyUseCase;
import com.example.jianhan.model.net.repository.Repository;
import com.example.jianhan.presenter.GamerSkyPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class GamerSkyModule {

    @Provides
    FetchMoreGamerSkyUseCase provideFetchMoreGamerSkyUseCase(Repository repository){
        return new FetchMoreGamerSkyUseCase(repository);
    }

    @Provides
    GamerSkyContract.Presenter provideGamerSkyPresenter(FetchMoreGamerSkyUseCase moreGamerSkyUseCase){
        return new GamerSkyPresenter(moreGamerSkyUseCase);
    }
}
