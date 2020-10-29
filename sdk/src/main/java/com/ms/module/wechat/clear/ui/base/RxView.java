package com.ms.module.wechat.clear.ui.base;

import android.os.Looper;
import android.view.View;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

import static com.ms.module.wechat.clear.ui.base.Preconditions.checkNotNull;
import static com.ms.module.wechat.clear.ui.base.Preconditions.checkUiThread;


// @author maohuawei
// @date 2019/2/2
// 使用Rxjava防止抖动 & 重复点击
public class RxView {
    /**
     * 防止重复点击
     *
     * @param target 目标view
     * @param action 监听器
     */
    public static void setOnClickListeners(final Action1<View> action, @NonNull View... target) {
        for (View view : target) {
            RxView.onClick(view).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Consumer<View>() {
                @Override
                public void accept(@io.reactivex.annotations.NonNull View view) throws Exception {
                    action.onClick(view);
                }
            });
        }
    }

    /**
     * 监听onclick事件防抖动
     *
     * @param view
     * @return
     */
    @CheckResult
    @NonNull
    private static Observable<View> onClick(@NonNull View view) {
        checkNotNull(view, "view == null");
        return Observable.create(new ViewClickOnSubscribe(view));
    }


    /**
     * onclick事件防抖动
     * 返回view
     */
    private static class ViewClickOnSubscribe implements ObservableOnSubscribe<View> {
        private View view;

        public ViewClickOnSubscribe(View view) {
            this.view = view;
        }

        @Override
        public void subscribe(@io.reactivex.annotations.NonNull final ObservableEmitter<View> e) throws Exception {
            checkUiThread();

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!e.isDisposed()) {
                        e.onNext(view);
                    }
                }
            };
            view.setOnClickListener(listener);
        }
    }

    /**
     * A one-argument action. 点击事件转发接口
     *
     * @param <T> the first argument type
     */
    public interface Action1<T> {
        void onClick(T t);
    }
}


final class Preconditions {
    public static void checkArgument(boolean assertion, String message) {
        if (!assertion) {
            throw new IllegalArgumentException(message);
        }
    }

    public static <T> T checkNotNull(T value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
        return value;
    }

    public static void checkUiThread() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException(
                    "Must be called from the main thread. Was: " + Thread.currentThread());
        }
    }

    private Preconditions() {
        throw new AssertionError("No instances.");
    }
}
