package cn.com.chioy.bmdapptest.model.impl;

import android.os.Handler;

import cn.com.chioy.bmdapptest.model.ILoginModel;
import cn.com.chioy.bmdapptest.utils.LogUtil;
import cn.com.chioy.bmdapptest.utils.TextUtil;

/**
 * Created by zhaowh on 2017/2/17.
 */

public class LoginModelImpl implements ILoginModel {
    @Override
    public void login(final String username, final String password, final OnLoginListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    if(TextUtil.encryptStr(username.trim()).equals(getUsername())
                            && TextUtil.encryptStr(password.trim()).equals(getPassword())){
                        LogUtil.e("username:"+getUsername());
                        LogUtil.e("password:"+getPassword());
                        listener.onSuccess();
                    }else{
                        listener.onFailure(OnLoginListener.FAILURE_TYPE_WRONG);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    listener.onFailure(OnLoginListener.FAILURE_TYPE_EXCEPTION);
                }
            }
        }, 2000);
    }

    private String getUsername(){
        //TODO 从设备中获取用户名
        return  TextUtil.encryptStr("chioy");
    }

    private String getPassword(){
        //TODO 从设备中获取密码
        return TextUtil.encryptStr("123456");
    }
}
