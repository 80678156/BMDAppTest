package cn.com.chioy.bmdapptest.model;

/**
 * Created by zhaowh on 2017/2/17.
 */

public class LoginModelImpl implements ILoginModel {
    @Override
    public boolean login(String username, String password, OnLoginListener listener) {
        boolean temp = true;
        if(temp){
            listener.onSuccess();
        }else{
            listener.onFailure("", null);
        }
        return temp;
    }

    interface OnLoginListener{
        void onSuccess();
        void onFailure(String msg, Exception e);
    }
}
