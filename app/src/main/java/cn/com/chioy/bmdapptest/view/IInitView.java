package cn.com.chioy.bmdapptest.view;

import java.io.File;

import cn.com.chioy.bmdapptest.beans.UpgradeInfo;

/**
 * Created by zhaowh on 2017/2/17.
 */

public interface IInitView {
    void showIntro();
    void showUpdateDialog(UpgradeInfo info);

    void onDownloading(long currentSize, long totalSize, float progress, long networkSpeed);
    void onDownloadComplete(File file);
    void onDownloadError(Throwable e);
}
