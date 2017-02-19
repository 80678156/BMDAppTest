package cn.com.chioy.bmdapptest.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import java.io.File;
import java.io.IOException;

import cn.com.chioy.bmdapptest.BuildConfig;
import cn.com.chioy.bmdapptest.R;
import cn.com.chioy.bmdapptest.utils.LogUtil;
import cn.com.chioy.bmdapptest.utils.ToastUtil;

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
        customReportContent = {APP_VERSION_NAME, ANDROID_VERSION, PHONE_MODEL, CUSTOM_DATA, STACK_TRACE, LOGCAT }
)
public class BaseApplication extends Application {
    //Andfix PatchManager初始化
    private PatchManager mPatchManager;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        //初始化ACRA框架
        ACRA.init(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Andfix
        Log.e("ZWH","application onCreate!!!!");
        mPatchManager = new PatchManager(getApplicationContext());
        mPatchManager.init(BuildConfig.VERSION_CODE+"");
        mPatchManager.loadPatch();

        checkAndUpdateApp();
    }

    /**
     * 1、检测是否有更新文件 2、如果有，则进行热修复
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
