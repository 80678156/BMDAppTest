package cn.com.chioy.bmdapptest.model.impl;

import android.content.Context;
import android.content.SharedPreferences;

import cn.com.chioy.bmdapptest.beans.UpdateInfo;
import cn.com.chioy.bmdapptest.model.IInitModel;
import cn.com.chioy.bmdapptest.utils.AppConstant;
import cn.com.chioy.bmdapptest.utils.SPUtil;

/**
 * Created by zhaowh on 2017/2/20.
 */

public class InitModelImpl implements IInitModel {
    @Override
    public void checkAndShowIntro(final Context context, final OnCheckedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = SPUtil.getDefaultSP(context);
                boolean isFirstStart = sp.getBoolean(AppConstant.SP_KEY_FIRST_START, true);
                listener.onCheckIntroDone(isFirstStart);
                sp.edit().putBoolean(AppConstant.SP_KEY_FIRST_START, false).commit();
            }
        }).start();
    }

    @Override
    public void checkAndUpdate(Context context, final OnCheckedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //TODO 请求网络，获取版本更新信息，或者从本地检测是否有增量更新包
                UpdateInfo info = new UpdateInfo();
                info.setForceUpdate(false);
                info.setVersionCode(2);
                info.setDescription("新版本描述信息：添加了新功能，fix bugs");
                listener.onCheckUpdateDone(info);
            }
        }).start();
    }
}
