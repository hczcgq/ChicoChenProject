package com.runx.android.ui.splash;
import com.runx.android.base.BaseActivity;
import com.runx.android.common.constant.Constant;
import com.runx.android.common.util.PreferencesUtils;
import com.runx.android.ui.main.MainActivity;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2018/3/7 0007.
 */

public class SplashActivity extends BaseActivity {

    @Override
    public int getContentViewId() {
        return -1;
    }

    @Override
    public void initActivity() {
        final boolean guide = PreferencesUtils.checkExist(this, Constant.PRE_GUIDE_STATUE);
        Observable.timer(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (guide) {
                            MainActivity.startActivity(SplashActivity.this);
                        } else {
                            GuideActivity.startActivity(SplashActivity.this);
                        }
                        finish();
                    }
                });
    }
}
