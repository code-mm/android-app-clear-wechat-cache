package com.ms.module.wechat.clear.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.ms.module.wechat.clear.R;
import com.ms.module.wechat.clear.activity.clear.DownChildNode;
import com.ms.module.wechat.clear.activity.clear.DownGroupNode;
import com.ms.module.wechat.clear.activity.clear.UpChildNode;
import com.ms.module.wechat.clear.activity.clear.UpGroupNode;
import com.ms.module.wechat.clear.activity.file.FileChildNode;
import com.ms.module.wechat.clear.activity.file.FileHeaderNode;
import com.ms.module.wechat.clear.utils.WeChatClearUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WeChatScanDataRepository {


    private static final String TAG = "WeChatScanDataRepositor";

    private WeChatScanDataRepository() {
    }

    private static final WeChatScanDataRepository instance = new WeChatScanDataRepository();

    public static WeChatScanDataRepository getInstance() {
        return instance;
    }

    // 朋友圈缓冲
    public List<String> friendList = new ArrayList<>();

    public List<String> cacheList = new ArrayList<>();

    // 垃圾文件
    public List<String> rubbishList = new ArrayList<>();
    // video
    public List<String> videoList = new ArrayList<>();
    // image
    public List<String> imageList = new ArrayList<>();
    // voice
    public List<String> voiceList = new ArrayList<>();
    // file
    public List<String> fileList = new ArrayList<>();
    // emoji
    public List<String> emojiList = new ArrayList<>();


    public List<String> getCacheList() {
        return cacheList;
    }

    public List<String> getFriendList() {
        return friendList;
    }

    public List<String> getEmojiList() {
        return emojiList;
    }

    public List<String> getFileList() {
        return fileList;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public List<String> getRubbishList() {
        return rubbishList;
    }

    public List<String> getVoiceList() {
        return voiceList;
    }

    public List<String> getVideoList() {
        return videoList;
    }

    // 扫描文件总大小
    private MutableLiveData<Long> mutableLiveDataTotalScanGarbageSize;


    public MutableLiveData<Long> getMutableLiveDataTotalScanGarbageSize() {
        return mutableLiveDataTotalScanGarbageSize;
    }

    public void setMutableLiveDataTotalScanGarbageSize(MutableLiveData<Long> mutableLiveDataTotalScanGarbageSize) {
        this.mutableLiveDataTotalScanGarbageSize = mutableLiveDataTotalScanGarbageSize;
    }

    private volatile long totalScanGarbageSize = 0;

    /**
     * 接收文件
     *
     * @return
     */
    public LiveData<List<String>> receiveFile() {
        return new LiveData<List<String>>() {
            @Override
            protected void onActive() {
                super.onActive();

                Observable.create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {
                        List<String> result = WeChatClearUtils.receiveFiles();
                        fileList.clear();
                        fileList.addAll(result);
                        for (String it : result) {
                            totalScanGarbageSize += new File(it).length();
                            mutableLiveDataTotalScanGarbageSize.postValue(totalScanGarbageSize);
                        }

                        emitter.onNext(result);

                    }
                })
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Observer<List<String>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<String> list) {
                                postValue(list);
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        };
    }


    /**
     * 垃圾文件
     *
     * @return
     */
    public LiveData<List<String>> rubbish() {
        return new LiveData<List<String>>() {
            @Override
            protected void onActive() {
                Observable.create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<String>> emitter) throws Exception {
                        List<String> rubbish = WeChatClearUtils.rubbish();
                        rubbishList.clear();
                        rubbishList.addAll(rubbish);

                        List<String> cache = WeChatClearUtils.cache();

                        cacheList.clear();
                        cacheList.addAll(cache);

                        List<String> friendCache = WeChatClearUtils.friendCache();
                        friendList.clear();
                        friendList.addAll(friendCache);


                        for (String it : rubbish) {
                            totalScanGarbageSize += new File(it).length();
                            mutableLiveDataTotalScanGarbageSize.postValue(totalScanGarbageSize);
                        }

                        for (String it : cache) {
                            totalScanGarbageSize += new File(it).length();
                            mutableLiveDataTotalScanGarbageSize.postValue(totalScanGarbageSize);
                        }

                        for (String it : friendCache) {
                            totalScanGarbageSize += new File(it).length();
                            mutableLiveDataTotalScanGarbageSize.postValue(totalScanGarbageSize);
                        }

                        List<String> result = new ArrayList<>();
                        result.addAll(rubbish);
                        result.addAll(cache);
                        result.addAll(friendCache);
                        emitter.onNext(result);
                    }
                })
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


    /**
     * 视频文件
     *
     * @return
     */
    public LiveData<List<String>> video() {
        return new LiveData<List<String>>() {
            @Override
            protected void onActive() {

                Observable.create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<String>> emitter) throws Exception {
                        List<String> result = WeChatClearUtils.video();
                        videoList.clear();
                        videoList.addAll(result);
                        for (String it : result) {
                            totalScanGarbageSize += new File(it).length();
                            mutableLiveDataTotalScanGarbageSize.postValue(totalScanGarbageSize);
                        }
                        emitter.onNext(result);
                    }
                })
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

    /**
     * 图片
     *
     * @return
     */
    public LiveData<List<String>> image() {
        return new LiveData<List<String>>() {
            @Override
            protected void onActive() {
                Observable.create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<String>> emitter) throws Exception {
                        List<String> result = WeChatClearUtils.images();
                        imageList.clear();
                        imageList.addAll(result);
                        for (String it : result) {
                            totalScanGarbageSize += new File(it).length();
                            mutableLiveDataTotalScanGarbageSize.postValue(totalScanGarbageSize);
                        }


                        emitter.onNext(result);
                    }
                })
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


    /**
     * 聊天语音
     *
     * @return
     */
    public LiveData<List<String>> voice() {
        return new LiveData<List<String>>() {
            @Override
            protected void onActive() {

                Observable.create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<String>> emitter) throws Exception {
                        List<String> result = WeChatClearUtils.voice2();
                        voiceList.clear();
                        voiceList.addAll(result);

                        for (String it : result) {
                            totalScanGarbageSize += new File(it).length();
                            mutableLiveDataTotalScanGarbageSize.postValue(totalScanGarbageSize);
                        }


                        emitter.onNext(result);
                    }
                })
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

    /**
     * 聊天表情
     *
     * @return
     */
    public LiveData<List<String>> emoji() {
        return new LiveData<List<String>>() {
            @Override
            protected void onActive() {

                Observable.create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<String>> emitter) throws Exception {
                        List<String> result = WeChatClearUtils.emojis();
                        emojiList.clear();
                        emojiList.addAll(result);
                        for (String it : result) {
                            totalScanGarbageSize += new File(it).length();
                            mutableLiveDataTotalScanGarbageSize.postValue(totalScanGarbageSize);
                        }


                        emitter.onNext(result);
                    }
                })
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


    /**
     * 缓冲
     *
     * @return
     */
    public LiveData<List<String>> cache() {
        return new LiveData<List<String>>() {
            @Override
            protected void onActive() {

                Observable.create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<String>> emitter) throws Exception {
                        List<String> result = WeChatClearUtils.cache();
                        cacheList.clear();
                        cacheList.addAll(result);
                        for (String it : result) {
                            totalScanGarbageSize += new File(it).length();
                            mutableLiveDataTotalScanGarbageSize.postValue(totalScanGarbageSize);
                        }
                        emitter.onNext(result);
                    }
                })
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


    /**
     * 盆友圈缓冲
     *
     * @return
     */
    public LiveData<List<String>> friendCache() {
        return new LiveData<List<String>>() {
            @Override
            protected void onActive() {

                Observable.create(new ObservableOnSubscribe<List<String>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<String>> emitter) throws Exception {
                        List<String> result = WeChatClearUtils.friendCache();
                        friendList.clear();
                        friendList.addAll(result);
                        for (String it : result) {
                            totalScanGarbageSize += new File(it).length();
                            mutableLiveDataTotalScanGarbageSize.postValue(totalScanGarbageSize);
                        }
                        emitter.onNext(result);
                    }
                })
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


    /**
     * 扫描完成 上面部分聚合数据
     *
     * @return
     */
    public LiveData<List<BaseNode>> getDatas() {
        return new LiveData<List<BaseNode>>() {
            @Override
            protected void onActive() {
                super.onActive();
                Observable.create(new ObservableOnSubscribe<List<BaseNode>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<BaseNode>> emitter) throws Exception {

                        List<BaseNode> groupDatas = new ArrayList<>();
                        List<BaseNode> upChildDatas = new ArrayList<>();
                        List<BaseNode> downChildDatas = new ArrayList<>();


                        upChildDatas.add(new UpChildNode("垃圾文件", R.drawable.image_trash_can, WeChatClearUtils.getFileLength(rubbishList), true, rubbishList));
                        upChildDatas.add(new UpChildNode("缓冲文件", R.drawable.image_cache, WeChatClearUtils.getFileLength(cacheList), true, cacheList));
                        upChildDatas.add(new UpChildNode("朋友圈缓冲", R.drawable.image_firend, WeChatClearUtils.getFileLength(friendList), true, friendList));

                        downChildDatas.add(new DownChildNode("聊天小视频", R.drawable.image_video, WeChatClearUtils.getFileLength(videoList)));
                        downChildDatas.add(new DownChildNode("聊天图片", R.drawable.image_image, WeChatClearUtils.getFileLength(imageList)));
                        downChildDatas.add(new DownChildNode("聊天语音", R.drawable.image_voice, WeChatClearUtils.getFileLength(voiceList)));
                        downChildDatas.add(new DownChildNode("文件", R.drawable.image_file, WeChatClearUtils.getFileLength(fileList)));
                        downChildDatas.add(new DownChildNode("聊天表情包", R.drawable.image_emoji, WeChatClearUtils.getFileLength(emojiList)));


                        groupDatas.add(new UpGroupNode(upChildDatas, WeChatClearUtils.getFileLength(rubbishList, cacheList, friendList), true));
                        groupDatas.add(new DownGroupNode(downChildDatas, WeChatClearUtils.getFileLength(videoList, imageList, voiceList, fileList, emojiList)));

                        emitter.onNext(groupDatas);
                    }
                }).subscribeOn(Schedulers.io())
                        .subscribe(new Observer<List<BaseNode>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull List<BaseNode> baseNodes) {
                                postValue(baseNodes);
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


    /**
     * 图片详情
     *
     * @return
     */
    public LiveData<List<BaseNode>> getImageData() {
        return new LiveData<List<BaseNode>>() {
            @Override
            protected void onActive() {
                super.onActive();
                Observable.create(new ObservableOnSubscribe<List<BaseNode>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<BaseNode>> emitter) throws Exception {

                        // 时间为Key,集合为同一天的文件
                        Map<String, List<String>> mapDatePaths = new TreeMap<>();

                        // 遍历 视频集合列表
                        for (String it : imageList) {

                            // 获取文件时间戳
                            long fileLastModifiedTime = WeChatClearUtils.getFileLastModifiedTime(it);
                            // 计算出 时间key
                            String dateKey = simpleDateFormat.format(fileLastModifiedTime);

                            // 判断集合中是否含有此时间key
                            if (mapDatePaths.containsKey(dateKey)) {
                                //将同一天的文件加入到一个集合
                                mapDatePaths.get(dateKey).add(it);
                            } else {
                                // 存入时间key 和 数据源实例
                                mapDatePaths.put(dateKey, new ArrayList<>());
                                mapDatePaths.get(dateKey).add(it);
                            }
                        }


                        mapDatePaths = ((TreeMap) mapDatePaths).descendingMap();
                        Set<String> keySet = mapDatePaths.keySet();

                        List<BaseNode> baseNodes = new ArrayList<>();

                        for (String it : keySet) {

                            // 获取 一天时间里的所有文件路径
                            List<String> paths = mapDatePaths.get(it);

                            List<BaseNode> childNodes = new ArrayList<>();
                            for (String it1 : paths) {
                                childNodes.add(new FileChildNode(it1, true, WeChatClearUtils.getFileLength(it1)));
                            }
                            baseNodes.add(new FileHeaderNode(it, WeChatClearUtils.getFileLength(paths), paths.size(), true, childNodes));
                        }
                        emitter.onNext(baseNodes);
                    }
                }).observeOn(Schedulers.io())
                        .subscribe(new Observer<List<BaseNode>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull List<BaseNode> baseNodes) {
                                postValue(baseNodes);
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


    /**
     * 表情
     *
     * @return
     */
    public LiveData<List<BaseNode>> getEmojiData() {
        return new LiveData<List<BaseNode>>() {
            @Override
            protected void onActive() {
                super.onActive();


                Observable.create(new ObservableOnSubscribe<List<BaseNode>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<BaseNode>> emitter) throws Exception {

                        // 时间为Key,集合为同一天的文件
                        Map<String, List<String>> mapDatePaths = new TreeMap<>();

                        // 遍历 视频集合列表
                        for (String it : emojiList) {

                            // 获取文件时间戳
                            long fileLastModifiedTime = WeChatClearUtils.getFileLastModifiedTime(it);
                            // 计算出 时间key
                            String dateKey = simpleDateFormat.format(fileLastModifiedTime);

                            // 判断集合中是否含有此时间key
                            if (mapDatePaths.containsKey(dateKey)) {
                                //将同一天的文件加入到一个集合
                                mapDatePaths.get(dateKey).add(it);
                            } else {
                                // 存入时间key 和 数据源实例
                                mapDatePaths.put(dateKey, new ArrayList<>());
                                mapDatePaths.get(dateKey).add(it);
                            }
                        }
                        mapDatePaths = ((TreeMap) mapDatePaths).descendingMap();
                        Set<String> keySet = mapDatePaths.keySet();

                        List<BaseNode> baseNodes = new ArrayList<>();

                        for (String it : keySet) {

                            // 获取 一天时间里的所有文件路径
                            List<String> paths = mapDatePaths.get(it);

                            List<BaseNode> childNodes = new ArrayList<>();
                            for (String it1 : paths) {
                                childNodes.add(new FileChildNode(it1, true, WeChatClearUtils.getFileLength(it1)));
                            }
                            baseNodes.add(new FileHeaderNode(it, WeChatClearUtils.getFileLength(paths), paths.size(), true, childNodes));
                        }
                        emitter.onNext(baseNodes);
                    }
                }).observeOn(Schedulers.io())
                        .subscribe(new Observer<List<BaseNode>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull List<BaseNode> baseNodes) {
                                postValue(baseNodes);
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


    /**
     * 文件
     *
     * @return
     */
    public LiveData<List<BaseNode>> getFileData() {
        return new LiveData<List<BaseNode>>() {
            @Override
            protected void onActive() {
                super.onActive();

                Observable.create(new ObservableOnSubscribe<List<BaseNode>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<BaseNode>> emitter) throws Exception {


                        // 时间为Key,集合为同一天的文件
                        Map<String, List<String>> mapDatePaths = new TreeMap<>();

                        // 遍历 视频集合列表
                        for (String it : fileList) {

                            // 获取文件时间戳
                            long fileLastModifiedTime = WeChatClearUtils.getFileLastModifiedTime(it);
                            // 计算出 时间key
                            String dateKey = simpleDateFormat.format(fileLastModifiedTime);

                            // 判断集合中是否含有此时间key
                            if (mapDatePaths.containsKey(dateKey)) {
                                //将同一天的文件加入到一个集合
                                mapDatePaths.get(dateKey).add(it);
                            } else {
                                // 存入时间key 和 数据源实例
                                mapDatePaths.put(dateKey, new ArrayList<>());
                                mapDatePaths.get(dateKey).add(it);
                            }
                        }
                        mapDatePaths = ((TreeMap) mapDatePaths).descendingMap();
                        Set<String> keySet = mapDatePaths.keySet();

                        List<BaseNode> baseNodes = new ArrayList<>();

                        for (String it : keySet) {

                            // 获取 一天时间里的所有文件路径
                            List<String> paths = mapDatePaths.get(it);

                            List<BaseNode> childNodes = new ArrayList<>();
                            for (String it1 : paths) {
                                childNodes.add(new FileChildNode(it1, true, WeChatClearUtils.getFileLength(it1)));
                            }
                            baseNodes.add(new FileHeaderNode(it, WeChatClearUtils.getFileLength(paths), paths.size(), true, childNodes));
                        }
                        emitter.onNext(baseNodes);


                    }
                }).observeOn(Schedulers.io())
                        .subscribe(new Observer<List<BaseNode>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull List<BaseNode> baseNodes) {
                                postValue(baseNodes);
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


    /**
     * 语音
     *
     * @return
     */
    public LiveData<List<BaseNode>> getVoiceData() {
        return new LiveData<List<BaseNode>>() {
            @Override
            protected void onActive() {
                super.onActive();


                Observable.create(new ObservableOnSubscribe<List<BaseNode>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<BaseNode>> emitter) throws Exception {


                        // 时间为Key,集合为同一天的文件
                        Map<String, List<String>> mapDatePaths = new TreeMap<>();

                        // 遍历 视频集合列表
                        for (String it : voiceList) {

                            // 获取文件时间戳
                            long fileLastModifiedTime = WeChatClearUtils.getFileLastModifiedTime(it);
                            // 计算出 时间key
                            String dateKey = simpleDateFormat.format(fileLastModifiedTime);

                            // 判断集合中是否含有此时间key
                            if (mapDatePaths.containsKey(dateKey)) {
                                //将同一天的文件加入到一个集合
                                mapDatePaths.get(dateKey).add(it);
                            } else {
                                // 存入时间key 和 数据源实例
                                mapDatePaths.put(dateKey, new ArrayList<>());
                                mapDatePaths.get(dateKey).add(it);
                            }
                        }
                        mapDatePaths = ((TreeMap) mapDatePaths).descendingMap();
                        Set<String> keySet = mapDatePaths.keySet();

                        List<BaseNode> baseNodes = new ArrayList<>();

                        for (String it : keySet) {

                            // 获取 一天时间里的所有文件路径
                            List<String> paths = mapDatePaths.get(it);

                            List<BaseNode> childNodes = new ArrayList<>();
                            for (String it1 : paths) {
                                childNodes.add(new FileChildNode(it1, true, WeChatClearUtils.getFileLength(it1)));
                            }
                            baseNodes.add(new FileHeaderNode(it, WeChatClearUtils.getFileLength(paths), paths.size(), true, childNodes));
                        }
                        emitter.onNext(baseNodes);
                    }
                }).observeOn(Schedulers.io())
                        .subscribe(new Observer<List<BaseNode>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull List<BaseNode> baseNodes) {
                                postValue(baseNodes);
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


    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 视频
     *
     * @return
     */
    public LiveData<List<BaseNode>> getVideoData() {
        return new LiveData<List<BaseNode>>() {
            @Override
            protected void onActive() {
                super.onActive();

                Observable.create(new ObservableOnSubscribe<List<BaseNode>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<List<BaseNode>> emitter) throws Exception {

                        // 时间为Key,集合为同一天的文件
                        Map<String, List<String>> mapDatePaths = new TreeMap<>();

                        // 遍历 视频集合列表
                        for (String it : videoList) {

                            // 获取文件时间戳
                            long fileLastModifiedTime = WeChatClearUtils.getFileLastModifiedTime(it);
                            // 计算出 时间key
                            String dateKey = simpleDateFormat.format(fileLastModifiedTime);

                            // 判断集合中是否含有此时间key
                            if (mapDatePaths.containsKey(dateKey)) {
                                //将同一天的文件加入到一个集合
                                mapDatePaths.get(dateKey).add(it);
                            } else {
                                // 存入时间key 和 数据源实例
                                mapDatePaths.put(dateKey, new ArrayList<>());
                                mapDatePaths.get(dateKey).add(it);
                            }
                        }
                        mapDatePaths = ((TreeMap) mapDatePaths).descendingMap();
                        Set<String> keySet = mapDatePaths.keySet();

                        List<BaseNode> baseNodes = new ArrayList<>();

                        for (String it : keySet) {

                            // 获取 一天时间里的所有文件路径
                            List<String> paths = mapDatePaths.get(it);

                            List<BaseNode> childNodes = new ArrayList<>();
                            for (String it1 : paths) {
                                childNodes.add(new FileChildNode(it1, true, WeChatClearUtils.getFileLength(it1)));
                            }
                            baseNodes.add(new FileHeaderNode(it, WeChatClearUtils.getFileLength(paths), paths.size(), true, childNodes));
                        }
                        emitter.onNext(baseNodes);
                    }
                }).subscribeOn(Schedulers.io())
                        .subscribe(new Observer<List<BaseNode>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull List<BaseNode> baseNodes) {
                                postValue(baseNodes);
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


    public long getFilesLength(List<BaseNode> datas) {
        List<String> deletes = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            BaseNode baseNode = datas.get(i);

            if (baseNode instanceof FileHeaderNode) {
                FileHeaderNode imageHeaderNode = (FileHeaderNode) baseNode;
                boolean check = imageHeaderNode.isCheck();
                if (check) {
                    List<BaseNode> childNode = imageHeaderNode.getChildNode();
                    for (int j = 0; j < childNode.size(); j++) {
                        BaseNode baseNode1 = childNode.get(j);
                        if (baseNode1 instanceof FileChildNode) {
                            FileChildNode imageChildNode = (FileChildNode) baseNode1;
                            deletes.add(imageChildNode.getPath());
                        }
                    }
                } else {
                    List<BaseNode> childNode = imageHeaderNode.getChildNode();
                    for (int j = 0; j < childNode.size(); j++) {
                        BaseNode baseNode1 = childNode.get(j);
                        if (baseNode1 instanceof FileChildNode) {
                            FileChildNode imageChildNode = (FileChildNode) baseNode1;
                            boolean check1 = imageChildNode.isCheck();
                            if (check1) {
                                deletes.add(imageChildNode.getPath());
                            }
                        }
                    }
                }
            }
        }

        long fileLength = WeChatClearUtils.getFileLength(deletes);

        return fileLength;
    }


    public void deleteFiles(List<BaseNode> datas) {


        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Boolean> emitter) throws Exception {


                List<String> deletes = new ArrayList<>();
                for (int i = 0; i < datas.size(); i++) {
                    BaseNode baseNode = datas.get(i);

                    if (baseNode instanceof FileHeaderNode) {
                        FileHeaderNode imageHeaderNode = (FileHeaderNode) baseNode;
                        boolean check = imageHeaderNode.isCheck();
                        if (check) {
                            List<BaseNode> childNode = imageHeaderNode.getChildNode();
                            for (int j = 0; j < childNode.size(); j++) {
                                BaseNode baseNode1 = childNode.get(j);
                                if (baseNode1 instanceof FileChildNode) {
                                    FileChildNode imageChildNode = (FileChildNode) baseNode1;
                                    deletes.add(imageChildNode.getPath());
                                }
                            }
                        } else {
                            List<BaseNode> childNode = imageHeaderNode.getChildNode();
                            for (int j = 0; j < childNode.size(); j++) {
                                BaseNode baseNode1 = childNode.get(j);
                                if (baseNode1 instanceof FileChildNode) {
                                    FileChildNode imageChildNode = (FileChildNode) baseNode1;
                                    boolean check1 = imageChildNode.isCheck();
                                    if (check1) {
                                        deletes.add(imageChildNode.getPath());
                                    }
                                }
                            }
                        }
                    }
                }

                for (String it : deletes) {
                    WeChatClearUtils.delete(it);
                }

                emitter.onComplete();


            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Boolean aBoolean) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 删除文件 并且带适配器刷新
     *
     * @param datas
     * @param adapter
     */
    public void deleteFile(List<BaseNode> datas, RecyclerView.Adapter adapter) {
        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Boolean> emitter) throws Exception {


                List<String> deletes = new ArrayList<>();
                for (int i = 0; i < datas.size(); i++) {
                    BaseNode baseNode = datas.get(i);

                    if (baseNode instanceof FileHeaderNode) {
                        FileHeaderNode imageHeaderNode = (FileHeaderNode) baseNode;
                        boolean check = imageHeaderNode.isCheck();
                        if (check) {
                            List<BaseNode> childNode = imageHeaderNode.getChildNode();
                            for (int j = 0; j < childNode.size(); j++) {
                                BaseNode baseNode1 = childNode.get(j);
                                if (baseNode1 instanceof FileChildNode) {
                                    FileChildNode imageChildNode = (FileChildNode) baseNode1;
                                    deletes.add(imageChildNode.getPath());
                                }
                            }
                        } else {
                            List<BaseNode> childNode = imageHeaderNode.getChildNode();
                            for (int j = 0; j < childNode.size(); j++) {
                                BaseNode baseNode1 = childNode.get(j);
                                if (baseNode1 instanceof FileChildNode) {
                                    FileChildNode imageChildNode = (FileChildNode) baseNode1;
                                    boolean check1 = imageChildNode.isCheck();
                                    if (check1) {
                                        deletes.add(imageChildNode.getPath());
                                    }
                                }
                            }
                        }
                    }


                    if (baseNode instanceof UpGroupNode) {
                        UpGroupNode upGroupNode = (UpGroupNode) baseNode;
                        if (upGroupNode.isCheck()) {
                            List<BaseNode> childNode = upGroupNode.getChildNode();

                            for (int k = 0; k < childNode.size(); k++) {
                                BaseNode baseNode1 = childNode.get(k);
                                if (baseNode1 instanceof UpChildNode) {
                                    UpChildNode upChildNode = (UpChildNode) baseNode1;
                                    deletes.addAll(upChildNode.getPaths());
                                }
                            }

                        } else {
                            List<BaseNode> childNode = upGroupNode.getChildNode();

                            for (int k = 0; k < childNode.size(); k++) {
                                BaseNode baseNode1 = childNode.get(k);
                                if (baseNode1 instanceof UpChildNode) {
                                    UpChildNode upChildNode = (UpChildNode) baseNode1;

                                    boolean check = upChildNode.isCheck();

                                    if (check) {
                                        deletes.addAll(upChildNode.getPaths());
                                    }
                                }
                            }
                        }
                    }
                }

                Log.e(TAG, "subscribe: " + deletes.size());

                for (String it : deletes) {
                    boolean delete = WeChatClearUtils.delete(it);


                    Log.e(TAG, "subscribe: " + it + " 删除成功 : " + delete);

                }

                emitter.onComplete();


            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Boolean aBoolean) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        if (adapter != null) {
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }


    /**
     * 清理现有 数据
     */
    public void clear() {
        totalScanGarbageSize = 0;
        friendList.clear();
        cacheList.clear();
        rubbishList.clear();
        videoList.clear();
        imageList.clear();
        voiceList.clear();
        fileList.clear();
        emojiList.clear();
    }
}