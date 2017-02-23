package cn.com.chioy.bmdapptest.base;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import cn.com.chioy.bmdapptest.R;
import cn.com.chioy.bmdapptest.arca.CustomeLogSenderFactory;
import cn.com.chioy.bmdapptest.dao.DaoMaster;
import cn.com.chioy.bmdapptest.dao.DaoSession;
import cn.com.chioy.bmdapptest.utils.UIUtil;

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
    //private PatchManager mPatchManager;

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

        //初始化Bugly_Upgrade begin
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
        // 安装tinker
        Beta.installTinker();
        //初始化Bugly_Upgrade end

        //初始化ACRA框架
        ACRA.init(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;

        //初始化Bugly_Upgrade
        initBuglyAndUpgrade();

        Log.e("ZWH","application onCreate!!!!");
        /*//初始化Andfix热修复， 使用bugly项目的tinker代替
        mPatchManager = new PatchManager(getApplicationContext());
        mPatchManager.init(BuildConfig.VERSION_CODE+"");
        mPatchManager.loadPatch();*/

        //初始化LeakCanary
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        //初始化GreenDao
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


    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    private void initBuglyAndUpgrade(){
        Context context = getApplicationContext();
        String packageName = context.getPackageName();
        String processName = UIUtil.getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化crashreport_upgrade
        Bugly.init(context, AppConfig.BUGLY_APP_ID, false, strategy);
    }

    /**
     * 1、检测是否有更新文件 2、如果有，则进行热修复
     * apkpatch -f app-release.apk -t app-old.apk -o patch -k bmd.jks -p bmdbmd -a bmdtest -e bmdbmd
     */
    /*private void checkAndUpdateApp() {
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
    }*/
}
