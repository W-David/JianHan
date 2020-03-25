package com.example.jianhan.di.module;

import com.example.jianhan.contract.MoeImgContract;
import com.example.jianhan.model.net.http.FetchMoreMoeImgUseCase;
import com.example.jianhan.model.net.repository.Repository;
import com.example.jianhan.presenter.MoeImgPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MoeImgModule {

    @Provides
    FetchMoreMoeImgUseCase provideFetchMoreMoeImgUseCase(Repository repository){
        return new FetchMoreMoeImgUseCase(repository);
    }

    @Provides
    MoeImgContract.Presenter provideMoeImgPresenter(FetchMoreMoeImgUseCase moreMoeImgUseCase){
        return new MoeImgPresenter(moreMoeImgUseCase);
    }
}
