package cn.com.chioy.bmdapptest.model;

import android.content.Context;

import java.io.File;

import cn.com.chioy.bmdapptest.beans.UpgradeInfo;

/**
 * Created by zhaowh on 2017/2/20.
 */

public interface IInitModel {
    void checkAndShowIntro(Context context, OnCheckedListener listener);
    void checkAndUpdate(Context context, OnCheckedListener listener);
    void downloadUpdate(String path, OnDownloadListener listener);

    interface OnCheckedListener{
        void onCheckIntroDone(boolean showIntro);
        void onCheckUpdateDone(UpgradeInfo info);
    }
    interface OnDownloadListener{
        void onDownloading(long currentSize, long totalSize, float progress, long networkSpeed);
        void onDownloadComplete(File file);
        void onDownloadError(Throwable e);
    }
}
