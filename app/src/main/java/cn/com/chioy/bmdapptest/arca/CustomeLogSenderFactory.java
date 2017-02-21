package cn.com.chioy.bmdapptest.arca;

import android.content.Context;
import android.support.annotation.NonNull;

import org.acra.config.ACRAConfiguration;
import org.acra.sender.HttpSender;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderFactory;

/**
 * Created by zhaowh on 2017/2/21.
 */

public class CustomeLogSenderFactory implements ReportSenderFactory {
    @NonNull
    @Override
    public ReportSender create(@NonNull Context context, @NonNull ACRAConfiguration config) {
        return new CustomeLogSender(config, HttpSender.Method.POST, HttpSender.Type.JSON, null);
    }
}
