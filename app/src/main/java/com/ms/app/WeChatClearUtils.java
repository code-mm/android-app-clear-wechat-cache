package com.ms.app;

import android.util.Log;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

public class WeChatClearUtils {

    // 微信包名称
    //com.tencent.mm

    private static final String TAG = "WeChatClearUtils";

    // 图片
    public static final String Pictures = "/sdcard/Pictures/WeiXin";

    // 外部 缓冲数据
    public static final String MicroMsg = "/sdcard/tencent/MicroMsg";

    // 内部 缓冲数据
    public static final String ANDROID_DATA_MicroMsg = "/sdcard/Android/data/com.tencent.mm/MicroMsg";

    // 缓冲路径
    public static final String CACHE = "/sdcard/Android/data/com.tencent.mm/cache";

    // 微信接收文件的目录
    public static final String DOWNLOAD = "/sdcard/Android/data/com.tencent.mm/MicroMsg/Download";


    public static final String attachment = "attachment";
    // 头像
    public static final String avatar = "avatar";
    public static final String bizcache = "bizcache";
    public static final String bizimg = "bizimg";
    public static final String brandicon = "brandicon";
    public static final String draft = "draft";
    //emoji
    public static final String emoji = "emoji";
    public static final String favoffline = "favoffline";
    public static final String favorite = "favorite";
    public static final String image = "image";
    public static final String image2 = "image2";
    public static final String mailapp = "mailapp";
    public static final String music = "music";
    public static final String oneday = "oneday";
    public static final String openapi = "openapi";
    public static final String openapi_cache = "openapi_cache";
    public static final String openim = "openim";
    public static final String _package = "package";
    public static final String patmsg = "patmsg";
    public static final String recbiz = "recbiz";
    public static final String record = "record";
    public static final String scanner = "scanner";
    public static final String video = "video";
    public static final String voice = "voice";
    public static final String voice2 = "voice2";
    public static final String voiceremind = "voiceremind";
    public static final String webcanvascache = "webcanvascache";
    public static final String wenote = "wenote";


    public static final String JPG = ".jpg";
    public static final String PNG = ".png";
    public static final String JPEG = ".jpeg";
    public static final String WEBP = ".webp";
    public static final String MP3 = ".mp3";
    public static final String MP4 = ".mp4";
    public static final String PDF = ".pdf";


    /**
     * 去除List<String>中重复元素
     *
     * @param data
     * @return
     */
    public static List<String> removeDuplicateElements(List<String> data) {
        HashSet ckear = new HashSet(data);
        data.clear();
        data.addAll(ckear);
        return data;
    }


    /**
     * 去除没有后缀的文件
     */
    public static List<String> removeFilesWithoutSuffix(List<String> datas) {

        List<String> result = new ArrayList<>();

        for (String it : datas) {

            File file = new File(it);

            if (file.getName().lastIndexOf(".") != -1) {
                result.add(it);
            }

        }
        return result;
    }


    /**
     * 获取文件创建时间
     *
     * @param file
     * @return
     */
    public static long getFileLastModifiedTime(File file) {
        long time = file.lastModified();
        return time;
    }


    /**
     * 遍历所有的文件
     *
     * @param result
     * @param path
     */
    public static void search(List<String> result, String path) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files == null || files.length == 0) {
                return;
            }
            for (File it : files) {
                if (it.isHidden()) {
                    continue;
                }
                if (it.isDirectory()) {
                    search(result, it.getPath());
                }
                result.add(it.getPath());
            }
        } else {
            Log.e(TAG, "search: " + path + "not exists ");
        }
    }


    /**
     * 根据文件类型 遍历文件
     *
     * @param result
     * @param path
     * @param fileType
     */
    public static void search(List<String> result, String path, String fileType) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files == null || files.length == 0) {
                return;
            }
            for (File it : files) {
                if (it.isHidden()) {
                    continue;
                }
                if (it.isDirectory()) {
                    search(result, it.getPath(), fileType);
                }

                if (it.getPath().endsWith(fileType)) {
                    result.add(it.getPath());
                }
            }
        } else {
            Log.e(TAG, "search: " + path + "not exists ");
        }
    }


    /**
     * 微信 根据文件类型获取 文件列表
     *
     * @param fileType 文件类型， 传入后缀名称 eg：.png ,jpeg
     * @return
     */
    public static List<String> fileTypeToFiles(String fileType) {
        List<String> result = new ArrayList<>();
        search(result, Pictures, fileType);
        search(result, MicroMsg, fileType);
        return removeDuplicateElements(result);
    }

    /**
     * 获取微信内 jpg 图片
     *
     * @return
     */

    public static List<String> jpgs() {
        List<String> result = new ArrayList<>();
        search(result, Pictures, JPG);
        search(result, MicroMsg, JPG);
        return removeDuplicateElements(result);
    }

    /**
     * 获取微信 png 图片
     *
     * @return
     */
    public static List<String> pngs() {
        List<String> result = new ArrayList<>();
        search(result, Pictures, PNG);
        search(result, MicroMsg, PNG);
        return removeDuplicateElements(result);
    }

    /**
     * 获取微信mp3文件
     *
     * @return
     */
    public static List<String> mp3s() {
        List<String> result = new ArrayList<>();
        search(result, Pictures, MP3);
        search(result, MicroMsg, MP3);
        return removeDuplicateElements(result);
    }

    /**
     * 获取微信 mp4 文件
     *
     * @return
     */
    public static List<String> mp4s() {
        List<String> result = new ArrayList<>();
        search(result, Pictures, MP4);
        search(result, MicroMsg, MP4);
        return removeDuplicateElements(result);
    }


    /**
     * 获取微信所有的图片
     *
     * @return
     */
    public static List<String> images() {
        List<String> result = new ArrayList<>();
        search(result, Pictures, PNG);
        search(result, MicroMsg, PNG);
        search(result, Pictures, JPEG);
        search(result, MicroMsg, JPEG);
        search(result, Pictures, JPG);
        search(result, MicroMsg, JPG);
        search(result, Pictures, WEBP);
        search(result, MicroMsg, WEBP);

        return removeDuplicateElements(result);
    }


    /**
     * 获取微信接收文件
     */

    public static List<String> receiveFiles() {
        List<String> result = new ArrayList<>();
        search(result, DOWNLOAD);
        return removeFilesWithoutSuffix(removeDuplicateElements(result));
    }


    /**
     * 获取emoji缓冲数据
     *
     * @return
     */
    public static List<String> emojis() {

        List<String> result = new ArrayList<>();
        List<String> accounts = accounts();

        for (String it : accounts) {
            String path = ANDROID_DATA_MicroMsg + "/" + it + "/" + emoji;
            search(result, path);
        }
        return removeDuplicateElements(result);
    }

    /**
     * /sdcard/Android/data/com.tencent.mm/MicroMsg/be37a7363309141066efd4f524ef1234
     * <p>
     * 获取 /sdcard/Android/data/com.tencent.mm/cache/ 路径下的 md5 用户标识
     *
     * @return
     */
    private static List<String> accounts() {

        List<String> result = new ArrayList<>();


        File file = new File(ANDROID_DATA_MicroMsg);

        if (file.exists()) {
            File[] files = file.listFiles();

            if (files == null || files.length == 0) {
                return result;
            }

            for (File it : files) {

                String fileName = it.getName();
                if (fileName.length() == 32) {
                    result.add(fileName);
                }
            }
        }
        return result;
    }


    /**
     * 获取微信语音聊天
     *
     * @return
     */
    public static List<String> voice2() {
        List<String> result = new ArrayList<>();
        List<String> accounts = accounts();

        for (String it : accounts) {
            String path = ANDROID_DATA_MicroMsg + "/" + it + "/" + voice2;
            search(result, path);
        }
        return removeDuplicateElements(result);
    }
}
