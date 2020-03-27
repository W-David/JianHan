package com.example.jianhan.presenter;

import com.example.jianhan.app.Constants;
import com.example.jianhan.contract.QueryContract;
import com.example.jianhan.model.bean.MoeImg;
import com.example.jianhan.model.net.http.FetchMoreQueryUseCase;
import com.example.jianhan.util.L;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class QueryPresenter implements QueryContract.Presenter {

    private static final String TAG = "QueryPresenter";
    private FetchMoreQueryUseCase moreQueryUseCase;
    private QueryContract.View view;
    private CompositeDisposable compositeDisposable;
    public QueryPresenter(FetchMoreQueryUseCase moreQueryUseCase){
        this.moreQueryUseCase = moreQueryUseCase;
    }
    @Override
    public void refresh(String queryString) {
        view.showRefreshing();
        moreQueryUseCase.setQuery(queryString);
        moreQueryUseCase.initOffset();
        moreQueryUseCase.execute()
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
                        L.i(TAG,"query size: " + moeImg.getData().size());
                        if(moeImg.getData().size() == 0){
                            view.showEmptyResult();
                        }else if(moeImg.getData().size() < Constants.HTTP.DEFAULT_NUM){
                            view.showQueryImg(moeImg);
                            view.showBottom();
                        }else{
                            view.showQueryImg(moeImg);
                            view.showLoadingMore();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showError("search error");
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
        moreQueryUseCase.offsetPlus();
        moreQueryUseCase.execute()
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
                        view.hideLoadingMore();
                        if(moeImg.getData().size() == 0){
                            view.showBottom();
                        }else if(moeImg.getData().size() < Constants.HTTP.DEFAULT_NUM){
                            view.appendQueryImg(moeImg);
                            view.showBottom();
                        }else{
                            view.appendQueryImg(moeImg);
                            view.showLoadingMore();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        L.e(TAG,e.toString());
                    }

                    @Override
                    public void onComplete() {
                        L.i(TAG,"loading successfully");
                    }
                });
    }

    @Override
    public void attachView(QueryContract.View view) {
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
