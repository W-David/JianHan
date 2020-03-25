package com.example.jianhan.model.net.api;

import com.example.jianhan.model.bean.Cosplay;
import com.example.jianhan.model.bean.GamerSky;
import com.example.jianhan.model.bean.MoeImg;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("category/moeimg/pictures")
    Observable<MoeImg> getMoeImg(@Query("offset") Integer offset);

    @GET("category/gamersky/pictures")
    Observable<GamerSky> getGamerSky(@Query("offset") Integer offset);

    @GET("category/cosplay/pictures")
    Observable<Cosplay> getCosplay(@Query("offset") Integer offset);

    @GET("pictures")
    Observable<MoeImg> getQuery(@Query("query") String query, @Query("offset") Integer offset);
}
