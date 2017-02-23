package cn.com.chioy.bmdapptest.view;

import java.io.File;

import cn.com.chioy.bmdapptest.beans.UpgradeInfo;

/**
 * 初始化界面接口View
 * @author zhaowh
 * @Date 2017/2/23
 */

public interface IInitView extends IBaseView{
    void showIntro();
    void showUpdateDialog(UpgradeInfo info);

    void onDownloading(long currentSize, long totalSize, float progress, long networkSpeed);
    void onDownloadComplete(File file);
    void onDownloadError(Throwable e);
}
