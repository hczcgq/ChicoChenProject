package com.runx.android.base;

/**
 * Created by 陈国权 on 2018/3/2 0002.
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}
