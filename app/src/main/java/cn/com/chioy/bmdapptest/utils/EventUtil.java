package cn.com.chioy.bmdapptest.utils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by zhaowh on 2017/2/22.
 */

public class EventUtil {
    /**
     * 注册事件
     * @param context
     */
    public static void register(Object context) {
        if (!EventBus.getDefault().isRegistered(context)) {
            EventBus.getDefault().register(context);
        }
    }

    /**
     * 解绑
     * @param context
     */
    public static void unregister(Object context) {
        if (EventBus.getDefault().isRegistered(context)) {
            EventBus.getDefault().unregister(context);
        }
    }

    /**
     * 发送消息
     * @param object
     */
    public static void post(Object object) {
        EventBus.getDefault().post(object);
    }

    /**
     * 发送粘性事件
     * @param object
     */
    public static void postSticky(Object object){
        EventBus.getDefault().postSticky(object);
    }

    /**
     * 移除粘性事件
     * @param event
     * @return
     */
    public static  boolean removeStickyEvent(Object event){
        return EventBus.getDefault().removeStickyEvent(event);
    }

    /**
     * 移除所有粘性事件
     * @param event
     */
    public static  void removeAllStickyEvents(Object event){
        EventBus.getDefault().removeAllStickyEvents();
    }

}
