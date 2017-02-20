package cn.com.chioy.bmdapptest.view;

import cn.com.chioy.bmdapptest.beans.UpdateInfo;

/**
 * Created by zhaowh on 2017/2/17.
 */

public interface IInitView {
    void showIntro();
    void showUpdateDialog(UpdateInfo info);
}
