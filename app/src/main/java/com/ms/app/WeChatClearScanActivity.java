package com.ms.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ms.app.utils.WeChatClearUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * 扫描中
 */
public class WeChatClearScanActivity extends AppCompatActivity {


    private static final String TAG = "WeChatClearScanActivity";


    private RecyclerView recyclerView;
    private WeChatClearRecyclerViewAdapter weChatClearRecyclerViewAdapter;
    private List<WeChatClearRecyclerViewAdapterItemBean> datas = new ArrayList<>();

    private TextView textViewCountSize;


    private List<String> rubbishList = new ArrayList<>();
    private List<String> voideList = new ArrayList<>();
    private List<String> imageList = new ArrayList<>();
    private List<String> voiceList = new ArrayList<>();
    private List<String> fileList = new ArrayList<>();
    private List<String> emojiList = new ArrayList<>();

    private static long COUNT_SIZE = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat_clear_scan);


        recyclerView = findViewById(R.id.recyclerView);
        textViewCountSize = findViewById(R.id.textViewCountSize);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        datas.add(new WeChatClearRecyclerViewAdapterItemBean("垃圾文件", R.drawable.image_rubbish));
        datas.add(new WeChatClearRecyclerViewAdapterItemBean("聊天小视频", R.drawable.image_video));
        datas.add(new WeChatClearRecyclerViewAdapterItemBean("聊天图片", R.drawable.image_image));
        datas.add(new WeChatClearRecyclerViewAdapterItemBean("聊天语音", R.drawable.image_voice));
        datas.add(new WeChatClearRecyclerViewAdapterItemBean("文件", R.drawable.image_file));
        datas.add(new WeChatClearRecyclerViewAdapterItemBean("聊天表情包", R.drawable.image_emoji));

        weChatClearRecyclerViewAdapter = new WeChatClearRecyclerViewAdapter(this, datas);
        recyclerView.setAdapter(weChatClearRecyclerViewAdapter);

        rubbish();
        refresh();
    }


    public void refresh() {


        Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> emitter) throws Exception {


                while (true) {
                    SystemClock.sleep(100);
                    emitter.onNext(COUNT_SIZE);
                }

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long l) {

                        textViewCountSize.setText("" + l);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        textViewCountSize.setText("" + COUNT_SIZE);
    }


    private void emoji() {
        datas.get(5).status = 1;
        weChatClearRecyclerViewAdapter.notifyDataSetChanged();
        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {

                List<String> result = WeChatClearUtils.emojis();
                emitter.onNext(result);
                SystemClock.sleep(1000);
                emitter.onComplete();

                for (String it : result) {
                    COUNT_SIZE += new File(it).length();
                }


            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> s) {
                        Log.e(TAG, "onNext: " + s);
                        emojiList.clear();
                        emojiList.addAll(s);
                        CommonData.emojiList.clear();
                        CommonData.emojiList.addAll(s);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        datas.get(5).status = 2;
                        weChatClearRecyclerViewAdapter.notifyDataSetChanged();
                        startActivity(new Intent(WeChatClearScanActivity.this, WeChatClearActivity.class));
                        finish();
                    }
                });

    }

    private void file() {
        datas.get(4).status = 1;
        weChatClearRecyclerViewAdapter.notifyDataSetChanged();
        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {

                List<String> result = WeChatClearUtils.receiveFiles();
                emitter.onNext(result);
                SystemClock.sleep(1000);
                emitter.onComplete();

                for (String it : result) {
                    COUNT_SIZE += new File(it).length();
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> s) {
                        fileList.clear();
                        fileList.addAll(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        datas.get(4).status = 2;
                        weChatClearRecyclerViewAdapter.notifyDataSetChanged();
                        emoji();
                    }
                });

    }

    private void voice() {
        datas.get(3).status = 1;
        weChatClearRecyclerViewAdapter.notifyDataSetChanged();
        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {

                List<String> result = WeChatClearUtils.voice2();
                emitter.onNext(result);
                SystemClock.sleep(1000);
                emitter.onComplete();
                for (String it : result) {
                    COUNT_SIZE += new File(it).length();
                }


            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> s) {
                        voiceList.clear();
                        voiceList.addAll(s);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        datas.get(3).status = 2;
                        weChatClearRecyclerViewAdapter.notifyDataSetChanged();
                        file();
                    }
                });
    }

    private void image() {
        datas.get(2).status = 1;
        weChatClearRecyclerViewAdapter.notifyDataSetChanged();
        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {


                List<String> result = WeChatClearUtils.images();
                emitter.onNext(result);
                SystemClock.sleep(1000);
                emitter.onComplete();
                for (String it : result) {
                    COUNT_SIZE += new File(it).length();
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> s) {
                        imageList.clear();
                        imageList.addAll(s);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        datas.get(2).status = 2;
                        weChatClearRecyclerViewAdapter.notifyDataSetChanged();
                        voice();
                    }
                });
    }


    private void video() {
        datas.get(1).status = 1;
        weChatClearRecyclerViewAdapter.notifyDataSetChanged();
        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {
                List<String> result = WeChatClearUtils.mp4s();

                emitter.onNext(result);
                SystemClock.sleep(1000);
                emitter.onComplete();

                for (String it : result) {
                    COUNT_SIZE += new File(it).length();
                }

            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> s) {
                        voideList.clear();
                        voideList.addAll(s);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        datas.get(1).status = 2;
                        weChatClearRecyclerViewAdapter.notifyDataSetChanged();
                        image();
                    }
                });
    }

    private void rubbish() {
        datas.get(0).status = 1;
        weChatClearRecyclerViewAdapter.notifyDataSetChanged();
        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {
                emitter.onNext(WeChatClearUtils.emojis());
                SystemClock.sleep(1000);
                emitter.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> s) {
                        rubbishList.clear();
                        rubbishList.addAll(s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        datas.get(0).status = 2;
                        weChatClearRecyclerViewAdapter.notifyDataSetChanged();
                        video();
                    }
                });
    }

    static class WeChatClearRecyclerViewAdapterItemBean {

        public String name;
        public int icon;
        // 0 默认状态
        // 1 扫描中
        // 2 扫描完成
        public int status = 0;

        public WeChatClearRecyclerViewAdapterItemBean(String name, int icon) {
            this.name = name;
            this.icon = icon;
        }
    }

    static class WeChatClearRecyclerViewAdapter extends RecyclerView.Adapter {

        private Context context;
        private List<WeChatClearRecyclerViewAdapterItemBean> datas;

        public WeChatClearRecyclerViewAdapter(Context context, List<WeChatClearRecyclerViewAdapterItemBean> datas) {
            this.context = context;
            this.datas = datas;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.adapter_wechat_clear_scan_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.textViewName.setText(datas.get(position).name);
            Glide.with(context).load(datas.get(position).icon).into(viewHolder.imageViewLogo);

            if (datas.get(position).status == 0) {
                Glide.with(context).load(R.drawable.image_no_start).into(viewHolder.imageViewStatus);

            } else if (datas.get(position).status == 1) {
                Glide.with(context).load(R.drawable.image_loading)
                        .into(viewHolder.imageViewStatus);

                Animation animation = AnimationUtils.loadAnimation(context,R.anim.anim_loading_ing);
                viewHolder.imageViewStatus.startAnimation(animation);

            } else if (datas.get(position).status == 2) {
                Glide.with(context).load(R.drawable.image_finish).into(viewHolder.imageViewStatus);
            }
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private ImageView imageViewLogo;
        private ImageView imageViewStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            imageViewLogo = itemView.findViewById(R.id.imageViewLogo);
            imageViewStatus = itemView.findViewById(R.id.imageViewStatus);
        }
    }
}


