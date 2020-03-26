package com.example.jianhan.contract;

import com.example.jianhan.base.BasePresenter;
import com.example.jianhan.base.BaseView;
import com.example.jianhan.model.bean.MoeImg;

public interface QueryContract {

    interface View extends BaseView{

        void showRefreshing();

        void hideRefreshing();

        void showLoadingMore();

        void hideLoadingMore();

        void showQueryImg(MoeImg moeImg);

        void appendQueryImg(MoeImg moeImg);

        void showEmptyResult();

        void showBottom();
    }

    interface Presenter extends BasePresenter<View>{

        void refresh(String queryString);

        void loadMore();
    }
}
