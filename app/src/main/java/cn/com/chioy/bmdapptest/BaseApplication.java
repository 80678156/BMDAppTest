package cn.com.chioy.bmdapptest;

import android.app.Application;
import android.content.Context;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

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

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        ACRA.init(this); //初始化ACRA框架
    }
}
