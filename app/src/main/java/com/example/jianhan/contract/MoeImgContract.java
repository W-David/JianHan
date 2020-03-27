package com.example.jianhan.contract;

import com.example.jianhan.base.BasePresenter;
import com.example.jianhan.base.BaseView;
import com.example.jianhan.model.bean.MoeImg;

public interface MoeImgContract {

    interface View extends BaseView{

        void hideRefreshing();

        void showRefreshing();

        void hideLoadingMore();

        void showLoadingMore();

        void showMoeImg(MoeImg moeImg);

        void appendMoeImg(MoeImg moeImg);

        void showEmptyResult();

        void showBottom();
    }

    interface Presenter extends BasePresenter<View>{

        void refresh();

        void loadMore();
    }
}
