package com.example.jianhan.model.net.http;

import com.example.jianhan.base.BaseUseCase;
import com.example.jianhan.model.bean.MoeImg;
import com.example.jianhan.model.net.repository.Repository;

import io.reactivex.Observable;


public class FetchMoreMoeImgUseCase implements BaseUseCase<MoeImg> {

    private Repository mRepository;

    private Integer offset;

    public FetchMoreMoeImgUseCase(Repository repository){this.mRepository = repository;}

    public void initOffset() { offset = 1;}
    public void offsetPlus() { this.offset++;}

    @Override
    public Observable<MoeImg> execute() {
        return mRepository.getMoeImg(offset);
    }
}
