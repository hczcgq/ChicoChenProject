package com.runx.android.base;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import com.runx.android.R;
import com.runx.android.common.constant.Constant;
import com.runx.android.common.util.AppUtil;
import com.tamic.jswebview.browse.BridgeHandler;
import com.tamic.jswebview.browse.BridgeWebView;
import com.tamic.jswebview.browse.BridgeWebViewClient;
import com.tamic.jswebview.browse.CallBackFunction;
import com.tamic.jswebview.browse.DefaultHandler;
import butterknife.BindView;

/**
 * Created by 陈国权 on 2018/3/8 0008.
 */

public class BaseHtmlActivity extends BaseActivity {
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.web_view)
    BridgeWebView webView;

    private String url;

    public static void startActivity(Context context, String url) {
        Intent intent = new Intent(context, BaseHtmlActivity.class);
        intent.putExtra(Constant.INTENT_URL, url);
        context.startActivity(intent);
    }

    @Override
    public void getIntentData() {
        super.getIntentData();
        url = getIntent().getExtras().getString(Constant.INTENT_URL);
    }

    @Override
    public int getContentViewId() {
        return R.layout.base_html;
    }

    @Override
    public void initActivity() {
        initWebClient();
        loadUrl();
    }

    /**
     * 设置WebView
     */
    private void initWebClient() {
        webView.setWebViewClient(new MyWebViewClient(webView));

        webView.setOnKeyListener(onKeyListener);
        webView.setWebChromeClient(webChromeClient);
        webView.setDefaultHandler(new MyHandlerCallBack());
    }

    private void loadUrl() {
        if (!TextUtils.isEmpty(url)) {
            webView.loadUrl("file:///android_asset/demo.html");

            webView.registerHandler("submitFromWeb", new BridgeHandler() {
                @Override
                public void handler(String data, CallBackFunction function) {
                    Log.e("===>", "handler = submitFromWeb,data from web = " + data);
                    //回调返回给js
                    function.onCallBack("Android名称：" + AppUtil.getAppName(BaseHtmlActivity.this));
                }
            });

            webView.callHandler("functionInJs", "这是我的名字", new CallBackFunction() {
                @Override
                public void onCallBack(String data) {
                    Log.e("====>",data);
                }
            });
        }
    }

    private View.OnKeyListener onKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent keyEvent) {
            if ((keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
                webView.goBack();
                return true;
            }
            return false;
        }
    };

    private WebChromeClient webChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressBar.setVisibility(View.GONE);
            } else {
                if (progressBar.getVisibility() == View.INVISIBLE) {
                    progressBar.setVisibility(View.VISIBLE);
                }
                progressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    };


    class MyWebViewClient extends BridgeWebViewClient {

        public MyWebViewClient(BridgeWebView webView) {
            super(webView);
        }
    }

    class MyHandlerCallBack extends DefaultHandler {
        @Override
        public void handler(String data, CallBackFunction function) {
            Log.e("11===>", data + "---" + function);
        }
    }


}
