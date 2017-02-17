package cn.com.chioy.bmdapptest.utils;

import android.content.Context;
import android.widget.Toast;

import cn.com.chioy.bmdapptest.AppConfig;

/**
 * Created by zhaowh on 2017/2/16.
 */

public class ToastUtil {
    private ToastUtil(){}

    public static boolean isShow = AppConfig.SHOW_TOAST;

    public static void showShort(Context context, CharSequence message){
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(Context context, int message)
    {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, CharSequence message)
    {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showLong(Context context, int message)
    {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void show(Context context, CharSequence message, int duration)
    {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

    public static void show(Context context, int message, int duration)
    {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

}
