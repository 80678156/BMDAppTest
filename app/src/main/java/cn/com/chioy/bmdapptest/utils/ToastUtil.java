package cn.com.chioy.bmdapptest.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zhaowh on 2017/2/16.
 */

public class ToastUtil {
    private ToastUtil(){}

    public static void showShort(Context context, CharSequence message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(Context context, int message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, CharSequence message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showLong(Context context, int message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void show(Context context, CharSequence message, int duration){
        Toast.makeText(context, message, duration).show();
    }

    public static void show(Context context, int message, int duration){
        Toast.makeText(context, message, duration).show();
    }

}
