package com.example.jianhan.model.net.http;

import com.example.jianhan.base.BaseUseCase;
import com.example.jianhan.model.bean.Cosplay;
import com.example.jianhan.model.net.repository.Repository;

import io.reactivex.Observable;


public class FetchMoreCosPlayUseCase implements BaseUseCase<Cosplay> {

    private Repository mRepository;

    private Integer offset;

    public FetchMoreCosPlayUseCase(Repository repository){this.mRepository = repository;}

    public void initOffset() { offset = 1;}
    public void offsetPlus() { this.offset++;}

    @Override
    public Observable<Cosplay> execute() {
        return mRepository.getCosPlay(offset);
    }
}
