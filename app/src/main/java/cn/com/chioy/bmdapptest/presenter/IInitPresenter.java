package cn.com.chioy.bmdapptest.presenter;

import android.content.Context;

/**
 * 提供初始化模块相关交互功能
 * @author zhaowh
 * @Date 2017/2/23
 */

public interface IInitPresenter extends IBasePresenter {
    void checkAndShowIntro(Context context);
    void checkAndUpdate(Context context);
    void downloadUpdate(String path);
}
