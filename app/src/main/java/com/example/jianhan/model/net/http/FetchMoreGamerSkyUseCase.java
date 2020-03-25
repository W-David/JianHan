package com.example.jianhan.model.net.http;

import com.example.jianhan.base.BaseUseCase;
import com.example.jianhan.model.bean.GamerSky;
import com.example.jianhan.model.net.repository.Repository;

import io.reactivex.Observable;


public class FetchMoreGamerSkyUseCase implements BaseUseCase<GamerSky> {

    private Repository repository;

    private Integer offset;

    public FetchMoreGamerSkyUseCase(Repository repository){
        this.repository = repository;
    }

    public void initOffset() { offset = 1;}
    public void offsetPlus() { this.offset++;}

    @Override
    public Observable<GamerSky> execute() {
        return repository.getGamerSky(offset);
    }
}
