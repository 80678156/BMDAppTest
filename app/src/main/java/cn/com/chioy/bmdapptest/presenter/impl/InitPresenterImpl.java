package cn.com.chioy.bmdapptest.presenter.impl;

import android.content.Context;
import android.util.Log;

import java.io.File;

import cn.com.chioy.bmdapptest.beans.UpdateInfo;
import cn.com.chioy.bmdapptest.model.IInitModel;
import cn.com.chioy.bmdapptest.model.impl.InitModelImpl;
import cn.com.chioy.bmdapptest.presenter.IInitPresenter;
import cn.com.chioy.bmdapptest.view.IInitView;

/**
 * Created by zhaowh on 2017/2/20.
 */

public class InitPresenterImpl implements IInitPresenter, IInitModel.OnCheckedListener , IInitModel.OnDownloadListener{

    private IInitView mInitView;
    private IInitModel mInitModel;
    private Context mContext;

    public InitPresenterImpl(Context context, IInitView initView){
        mContext = context;
        mInitView = initView;
        mInitModel = new InitModelImpl();
    }

    @Override
    public void checkAndShowIntro(Context context) {
        mInitModel.checkAndShowIntro(context, this);
    }

    @Override
    public void checkAndUpdate(Context context) {
        mInitModel.checkAndUpdate(context, this);
    }

    @Override
    public void downloadUpdate(String path) {
        mInitModel.downloadUpdate(path, this);
    }

    @Override
    public void onCheckIntroDone(boolean showIntro) {
        if(showIntro){
            mInitView.showIntro();
        }else{
            mInitModel.checkAndUpdate(mContext, this);
        }
    }

    @Override
    public void onCheckUpdateDone(UpdateInfo info) {
        mInitView.showUpdateDialog(info);
    }

    @Override
    public void onDownloading(long currentSize, long totalSize, float progress, long networkSpeed) {
        mInitView.onDownloading(currentSize, totalSize, progress, networkSpeed);
        Log.e("ZWH", "onDownloadComplete() progress:"+progress+" networkSpeed:"+networkSpeed+" currentSize:"+currentSize+" totalSize:"+totalSize);
    }

    @Override
    public void onDownloadComplete(File file) {
        mInitView.onDownloadComplete(file);
        Log.e("ZWH", "onDownloadComplete() file:"+file.getAbsolutePath());
    }

    @Override
    public void onDownloadError(Throwable e) {
        //e.printStackTrace();
        mInitView.onDownloadError(e);
    }

}
