package cn.com.chioy.bmdapptest.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.chioy.bmdapptest.R;
import cn.com.chioy.bmdapptest.base.BaseApplication;
import cn.com.chioy.bmdapptest.utils.LogUtil;
import cn.com.chioy.bmdapptest.utils.ToastUtil;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @BindView(R.id.edit_username) EditText mEditUsername;
    @BindView(R.id.edit_password) EditText mEditPassword;
    @BindView(R.id.btn_login) Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Toast.makeText(this, "lalallala", Toast.LENGTH_SHORT).show();
        patchTest();
    }

    @OnClick(R.id.btn_login)void login(View view){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditPassword.getWindowToken(), 0);
        Toast.makeText(this, "hahahhahahahah!", Toast.LENGTH_SHORT).show();
    }

    private void patchTest(){
        Log.e("ZWH", "newnewnew ");
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    //public native String stringFromJNI();
}
