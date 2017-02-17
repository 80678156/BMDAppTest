package cn.com.chioy.bmdapptest.model;

/**
 * Created by zhaowh on 2017/2/17.
 */

public interface ILoginModel {
    boolean login(String username, String password,  LoginModelImpl.OnLoginListener listener);
}
