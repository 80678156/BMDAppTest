package cn.com.chioy.bmdapptest.utils;

import java.util.AbstractMap;
import java.util.Collection;

/**
 * Created by zhaowh on 2017/2/17.
 */

public class CollectionUtil {

    /**
     * 判断一个集合是否为空
     * @param c
     * @return 参数为null或者集合没有数据则返回true，否则返回false
     */
    public static boolean isEmpty(Collection c){
        if(c==null){
            return true;
        }
        return c.isEmpty();
    }

    /**
     * 判断一个map是否为空
     * @param map
     * @return 参数为null或者map没有数据则返回true，否则返回false
     */
    public static boolean isEmpty(AbstractMap map){
        if(map==null){
            return true;
        }
        return map.isEmpty();
    }
}
