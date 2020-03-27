package com.example.jianhan.contract;

import com.example.jianhan.base.BasePresenter;
import com.example.jianhan.base.BaseView;
import com.example.jianhan.model.bean.GamerSky;

public interface GamerSkyContract {
    interface View extends BaseView {

        void hideRefreshing();

        void showRefreshing();

        void hideLoadingMore();

        void showLoadingMore();

        void showGamerSky(GamerSky gamerSky);

        void appendGamerSky(GamerSky gamerSky);

        void showEmptyResult();

        void showBottom();
    }

    interface Presenter extends BasePresenter<View> {

        void refresh();

        void loadMore();
    }
}
