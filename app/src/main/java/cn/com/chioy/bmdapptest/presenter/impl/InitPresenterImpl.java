package cn.com.chioy.bmdapptest.presenter.impl;

import android.content.Context;

import cn.com.chioy.bmdapptest.beans.UpdateInfo;
import cn.com.chioy.bmdapptest.model.IInitModel;
import cn.com.chioy.bmdapptest.model.impl.InitModelImpl;
import cn.com.chioy.bmdapptest.presenter.IInitPresenter;
import cn.com.chioy.bmdapptest.view.IInitView;

/**
 * Created by zhaowh on 2017/2/20.
 */

public class InitPresenterImpl implements IInitPresenter, IInitModel.OnCheckedListener {

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
}
