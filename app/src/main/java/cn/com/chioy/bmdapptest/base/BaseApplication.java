package cn.com.chioy.bmdapptest.base;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import java.io.File;
import java.io.IOException;

import cn.com.chioy.bmdapptest.BuildConfig;
import cn.com.chioy.bmdapptest.R;
import cn.com.chioy.bmdapptest.arca.CustomeLogSenderFactory;
import cn.com.chioy.bmdapptest.dao.DaoMaster;
import cn.com.chioy.bmdapptest.dao.DaoSession;

import static org.acra.ReportField.ANDROID_VERSION;
import static org.acra.ReportField.APP_VERSION_NAME;
import static org.acra.ReportField.CUSTOM_DATA;
import static org.acra.ReportField.LOGCAT;
import static org.acra.ReportField.PHONE_MODEL;
import static org.acra.ReportField.STACK_TRACE;

/**
 * Created by zhaowh on 2017/2/16.
 */
//ACRA log文件上传接口URI
@ReportsCrashes(
        formUri = "http://192.168.0.23:8080/AppTestService/LogServlet",
        mode = ReportingInteractionMode.TOAST,
        resToastText = R.string.crash_toast_text,
        reportSenderFactoryClasses = {CustomeLogSenderFactory.class} ,
        customReportContent = {APP_VERSION_NAME, ANDROID_VERSION, PHONE_MODEL, CUSTOM_DATA, STACK_TRACE, LOGCAT }
)
public class BaseApplication extends Application {
    public static BaseApplication instances;

    //Andfix PatchManager初始化
    private PatchManager mPatchManager;

    //初始化GreenDao，需要先创建Entity重新编译，GreenDao会自动生成DaoMaster和DaoSession
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public static BaseApplication getInstances(){
        return instances;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        //初始化ACRA框架
        ACRA.init(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;

        //初始化Andfix
        Log.e("ZWH","application onCreate!!!!");
        mPatchManager = new PatchManager(getApplicationContext());
        mPatchManager.init(BuildConfig.VERSION_CODE+"");
        mPatchManager.loadPatch();

        //初始化GreenDao，需要先创建Entity重新编译，GreenDao会自动生成DaoMaster和DaoSession
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();

        try {
            //初始化OKgo
            HttpHeaders headers = new HttpHeaders();
            //headers.put();
            OkGo.init(this);
            OkGo.getInstance().addCommonHeaders(headers)
                    .setConnectTimeout(20000)
                    .setRetryCount(3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        checkAndUpdateApp();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    /**
     * 1、检测是否有更新文件 2、如果有，则进行热修复
     * apkpatch -f app-release.apk -t app-old.apk -o patch -k bmd.jks -p bmdbmd -a bmdtest -e bmdbmd
     */
    private void checkAndUpdateApp() {
        //TODO 1、检测是否有更新文件 2、如果有，则进行热修复
        try {
            //String sdcardDir = Environment.getExternalStorageDirectory().toString();
            //Log.e("ZWH","sdcard:"+sdcardDir);

            File pathFile = new File(getFilesDir(),  "test0000.apatch");
            Log.e("ZWH","pathFile:"+pathFile.exists());
            if(pathFile!=null && pathFile.exists()){
                File apatchDir = new File(getFilesDir(), "apatch");
                if(!apatchDir.exists()){
                    apatchDir.mkdirs();
                }
                mPatchManager.addPatch(pathFile.getAbsolutePath());
                Log.e("ZWH","add patch!!!!");
                boolean result = pathFile.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
