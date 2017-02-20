package cn.com.chioy.bmdapptest.model;

import cn.com.chioy.bmdapptest.model.impl.LoginModelImpl;

/**
 * Created by zhaowh on 2017/2/17.
 */

public interface ILoginModel {
    void login(String username, String password,  LoginModelImpl.OnLoginListener listener);

    interface OnLoginListener{
        /**用户名为空或者不合法*/
        public static final int INVALID_TYPE_USERNAME = 0x01;
        /**密码为空或者不合法*/
        public static final int INVALID_TYPE_PASSWORD = 0x02;
        /**用户名和密码均为空或者不合法*/
        public static final int INVALID_TYPE_ALL = 0x03;
        void onInvalid(int invalidType);

        /**用户名或者密码错误*/
        public static final int FAILURE_TYPE_WRONG = 0x01;
        /**登陆发生异常*/
        public static final int FAILURE_TYPE_EXCEPTION = 0x02;

        /**
         * 登陆失败
         * @param failureType 类型，值为{@link #FAILURE_TYPE_WRONG}表示用户名和密码错误,
         *                    值为{@link #FAILURE_TYPE_EXCEPTION}表示发生异常
         */
        void onFailure(int failureType);

        void onSuccess();

    }
}
