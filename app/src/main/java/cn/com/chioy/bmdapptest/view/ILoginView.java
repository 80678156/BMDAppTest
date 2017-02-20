package cn.com.chioy.bmdapptest.view;

/**
 * Created by zhaowh on 2017/2/17.
 */

public interface ILoginView {
    void showProgress();
    void hideProgress();
    void hideSoftInput();
    void showInvalidMsg(int invalidRes);
    void onLoginFailt(int msgRes);
    void onLoginSuccess();
}
