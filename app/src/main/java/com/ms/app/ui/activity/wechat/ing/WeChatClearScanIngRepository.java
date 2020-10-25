package com.ms.app.ui.activity.wechat.ing;

import android.os.SystemClock;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ms.app.utils.WeChatClearUtils;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WeChatClearScanIngRepository {

    private MutableLiveData<Long> mutableLiveDataTotalScanGarbageSize;
    private volatile long totalScanGarbageSize = 0;

    public WeChatClearScanIngRepository(MutableLiveData<Long> mutableLiveDataTotalScanGarbageSize) {
        this.mutableLiveDataTotalScanGarbageSize = mutableLiveDataTotalScanGarbageSize;
    }
    public LiveData<List<String>> receiveFile() {
        return new LiveData<List<String>>() {
            @Override
            protected void onActive() {
                super.onActive();

                Observable.create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<String>> emitter) throws Exception {
                        List<String> result = WeChatClearUtils.receiveFiles();
                        SystemClock.sleep(1000);
                        emitter.onNext(result);
                        for (String it : result) {
                            totalScanGarbageSize += new File(it).length();
                            mutableLiveDataTotalScanGarbageSize.postValue(totalScanGarbageSize);
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Observer<List<String>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                            }

                            @Override
                            public void onNext(@NonNull List<String> result) {
                                postValue(result);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                            }

                            @Override
                            public void onComplete() {
                            }
                        });

            }
        };
    }


    public LiveData<List<String>> rubbish() {
        return new LiveData<List<String>>() {
            @Override
            protected void onActive() {


                Observable.create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<String>> emitter) throws Exception {
                        List<String> result = WeChatClearUtils.rubbish();
                        SystemClock.sleep(1000);
                        emitter.onNext(result);
                        for (String it : result) {
                            totalScanGarbageSize += new File(it).length();
                            mutableLiveDataTotalScanGarbageSize.postValue(totalScanGarbageSize);
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Observer<List<String>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull List<String> result) {
                                postValue(result);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });


            }
        };
    }


    public LiveData<List<String>> mp4() {
        return new LiveData<List<String>>() {
            @Override
            protected void onActive() {

                Observable.create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<String>> emitter) throws Exception {
                        List<String> result = WeChatClearUtils.mp4s();
                        SystemClock.sleep(1000);
                        emitter.onNext(result);
                        for (String it : result) {
                            totalScanGarbageSize += new File(it).length();
                            mutableLiveDataTotalScanGarbageSize.postValue(totalScanGarbageSize);
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Observer<List<String>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull List<String> result) {
                                postValue(result);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

            }
        };
    }

    public LiveData<List<String>> image() {
        return new LiveData<List<String>>() {
            @Override
            protected void onActive() {
                Observable.create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<String>> emitter) throws Exception {
                        List<String> result = WeChatClearUtils.images();
                        SystemClock.sleep(1000);
                        emitter.onNext(result);
                        for (String it : result) {
                            totalScanGarbageSize += new File(it).length();
                            mutableLiveDataTotalScanGarbageSize.postValue(totalScanGarbageSize);
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Observer<List<String>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull List<String> result) {
                                postValue(result);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });


            }
        };
    }


    public LiveData<List<String>> voice() {
        return new LiveData<List<String>>() {
            @Override
            protected void onActive() {

                Observable.create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<String>> emitter) throws Exception {
                        List<String> result = WeChatClearUtils.voice2();
                        SystemClock.sleep(1000);
                        emitter.onNext(result);
                        for (String it : result) {
                            totalScanGarbageSize += new File(it).length();
                            mutableLiveDataTotalScanGarbageSize.postValue(totalScanGarbageSize);
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Observer<List<String>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull List<String> result) {
                                postValue(result);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });


            }
        };
    }

    public LiveData<List<String>> emoji() {
        return new LiveData<List<String>>() {
            @Override
            protected void onActive() {

                Observable.create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<String>> emitter) throws Exception {
                        List<String> result = WeChatClearUtils.emojis();
                        SystemClock.sleep(1000);
                        emitter.onNext(result);
                        for (String it : result) {
                            totalScanGarbageSize += new File(it).length();
                            mutableLiveDataTotalScanGarbageSize.postValue(totalScanGarbageSize);
                        }
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Observer<List<String>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull List<String> result) {
                                postValue(result);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

            }
        };
    }
}
