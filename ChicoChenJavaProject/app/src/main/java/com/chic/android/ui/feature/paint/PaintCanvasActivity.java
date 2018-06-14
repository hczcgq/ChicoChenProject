package com.chic.android.ui.feature.paint;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.chic.android.R;
import com.chic.android.base.BaseActivity;

import butterknife.BindView;

/**
 * @author: 陈国权
 * @date: 2018/6/14 15:47
 * @description:
 */

public class PaintCanvasActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.view_canvas)
    PaintCanvasView canvasView;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_paint_canvas;
    }

    @Override
    protected void initActivity() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP
                | ActionBar.DISPLAY_SHOW_HOME);
        mToolbar.setNavigationIcon(R.drawable.icon_back);
        toolbarTitle.setText("画板");

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        canvasView.init(metrics);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_paint, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_normal:
                canvasView.normal();
                return true;
            case R.id.item_emboss:
                canvasView.emboss();
                return true;
            case R.id.item_blur:
                canvasView.blur();
                return true;
            case R.id.item_clear:
                canvasView.clean();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
