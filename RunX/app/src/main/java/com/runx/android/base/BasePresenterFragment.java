package com.runx.android.base;
import javax.inject.Inject;

/**
 * Created by 陈国权 on 2018/3/2 0002.
 */

public abstract class BasePresenterFragment<T extends BasePresenter> extends BaseFragment implements BaseView {

    @Inject
    protected T presenter;

    @Override
    protected void initFragment() {
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }
}
