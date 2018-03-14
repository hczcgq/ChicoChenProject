package com.runx.android.http;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2018/3/2 0002.
 */

public interface APIService {

    @Streaming
    @GET
    Observable<ResponseBody> downloadApk(@Url String url);
}
