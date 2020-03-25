package com.example.jianhan.model.net.repository;

import com.example.jianhan.model.bean.Cosplay;
import com.example.jianhan.model.bean.GamerSky;
import com.example.jianhan.model.bean.MoeImg;

import io.reactivex.Observable;


public interface Repository {


    Observable<MoeImg> getMoeImg(Integer offset);

    Observable<Cosplay> getCosPlay(Integer offset);

    Observable<GamerSky> getGamerSky(Integer offset);

    Observable<MoeImg> getQuery(String query, Integer offset);
}
