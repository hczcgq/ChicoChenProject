package com.runx.android.http;



import com.runx.android.bean.ResultBean;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;


/**
 * Created by Administrator on 2018/3/2 0002.
 */

public class ResultTransformer<T> implements ObservableTransformer<ResultBean<T>, T> {
    @Override
    public ObservableSource<T> apply(Observable<ResultBean<T>> upstream) {
        return upstream.flatMap(new Function<ResultBean<T>, ObservableSource<T>>() {
            @Override
            public ObservableSource<T> apply(ResultBean<T> resultInfo) throws Exception {
                if (resultInfo.code == 200) {
                    return createData(resultInfo.data);
                } else {
                    return Observable.error(new Exception());
                }
            }
        });
    }

    public static <T> Observable<T> createData(final T t) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });
    }

}
