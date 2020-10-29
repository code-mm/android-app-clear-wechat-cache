package com.ms.module.wechat.clear.utils;

import java.text.DecimalFormat;

public class ByteSizeToStringUnitUtils {

    public static String byteToStringUnit(long size) {
        StringBuffer bytes = new StringBuffer();
        DecimalFormat format = new DecimalFormat("###.0");
        if (size >= 1024 * 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0 * 1024.0));
            bytes.append(format.format(i)).append("GB");
        } else if (size >= 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0));
            bytes.append(format.format(i)).append("MB");
        } else if (size >= 1024) {
            double i = (size / (1024.0));
            bytes.append(format.format(i)).append("KB");
        } else if (size < 1024) {
            if (size <= 0) {
                bytes.append("0B");
            } else {
                bytes.append((int) size).append("B");
            }
        }
        return bytes.toString();
    }


    public static String[] byteToStringUnitS(long size) {

        DecimalFormat format = new DecimalFormat("###.0");
        if (size >= 1024 * 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0 * 1024.0));
            return new String[]{format.format(i), "GB"};
        } else if (size >= 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0));
            return new String[]{format.format(i), "MB"};
        } else if (size >= 1024) {
            double i = (size / (1024.0));
            return new String[]{format.format(i), "KB"};
        } else if (size < 1024) {
            if (size <= 0) {
                return new String[]{format.format(0), "B"};
            } else {
                return new String[]{format.format(size), "B"};
            }
        }
        return new String[]{};
    }
}
