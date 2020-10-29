package com.ms.module.wechat.clear.utils;


import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 微信清理核心工具类
 */
public class WeChatClearUtils {

    private static final String TAG = "WeChatClearUtils";
    // 微信包名称
    //com.tencent.mm
    private static final String WECHAT_PACKAGE_NAME = "com.tencent.mm";

    // 图片
    public static final String PICTURES = "/sdcard/Pictures/WeiXin";

    // 外部 缓冲数据
    public static final String MICROMSG = "/sdcard/tencent/MicroMsg";

    // 内部 缓冲数据
    public static final String ANDROID_DATA_MICROMSG = "/sdcard/Android/data/com.tencent.mm/MicroMsg";

    // 缓冲路径
    public static final String CACHE = "/sdcard/Android/data/com.tencent.mm/cache";

    // 微信接收文件的目录
    public static final String DOWNLOAD = "/sdcard/Android/data/com.tencent.mm/MicroMsg/Download";


    public static final String ATTACHMENT = "attachment";
    // 头像
    public static final String AVATAR = "avatar";
    public static final String BIZCACHE = "bizcache";
    public static final String BIZIMG = "bizimg";
    public static final String BRANDICON = "brandicon";
    public static final String DRAFT = "draft";
    //emoji
    public static final String EMOJI = "emoji";
    public static final String FAVOFFLINE = "favoffline";
    public static final String FAVORITE = "favorite";
    public static final String IMAGE = "image";
    public static final String IMAGE2 = "image2";
    public static final String MAILAPP = "mailapp";
    public static final String MUSIC = "music";
    public static final String ONEDAY = "oneday";
    public static final String OPENAPI = "openapi";
    public static final String OPENAPI_CACHE = "openapi_cache";
    public static final String OPENIM = "openim";
    public static final String _PACKAGE = "package";
    public static final String PATMSG = "patmsg";
    public static final String RECBIZ = "recbiz";
    public static final String RECORD = "record";
    public static final String SCANNER = "scanner";
    public static final String VIDEO = "video";
    public static final String VOICE = "voice";
    public static final String VOICE2 = "voice2";
    public static final String VOICEREMIND = "voiceremind";
    public static final String WEBCANVASCACHE = "webcanvascache";
    public static final String WENOTE = "wenote";


    public static final String JPG = ".jpg";
    public static final String PNG = ".png";
    public static final String JPEG = ".jpeg";
    public static final String WEBP = ".webp";
    public static final String MP3 = ".mp3";
    public static final String MP4 = ".mp4";
    public static final String AVI = ".avi";
    public static final String PDF = ".pdf";


    /**
     * 去除List<String>中重复元素
     *
     * @param data
     * @return
     */
    public static List<String> removeDuplicateElements(List<String> data) {
        HashSet hashSetClear = new HashSet(data);
        data.clear();
        data.addAll(hashSetClear);
        return data;
    }

    /**
     * 去除没有后缀的文件
     */
    public static List<String> removeFilesWithoutSuffix(List<String> list) {
        List<String> result = new ArrayList<>();
        for (String it : list) {
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
        if (file == null) {
            return 0;
        }
        long time = file.lastModified();
        return time;
    }

    /**
     * 根据文件路径获取 文件创建时间
     *
     * @param path
     * @return
     */
    public static long getFileLastModifiedTime(String path) {
        if (path == null || "".equals(path)) {
            return 0;
        }

        File file = new File(path);
        if (file.exists()) {
            long time = file.lastModified();
            return time;
        }
        return 0;
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

                if (it.exists() && it.isFile() && !it.isDirectory()) {
                    result.add(it.getPath());
                }
            }
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
        search(result, PICTURES, fileType);
        search(result, MICROMSG, fileType);
        return removeDuplicateElements(result);
    }

    /**
     * 获取微信内 jpg 图片
     *
     * @return
     */

    public static List<String> jpg() {
        List<String> result = new ArrayList<>();
        search(result, PICTURES, JPG);
        search(result, MICROMSG, JPG);
        return removeDuplicateElements(result);
    }

    /**
     * 获取微信 png 图片
     *
     * @return
     */
    public static List<String> png() {
        List<String> result = new ArrayList<>();
        search(result, PICTURES, PNG);
        search(result, MICROMSG, PNG);
        return removeDuplicateElements(result);
    }

    /**
     * 获取微信mp3文件
     *
     * @return
     */
    public static List<String> mp3() {
        List<String> result = new ArrayList<>();
        search(result, PICTURES, MP3);
        search(result, MICROMSG, MP3);
        return removeDuplicateElements(result);
    }

    /**
     * 获取微信 mp4 文件
     *
     * @return
     */
    public static List<String> mp4() {
        List<String> result = new ArrayList<>();
        search(result, PICTURES, MP4);
        search(result, MICROMSG, MP4);
        return removeDuplicateElements(result);
    }

    /**
     * avi 文件
     *
     * @return
     */
    public static List<String> avi() {
        List<String> result = new ArrayList<>();
        search(result, PICTURES, AVI);
        search(result, MICROMSG, AVI);
        return removeDuplicateElements(result);
    }


    /**
     * 获取视频文件
     *
     * @return
     */
    public static List<String> video() {
        List<String> result = new ArrayList<>();
        search(result, PICTURES, MP4);
        search(result, MICROMSG, MP4);
        return removeDuplicateElements(result);
    }


    /**
     * 获取微信所有的图片
     *
     * @return
     */
    public static List<String> image() {
        List<String> result = new ArrayList<>();
        search(result, PICTURES, PNG);
        search(result, MICROMSG, PNG);
        search(result, PICTURES, JPEG);
        search(result, MICROMSG, JPEG);
        search(result, PICTURES, JPG);
        search(result, MICROMSG, JPG);
        search(result, PICTURES, WEBP);
        search(result, MICROMSG, WEBP);

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
    public static List<String> emoji() {

        List<String> result = new ArrayList<>();
        List<String> accounts = accounts();

        for (String it : accounts) {
            String path = ANDROID_DATA_MICROMSG + "/" + it + "/" + EMOJI;
            search(result, path);
        }
        List<String> newResult = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).contains("_panel_enable") || result.get(i).contains("_cover")) {
                newResult.add(result.get(i));
            }
        }
        return removeDuplicateElements(newResult);
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
        File file = new File(ANDROID_DATA_MICROMSG);
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
            String path = ANDROID_DATA_MICROMSG + "/" + it + "/" + VOICE2;
            search(result, path);
        }
        return removeDuplicateElements(result);
    }

    /**
     * 垃圾文件判定：
     * /sdcard/Android/data/com.tencent.mm/MicroMsg/crash
     * /sdcard/Android/data/com.tencent.mm/MicroMsg/xlog
     * <p>
     * 垃圾文件
     *
     * @return
     */
    public static List<String> rubbish() {
        List<String> result = new ArrayList<>();
        String path = ANDROID_DATA_MICROMSG + "/crash";
        search(result, path);
        search(result, ANDROID_DATA_MICROMSG + "/wxacache");
        search(result, ANDROID_DATA_MICROMSG + "/WebNetFile");
        search(result, ANDROID_DATA_MICROMSG + "/CheckResUpdate");
        return removeDuplicateElements(result);
    }

    /**
     * 缓冲文件
     *
     * @return
     */
    public static List<String> cache() {
        List<String> result = new ArrayList<>();
        search(result, "/sdcard/Android/data/com.tencent.mm/cache");
        return result;
    }


    /**
     * 朋友圈缓冲
     *
     * @return
     */
    public static List<String> friendCache() {
        List<String> result = new ArrayList<>();
        String path = ANDROID_DATA_MICROMSG + "/crash";
        search(result, path);
        return result;
    }


    /**
     * 获取单个集合路径文件总大小
     *
     * @param datas
     * @return
     */
    public static long getFileLength(List<String> datas) {
        if (datas == null) {
            return 0;
        }
        long count = 0;
        for (String it : datas) {
            count += new File(it).length();
        }
        return count;
    }


    /**
     * 计算多个路径集合的文件总大小
     *
     * @param lists
     * @return
     */
    public static long getFileLength(List<String>... lists) {
        long count = 0;
        for (int i = 0; i < lists.length; i++) {
            List<String> list = lists[i];
            for (String it : list) {
                count += new File(it).length();
            }
        }
        return count;
    }

    /**
     * 获取文件大小
     *
     * @param path
     * @return
     */
    public static long getFileLength(String path) {
        return new File(path).length();
    }

    /**
     * 删除文件
     *
     * @param path
     * @return
     */
    public static boolean delete(String path) {
        if (path == null || "".equals(path)) {
            return false;
        }
        File file = new File(path);
        return delete(file);
    }


    /**
     * 删除文件
     *
     * @param file
     * @return
     */
    public static boolean delete(File file) {
        if (file != null) {
            if (file.exists()) {
                boolean delete = file.delete();
                return delete;
            }
        }
        return false;
    }
}