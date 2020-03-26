package com.example.jianhan.di.module;

import com.example.jianhan.app.JianHanApplication;
import com.example.jianhan.app.Constants;
import com.example.jianhan.di.scope.PreApplication;
import com.example.jianhan.model.net.repository.Repository;
import com.example.jianhan.model.net.repository.RepositoryImpl;
import com.example.jianhan.util.FileUtil;
import com.example.jianhan.util.interceptor.CacheInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private final JianHanApplication mJianHanApplication;

    public NetworkModule(JianHanApplication jianHanApplication) {
        this.mJianHanApplication = jianHanApplication;
    }

    @Provides
    @PreApplication
    Repository provideRepository(Retrofit retrofit) {
        return new RepositoryImpl(retrofit);
    }

    @Provides
    @PreApplication
    Retrofit provideRetrofit(){
        String BaseUrl = "https://rabtman.com/api/v2/acgclub/";
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(new Cache(FileUtil.getHttpCacheDir(mJianHanApplication), Constants.CacheConfig.HTTP_CACHE_SIZE))
                .connectTimeout(Constants.CacheConfig.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(Constants.CacheConfig.HTTP_READ_TIMEOUT,TimeUnit.MILLISECONDS)
                .addInterceptor(new CacheInterceptor())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }
}
