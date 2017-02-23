package cn.com.chioy.bmdapptest.presenter;

/**
 * User Login 相关View和Model交互接口
 * @author zhaowh
 * @Date 2017/2/23
 */

public interface ILoginPresenter extends IBasePresenter{
    void login(String username, String password);
}
