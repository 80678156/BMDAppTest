package cn.com.chioy.bmdapptest.model;

import android.content.Context;

import cn.com.chioy.bmdapptest.beans.UpdateInfo;

/**
 * Created by zhaowh on 2017/2/20.
 */

public interface IInitModel {
    void checkAndShowIntro(Context context, OnCheckedListener listener);
    void checkAndUpdate(Context context, OnCheckedListener listener);

    interface OnCheckedListener{
        void onCheckIntroDone(boolean showIntro);
        void onCheckUpdateDone(UpdateInfo info);
    }
}
