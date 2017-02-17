package cn.com.chioy.bmdapptest.view;

/**
 * Created by zhaowh on 2017/2/17.
 */

public interface ILoginView {
    String getUsername();
    String getPassword();
    void showProgress();
    void hideProgress();
    void loginFailt();
    void loginSuccess();
}
