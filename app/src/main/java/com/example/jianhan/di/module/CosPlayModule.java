package com.example.jianhan.di.module;

import com.example.jianhan.contract.CosPlayContract;
import com.example.jianhan.model.net.http.FetchMoreCosPlayUseCase;
import com.example.jianhan.model.net.repository.Repository;
import com.example.jianhan.presenter.CosPlayPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class CosPlayModule {

    @Provides
    FetchMoreCosPlayUseCase provideFetchMoreCosplayUsecase(Repository repository){
        return new FetchMoreCosPlayUseCase(repository);
    }

    @Provides
    CosPlayContract.Presenter provideCosplayPresenter(FetchMoreCosPlayUseCase moreCosplayUsecase){
        return new CosPlayPresenter(moreCosplayUsecase);
    }
}
