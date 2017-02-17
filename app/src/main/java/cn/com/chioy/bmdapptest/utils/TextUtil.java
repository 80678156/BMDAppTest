package cn.com.chioy.bmdapptest.utils;

/**
 * Created by zhaowh on 2017/2/17.
 */

public class TextUtil {

    /**
     * 判断一个字符串是否为空（空字符或只包含空格的字符也算做empty）
     * @param str 需要判断的字符串
     * @return 为null、空字符、只包含空格的字符时，返回TRUE，否则返回false
     */
    public static boolean isEmpty(String str){
        if(str==null){
            return true;
        }
        if("".equals(str.trim())){
            return true;
        }
        return false;
    }
}
