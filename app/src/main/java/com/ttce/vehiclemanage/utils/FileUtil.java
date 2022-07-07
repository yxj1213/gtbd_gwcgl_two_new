package com.ttce.vehiclemanage.utils;

import android.text.TextUtils;

import com.ttce.vehiclemanage.bean.MonitorResponseBean;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2018/9/19.
 */

public class FileUtil {
    /**
     * 删除文件夹
     */
    public static void delFolder(String folderPath) {
        if (TextUtils.isEmpty(folderPath)) {
            return;
        }

        try {
            delAllFile(folderPath);

            File myFilePath = new File(folderPath);

            myFilePath.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件夹里面的所有文件
     */
    public static void delAllFile(String path) {
        File file = new File(path);

        if (!file.exists()) {
            return;
        }

        if (file.isFile()) {
            file.delete();

            return;
        }

        String[] tempList = file.list();
        File temp = null;

        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }

            if (temp.isFile()) {
                temp.delete();
            }

            if (temp.isDirectory()) {
                delFolder(path + "/" + tempList[i]);
            }
        }
    }

    /**
     * 转换文件大小
     */
    public static String formetFileSize(long size) {
        DecimalFormat df = new DecimalFormat("#.00");
        String result;
        if (size < 1024) {
            result = df.format((double) size) + "B";
        } else if (size < 1048576) {
            result = df.format((double) size / 1024) + "K";
        } else if (size < 1073741824) {
            result = df.format((double) size / 1048576) + "M";
        } else {
            result = df.format((double) size / 1073741824) + "G";
        }

        if (TextUtils.isEmpty(result) || result.equals(".00B")) {
            return "0B";
        }

        if (result.contains(".00")) {
            return result.replace(".00", "");
        }

        return result;
    }

    /**
     * 取得文件大小
     */
    public static long getFileSize(File f) throws Exception {
        try {
            long s = 0;
            if (f.exists()) {
                FileInputStream fis = new FileInputStream(f);
                s = fis.available();
                fis.close();
            }
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取文件夹大小
     */
    public static long getFolderSize(File file) {
        long size = 0;

        if (file == null || !file.exists()) {
            return size;
        }

        try {
            File[] fileList = file.listFiles();

            for (File aFileList : fileList) {
                if (aFileList == null) {
                    continue;
                }

                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);

                } else {
                    size = size + getFileSize(aFileList);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return size;
    }
}
