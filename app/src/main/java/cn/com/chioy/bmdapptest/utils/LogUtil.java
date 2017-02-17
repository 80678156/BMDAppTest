package cn.com.chioy.bmdapptest.utils;

import android.util.Log;

import cn.zhaowh.mytest.AppConfig;

/**
 * Created by zhaowh on 2017/2/16.
 */

public class LogUtil {
    static String className;//类名
    static String methodName;//方法名
    static int lineNumber;//行数

    private LogUtil(){
        /* Protect from instantiations */
    }

    public static boolean isDebuggable() {
        return AppConfig.SHOW_LOG;
    }

    /**
     * 输出格式类似于 [方法名称()][line:行号]log信息
     * @param log
     * @return
     */
    private static String createLog( String log ) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[").append(methodName);
        buffer.append("()][line:").append(lineNumber).append("]");
        buffer.append(log);
        return buffer.toString();
    }

    private static void getMethodNames(StackTraceElement[] sElements){
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }


    public static void e(String message){
        if (!isDebuggable())
            return;

        // Throwable instance must be created before any methods
        getMethodNames(new Throwable().getStackTrace());
        Log.e(className, createLog(message));
    }


    public static void i(String message){
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.i(className, createLog(message));
    }

    public static void d(String message){
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.d(className, createLog(message));
    }

    public static void v(String message){
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.v(className, createLog(message));
    }

    public static void w(String message){
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.w(className, createLog(message));
    }

    public static void wtf(String message){
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.wtf(className, createLog(message));
    }
}
