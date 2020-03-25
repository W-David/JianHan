package com.example.jianhan.presenter;

import android.util.Log;

import com.example.jianhan.contract.MoeImgContract;
import com.example.jianhan.model.bean.MoeImg;
import com.example.jianhan.model.net.http.FetchMoreMoeImgUseCase;
import com.example.jianhan.util.L;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MoeImgPresenter implements MoeImgContract.Presenter {

    private static final String TAG = "MoeImgPresenter";
    private FetchMoreMoeImgUseCase moreMoeImgUseCase;
    private MoeImgContract.View view;
    private CompositeDisposable compositeDisposable;
    public MoeImgPresenter(FetchMoreMoeImgUseCase moreMoeImgUseCase) {
        this.moreMoeImgUseCase = moreMoeImgUseCase;
    }

    @Override
    public void refresh() {
        view.showRefreshing();
        moreMoeImgUseCase.initOffset();
        moreMoeImgUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoeImg>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull MoeImg moeImg) {
                        L.i(TAG,moeImg.getMessage());
                        view.showMoeImg(moeImg);
                        view.showLoadingMore();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showError("(´･ω･`)?");
                        L.e(TAG,e.toString());
                    }

                    @Override
                    public void onComplete() {
                        view.hideRefreshing();
                        L.i(TAG,"refreshing successfully!");
                    }
                });
    }

    @Override
    public void loadMore() {
        moreMoeImgUseCase.offsetPlus();
        moreMoeImgUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoeImg>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull MoeImg moeImg) {
                        view.hideLoadingMore();
                        view.appendMoeImg(moeImg);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showError("(￣m￣）");
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
    public void attachView(MoeImgContract.View view) {
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
