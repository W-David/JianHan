package com.example.jianhan.contract;

import com.example.jianhan.base.BasePresenter;
import com.example.jianhan.base.BaseView;
import com.example.jianhan.model.bean.Cosplay;

public interface CosPlayContract {

    interface View extends BaseView {

        void hideRefreshing();

        void showRefreshing();

        void hideLoadingMore();

        void showLoadingMore();

        void showCosPlay(Cosplay cosplay);

        void appendCosPlay(Cosplay cosplay);
    }

    interface Presenter extends BasePresenter<View> {

        void refresh();

        void loadMore();
    }
}
