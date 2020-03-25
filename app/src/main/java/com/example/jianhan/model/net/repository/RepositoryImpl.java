package com.example.jianhan.model.net.repository;

import com.example.jianhan.model.bean.Cosplay;
import com.example.jianhan.model.bean.GamerSky;
import com.example.jianhan.model.bean.MoeImg;
import com.example.jianhan.model.net.api.ApiService;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class RepositoryImpl implements Repository{

    private ApiService apiService;

    public RepositoryImpl(Retrofit retrofit) {apiService = retrofit.create(ApiService.class);}

    @Override
    public Observable<MoeImg> getMoeImg(Integer offset) {
        return apiService.getMoeImg(offset);
    }

    @Override
    public Observable<Cosplay> getCosPlay(Integer offset) {
        return apiService.getCosplay(offset);
    }

    @Override
    public Observable<GamerSky> getGamerSky(Integer offset) {
        return apiService.getGamerSky(offset);
    }

    @Override
    public Observable<MoeImg> getQuery(String query, Integer offset) {
        return apiService.getQuery(query,offset);
    }
}
