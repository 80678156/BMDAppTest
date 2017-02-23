package cn.com.chioy.bmdapptest.view;

/**
 * 登陆界面接口View
 * @author zhaowh
 * @Date 2017/2/23
 */

public interface ILoginView extends IBaseView {
    void showProgress();
    void hideProgress();
    void hideSoftInput();
    void showInvalidMsg(int invalidRes);
    void onLoginFailt(int msgRes);
    void onLoginSuccess();
}
