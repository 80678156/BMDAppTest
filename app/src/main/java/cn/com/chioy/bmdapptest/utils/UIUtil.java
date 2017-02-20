package cn.com.chioy.bmdapptest.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by zhaowh on 2017/2/20.
 */

public class UIUtil {
    private UIUtil(){};

    public static void hideSoftInput(Context context, View view){
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
