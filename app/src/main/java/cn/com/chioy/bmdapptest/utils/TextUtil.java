package cn.com.chioy.bmdapptest.utils;

import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    /**
     * 加密字符串，不可逆，使用SHA-256加密，目前来说安全性相对较高
     * @param str 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String encryptStr(String str){
        if(TextUtil.isEmpty(str)){
            return null;
        }
        try {
            byte[] input = str.getBytes();
            MessageDigest shaDigest = MessageDigest.getInstance("SHA-256");
            shaDigest.update(input);
            byte[] output = shaDigest.digest();
            String result = Base64.encodeToString(output, Base64.DEFAULT);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
