package cn.com.chioy.bmdapptest.base;

/**
 * Created by zhaowh on 2017/2/16.
 */

public class AppConfig {

    public static final String BUGLY_APP_ID = "5ff25a4cbf";
    /** 是否打印Log信息*/
    public static final boolean SHOW_LOG = true;
    public static final String test = "test";

    public static final String LOG_DIR = "bmd/logs/";
    public static final String LOG_FILE_NAME = "crash.txt";

    /** 是否开启自动检测升级*/
    public static boolean AUTO_CHECK_UPGRADE = true;
    /**是否在WIFI下自动下载升级包*/
    public static boolean AUTO_DOWNLOAD_ON_WIFI = false;
    /** 是否开启热升级*/
    public static boolean ENABLE_HOT_FIX = true;
}
