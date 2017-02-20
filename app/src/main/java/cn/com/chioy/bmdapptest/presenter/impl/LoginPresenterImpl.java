package cn.com.chioy.bmdapptest.presenter.impl;

import android.content.Context;

import cn.com.chioy.bmdapptest.R;
import cn.com.chioy.bmdapptest.model.ILoginModel;
import cn.com.chioy.bmdapptest.model.impl.LoginModelImpl;
import cn.com.chioy.bmdapptest.presenter.ILoginPresenter;
import cn.com.chioy.bmdapptest.utils.TextUtil;
import cn.com.chioy.bmdapptest.view.ILoginView;

/**
 * Created by zhaowh on 2017/2/20.
 */

public class LoginPresenterImpl implements ILoginPresenter, ILoginModel.OnLoginListener {

    private ILoginView mLoginView;
    private ILoginModel mLoginModel;
    private Context mContext;

    public LoginPresenterImpl(Context context, ILoginView loginView){
        mContext = context;
        mLoginView = loginView;
        mLoginModel = new LoginModelImpl();
    }
    @Override
    public void login(String username, String password) {
        if(mLoginView!=null){
            mLoginView.hideSoftInput();

            int type = validateFields(username, password);
            if(type>=INVALID_TYPE_USERNAME){
                onInvalid(type);
                return;
            }

            mLoginView.showProgress();
            mLoginModel.login(username, password, this);
        }
    }

    private int validateFields(String username, String password){
        if(TextUtil.isEmpty(username) && TextUtil.isEmpty(password)){
            return INVALID_TYPE_ALL;
        }else if(TextUtil.isEmpty(username)){
            return INVALID_TYPE_USERNAME;
        }else if (TextUtil.isEmpty(password)){
            return INVALID_TYPE_PASSWORD;
        }
        return -1;
    }

    @Override
    public void onInvalid(int invalidType) {
        if(mLoginView!=null){
            switch (invalidType){
                case INVALID_TYPE_ALL:
                    mLoginView.showInvalidMsg(R.string.login_invalid_type_all);
                    break;
                case INVALID_TYPE_USERNAME:
                    mLoginView.showInvalidMsg(R.string.login_invalid_type_username);
                    break;
                case INVALID_TYPE_PASSWORD:
                    mLoginView.showInvalidMsg(R.string.login_invalid_type_password);
                    break;
            }
        }
    }

    @Override
    public void onFailure(int failureType) {
        if(mLoginView!=null){
            mLoginView.hideProgress();
            switch (failureType){
                case FAILURE_TYPE_WRONG:
                    mLoginView.onLoginFailt(R.string.login_failure_type_wrong);
                    break;
                case FAILURE_TYPE_EXCEPTION:
                    mLoginView.onLoginFailt(R.string.login_failure_type_exception);
                    break;
                default:
                    mLoginView.onLoginFailt(R.string.login_failure_type_unKnown);
            }

        }
    }

    @Override
    public void onSuccess() {
        if(mLoginView!=null){
            mLoginView.hideProgress();
            mLoginView.onLoginSuccess();
        }
    }
}
