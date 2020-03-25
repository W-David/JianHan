package com.example.jianhan.presenter;

import com.example.jianhan.contract.CosPlayContract;
import com.example.jianhan.model.bean.Cosplay;
import com.example.jianhan.model.net.http.FetchMoreCosPlayUseCase;
import com.example.jianhan.util.L;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CosPlayPresenter implements CosPlayContract.Presenter {

    private static final String TAG = "CosplayPresenter";

    private FetchMoreCosPlayUseCase moreCosPlayUsecase;
    private CosPlayContract.View view;
    private CompositeDisposable compositeDisposable;

    public CosPlayPresenter(FetchMoreCosPlayUseCase moreCosPlayUsecase){
        this.moreCosPlayUsecase  = moreCosPlayUsecase;
    }
    @Override
    public void refresh() {
        view.showRefreshing();
        moreCosPlayUsecase.initOffset();
        moreCosPlayUsecase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Cosplay>(){

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull Cosplay cosplay) {
                        L.i(TAG,cosplay.getMessage());
                        view.showCosPlay(cosplay);
                        view.showLoadingMore();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showError(e.toString());
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
        moreCosPlayUsecase.offsetPlus();
        moreCosPlayUsecase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Cosplay>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull Cosplay cosplay) {
                        L.i(TAG,cosplay.getData().toString() + "something out");
                        view.hideLoadingMore();
                        view.appendCosPlay(cosplay);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showError("（o´・ェ・｀o）");
                        L.e(TAG,e.toString());
                    }

                    @Override
                    public void onComplete() {
                        view.showLoadingMore();
                        L.i(TAG,"loadMore successfully");
                    }
                });
    }

    @Override
    public void attachView(CosPlayContract.View view) {
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
