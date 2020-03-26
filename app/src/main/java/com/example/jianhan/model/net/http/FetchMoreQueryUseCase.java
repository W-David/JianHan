package com.example.jianhan.model.net.http;

import com.example.jianhan.base.BaseUseCase;
import com.example.jianhan.model.bean.MoeImg;
import com.example.jianhan.model.net.repository.Repository;

import io.reactivex.Observable;

public class FetchMoreQueryUseCase implements BaseUseCase<MoeImg> {
    private Repository repository;

    private String query;

    private Integer offset;

    public FetchMoreQueryUseCase(Repository repository){
        this.repository = repository;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void initOffset() { offset = 1;}
    public void offsetPlus() { this.offset++;}

    @Override
    public Observable<MoeImg> execute() {
        return repository.getQuery(query,offset);
    }
}
