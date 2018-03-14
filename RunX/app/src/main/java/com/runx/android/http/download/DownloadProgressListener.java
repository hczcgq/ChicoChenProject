package com.runx.android.http.download;

/**
 * Created by Administrator on 2018/3/6 0006.
 */

public interface DownloadProgressListener {
    void update(long bytesRead, long contentLength, boolean done);
}
