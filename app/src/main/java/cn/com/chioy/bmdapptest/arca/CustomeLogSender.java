package cn.com.chioy.bmdapptest.arca;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.acra.ACRAConstants;
import org.acra.ReportField;
import org.acra.collections.ImmutableSet;
import org.acra.collector.CrashReportData;
import org.acra.config.ACRAConfiguration;
import org.acra.sender.HttpSender;
import org.acra.sender.ReportSenderException;
import org.acra.util.HttpRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.com.chioy.bmdapptest.base.AppConfig;
import cn.com.chioy.bmdapptest.utils.LogUtil;

/**
 * Created by zhaowh on 2017/2/21.
 */

public class CustomeLogSender extends HttpSender {

    private final ACRAConfiguration config;
    @Nullable
    private final Uri mFormUri;
    private final Map<ReportField, String> mMapping;
    private final Method mMethod;
    private final Type mType;
    @Nullable
    private String mUsername;
    @Nullable
    private String mPassword;


    public CustomeLogSender(@NonNull ACRAConfiguration config, @NonNull Method method, @NonNull Type type, @Nullable Map<ReportField, String> mapping) {
        this(config, method, type, null, mapping);
    }


    public CustomeLogSender(@NonNull ACRAConfiguration config, @NonNull Method method, @NonNull Type type, @Nullable String formUri, @Nullable Map<ReportField, String> mapping) {
        super(config, method, type, null, mapping);
        this.config = config;
        mMethod = method;
        mFormUri = (formUri == null) ? null : Uri.parse(formUri);
        mMapping = mapping;
        mType = type;
        mUsername = null;
        mPassword = null;
    }

    /**
     * <p>
     * Set credentials for this HttpSender that override (if present) the ones
     * set globally.
     * </p>
     *
     * @param username
     *            The username to set for HTTP Basic Auth.
     * @param password
     *            The password to set for HTTP Basic Auth.
     */
    @SuppressWarnings( "unused" )
    public void setBasicAuth(@Nullable String username, @Nullable String password) {
        mUsername = username;
        mPassword = password;
    }

    @Override
    public void send(@NonNull Context context, @NonNull CrashReportData report) throws ReportSenderException {
        LogUtil.e("send!!!!!!!!!!!!!!");
        try {
            final String reportAsString;
            switch (mType) {
                case JSON:
                    reportAsString = report.toJSON().toString();
                    break;
                case FORM:
                default:
                    final Map<String, String> finalReport = remap(report);
                    reportAsString = HttpRequest.getParamsAsFormString(finalReport);
                    break;
            }
            File logDir = new File( Environment.getExternalStorageDirectory(), AppConfig.LOG_DIR);
            LogUtil.e("send!!!!!!!!!!!!!!logDir:"+logDir);
            LogUtil.e("send!!!!!!!!!!!!!!reportAsString:"+reportAsString);
            if(!logDir.exists()){
                logDir.mkdirs();
            }
            if(logDir!=null && logDir.exists() && reportAsString!=null){
                File logFile = new File(logDir, AppConfig.LOG_FILE_NAME);
                FileOutputStream fos = new FileOutputStream(logFile, true);
                fos.write(reportAsString.getBytes());
                fos.flush();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*try {
            URL reportUrl = mFormUri == null ? new URL(config.formUri()) : new URL(mFormUri.toString());
            if (ACRA.DEV_LOGGING) ACRA.log.d(LOG_TAG, "Connect to " + reportUrl.toString());

            final String login = mUsername != null ? mUsername : isNull(config.formUriBasicAuthLogin()) ? null : config.formUriBasicAuthLogin();
            final String password = mPassword != null ? mPassword : isNull(config.formUriBasicAuthPassword()) ? null : config.formUriBasicAuthPassword();

            final HttpRequest request = new HttpRequest(config);
            request.setConnectionTimeOut(config.connectionTimeout());
            request.setSocketTimeOut(config.socketTimeout());
            request.setLogin(login);
            request.setPassword(password);
            request.setHeaders(config.getHttpHeaders());

            // Generate report body depending on requested type
            final String reportAsString;
            switch (mType) {
                case JSON:
                    reportAsString = report.toJSON().toString();
                    break;
                case FORM:
                default:
                    final Map<String, String> finalReport = remap(report);
                    reportAsString = HttpRequest.getParamsAsFormString(finalReport);
                    break;
            }

            // Adjust URL depending on method
            switch (mMethod) {
                case POST:
                    break;
                case PUT:
                    reportUrl = new URL(reportUrl.toString() + '/' + report.getProperty(ReportField.REPORT_ID));
                    break;
                default:
                    throw new UnsupportedOperationException("Unknown method: " + mMethod.name());
            }
            request.send(context, reportUrl, mMethod, reportAsString, mType);

        } catch (@NonNull IOException e) {
            throw new ReportSenderException("Error while sending " + config.reportType()
                    + " report via Http " + mMethod.name(), e);
        } catch (@NonNull JSONReportBuilder.JSONReportException e) {
            throw new ReportSenderException("Error while sending " + config.reportType()
                    + " report via Http " + mMethod.name(), e);
        }*/
    }

    @NonNull
    private Map<String, String> remap(@NonNull Map<ReportField, String> report) {

        Set<ReportField> fields = config.getReportFields();
        if (fields.isEmpty()) {
            fields = new ImmutableSet<ReportField>(ACRAConstants.DEFAULT_REPORT_FIELDS);
        }

        final Map<String, String> finalReport = new HashMap<String, String>(report.size());
        for (ReportField field : fields) {
            if (mMapping == null || mMapping.get(field) == null) {
                finalReport.put(field.toString(), report.get(field));
            } else {
                finalReport.put(mMapping.get(field), report.get(field));
            }
        }
        return finalReport;
    }

    private boolean isNull(@Nullable String aString) {
        return aString == null || ACRAConstants.NULL_VALUE.equals(aString);
    }
}
