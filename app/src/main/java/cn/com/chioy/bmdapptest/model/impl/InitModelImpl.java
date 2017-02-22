package cn.com.chioy.bmdapptest.model.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;

import java.io.File;

import cn.com.chioy.bmdapptest.beans.UpgradeInfo;
import cn.com.chioy.bmdapptest.model.IInitModel;
import cn.com.chioy.bmdapptest.utils.AppConstant;
import cn.com.chioy.bmdapptest.utils.SPUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zhaowh on 2017/2/20.
 */

public class InitModelImpl implements IInitModel {
    @Override
    public void checkAndShowIntro(final Context context, final OnCheckedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = SPUtil.getDefaultSP(context);
                boolean isFirstStart = sp.getBoolean(AppConstant.SP_KEY_FIRST_START, true);
                listener.onCheckIntroDone(isFirstStart);
                sp.edit().putBoolean(AppConstant.SP_KEY_FIRST_START, false).commit();
            }
        }).start();
    }

    @Override
    public void checkAndUpdate(Context context, final OnCheckedListener listener) {
        Observable.create(new ObservableOnSubscribe<UpgradeInfo>() {
            @Override
            public void subscribe(ObservableEmitter<UpgradeInfo> e) throws Exception {
                //TODO 请求网络，获取版本更新信息，或者从本地检测是否有增量更新包
                try {
                    UpgradeInfo info = new UpgradeInfo();
                    info.setForceUpdate(false);
                    info.setVersionCode(2);
                    info.setDescription("新版本描述信息：添加了新功能，fix bugs");
                    Thread.sleep(4000);
                    e.onNext(info);
                } catch (InterruptedException e1) {
                    e.onError(e1);
                }
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<UpgradeInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(UpgradeInfo value) {
                listener.onCheckUpdateDone(value);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    public void downloadUpdate(String path, final OnDownloadListener listener) {
        OkGo.get(path)
                .tag(this)
                .execute(new FileCallback() {
                    @Override
                    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        listener.onDownloading(currentSize, totalSize, progress, networkSpeed);
                    }
                    @Override
                    public void onSuccess(File file, Call call, Response response) {
                        listener.onDownloadComplete(file);
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        listener.onDownloadError(e);
                    }
                });
    }
}
