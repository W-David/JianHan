package com.example.jianhan.presenter;

import com.example.jianhan.app.Constants;
import com.example.jianhan.contract.GamerSkyContract;
import com.example.jianhan.model.bean.GamerSky;
import com.example.jianhan.model.net.http.FetchMoreGamerSkyUseCase;
import com.example.jianhan.util.L;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GamerSkyPresenter implements GamerSkyContract.Presenter {

    private static final String TAG = "GamerSkyPresenter";
    private FetchMoreGamerSkyUseCase moreGamerSkyUseCase;
    private GamerSkyContract.View view;
    private CompositeDisposable compositeDisposable;

    public GamerSkyPresenter(FetchMoreGamerSkyUseCase moreGamerSkyUseCase){
        this.moreGamerSkyUseCase = moreGamerSkyUseCase;
    }
    @Override
    public void refresh() {
        view.showRefreshing();
        moreGamerSkyUseCase.initOffset();
        moreGamerSkyUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GamerSky>(){

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull GamerSky gamerSky) {
                        L.i(TAG,gamerSky.getData().toString());
                        if(gamerSky.getData().size() == 0){
                            view.showEmptyResult();
                        }else if(gamerSky.getData().size() < Constants.HTTP.DEFAULT_NUM){
                            view.showGamerSky(gamerSky);
                            view.showBottom();
                        }else{
                            view.showGamerSky(gamerSky);
                            view.showLoadingMore();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        L.e(TAG,e.toString());
                    }

                    @Override
                    public void onComplete() {
                        view.hideRefreshing();
                        L.i(TAG,"refresh successfully");
                    }
                });
    }

    @Override
    public void loadMore() {
        moreGamerSkyUseCase.offsetPlus();
        moreGamerSkyUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GamerSky>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull GamerSky gamerSky) {
                        view.hideLoadingMore();
                        if(gamerSky.getData().size() == 0 ){
                            view.showBottom();
                        }else if(gamerSky.getData().size() < Constants.HTTP.DEFAULT_NUM){
                            view.appendGamerSky(gamerSky);
                            view.showBottom();
                        }else{
                            view.appendGamerSky(gamerSky);
                            view.showLoadingMore();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showError(e.toString());
                        L.e(TAG,e.toString());
                    }

                    @Override
                    public void onComplete() {
                        L.i(TAG,"loadMore successfully");
                    }
                });
    }

    @Override
    public void attachView(GamerSkyContract.View view) {
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {
        compositeDisposable.dispose();
    }
}
