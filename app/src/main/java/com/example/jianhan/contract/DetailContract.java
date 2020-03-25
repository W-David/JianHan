package com.example.jianhan.contract;


import com.example.jianhan.base.BasePresenter;
import com.example.jianhan.base.BaseView;

import java.util.List;

public interface DetailContract {

    interface View extends BaseView{

        void hideProgressBar();

        void showProgressBar();

        void initDetailImg();

    }

    interface Presenter extends BasePresenter<View>{

        void refresh();
    }
}
