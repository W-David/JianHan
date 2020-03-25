package com.example.jianhan.di.module;

import com.example.jianhan.contract.QueryContract;
import com.example.jianhan.model.net.http.FetchMoreQueryUseCase;
import com.example.jianhan.model.net.repository.Repository;
import com.example.jianhan.presenter.QueryPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class QueryModule {

    @Provides
    FetchMoreQueryUseCase provideFetchMoreQueryUseCase(Repository repository){
        return new FetchMoreQueryUseCase(repository);
    }

    @Provides
    QueryContract.Presenter provideQueryPresenter(FetchMoreQueryUseCase moreQueryUseCase){
        return new QueryPresenter(moreQueryUseCase);
    }
}
