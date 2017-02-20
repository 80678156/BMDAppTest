package cn.com.chioy.bmdapptest.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by zhaowh on 2017/2/20.
 */

public class SPUtil {
    private SPUtil(){}

    /**
     * 获取App默认的SharedPreferences
     * @param context
     * @return
     */
    public static SharedPreferences getDefaultSP (Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

}
