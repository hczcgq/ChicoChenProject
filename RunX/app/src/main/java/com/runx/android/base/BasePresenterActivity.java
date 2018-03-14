package com.runx.android.base;


import javax.inject.Inject;

/**
 * Created by 陈国权 on 2018/3/2 0002.
 */

public abstract class BasePresenterActivity<T extends BasePresenter> extends BaseActivity implements BaseView {
    @Inject
    protected T presenter;

    @Override
    public void initActivity() {
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }
}
