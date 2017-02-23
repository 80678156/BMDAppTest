package cn.com.chioy.bmdapptest.utils;

import android.util.Log;

import com.tencent.bugly.crashreport.BuglyLog;

import cn.com.chioy.bmdapptest.base.AppConfig;


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

    public static boolean showLog() {
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
        if (!showLog())
            return;

        // Throwable instance must be created before any methods
        getMethodNames(new Throwable().getStackTrace());
        Log.e(className, createLog(message));
    }


    public static void i(String message){
        if (!showLog())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.i(className, createLog(message));
    }

    public static void d(String message){
        if (!showLog())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.d(className, createLog(message));
    }

    public static void v(String message){
        if (!showLog())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.v(className, createLog(message));
    }

    public static void w(String message){
        if (!showLog())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.w(className, createLog(message));
    }

    public static void wtf(String message){
        if (!showLog())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.wtf(className, createLog(message));
    }
    public static void e(String tag, String message){
        if (!showLog())
            return;

        // Throwable instance must be created before any methods
        getMethodNames(new Throwable().getStackTrace());
        Log.e(tag, createLog(message));
    }


    public static void i(String tag, String message){
        if (!showLog())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.i(tag, createLog(message));
    }

    public static void d(String tag, String message){
        if (!showLog())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.d(tag, createLog(message));
    }

    public static void v(String tag, String message){
        if (!showLog())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.v(tag, createLog(message));
    }

    public static void w(String tag, String message){
        if (!showLog())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.w(tag, createLog(message));
    }

    public static void wtf(String tag, String message){
        if (!showLog())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.wtf(tag, createLog(message));
    }
    public static void reportE(String message){
        if (!showLog())
            return;

        // Throwable instance must be created before any methods
        getMethodNames(new Throwable().getStackTrace());
        BuglyLog.e(className, createLog(message));
    }


    public static void reportI(String message){
        if (!showLog())
            return;

        getMethodNames(new Throwable().getStackTrace());
        BuglyLog.i(className, createLog(message));
    }

    public static void reportD(String message){
        if (!showLog())
            return;

        getMethodNames(new Throwable().getStackTrace());
        BuglyLog.d(className, createLog(message));
    }

    public static void reportV(String message){
        if (!showLog())
            return;

        getMethodNames(new Throwable().getStackTrace());
        BuglyLog.v(className, createLog(message));
    }

    public static void reportW(String message){
        if (!showLog())
            return;

        getMethodNames(new Throwable().getStackTrace());
        BuglyLog.w(className, createLog(message));
    }
    public static void reportE(String tag, String message){
        if (!showLog())
            return;

        // Throwable instance must be created before any methods
        getMethodNames(new Throwable().getStackTrace());
        BuglyLog.e(tag, createLog(message));
    }


    public static void reportI(String tag, String message){
        if (!showLog())
            return;

        getMethodNames(new Throwable().getStackTrace());
        BuglyLog.i(tag, createLog(message));
    }

    public static void reportD(String tag, String message){
        if (!showLog())
            return;

        getMethodNames(new Throwable().getStackTrace());
        BuglyLog.d(tag, createLog(message));
    }

    public static void reportV(String tag, String message){
        if (!showLog())
            return;

        getMethodNames(new Throwable().getStackTrace());
        BuglyLog.v(tag, createLog(message));
    }

    public static void reportW(String tag, String message){
        if (!showLog())
            return;

        getMethodNames(new Throwable().getStackTrace());
        BuglyLog.w(tag, createLog(message));
    }
}
