package cn.com.chioy.bmdapptest.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.chioy.bmdapptest.R;
import cn.com.chioy.bmdapptest.presenter.ILoginPresenter;
import cn.com.chioy.bmdapptest.presenter.impl.LoginPresenterImpl;
import cn.com.chioy.bmdapptest.utils.ToastUtil;
import cn.com.chioy.bmdapptest.utils.UIUtil;
import cn.com.chioy.bmdapptest.view.ILoginView;

public class MainActivity extends AppCompatActivity implements ILoginView{

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @BindView(R.id.edit_username) EditText mEditUsername;
    @BindView(R.id.edit_password) EditText mEditPassword;
    @BindView(R.id.btn_login) Button mBtnLogin;
    @BindView(R.id.stub_progress) ViewStub mStubProgress;

    private ILoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mLoginPresenter = new LoginPresenterImpl(MainActivity.this, this);

        andfixTest();
    }

    @OnClick(R.id.btn_login)void login(View view){
        mLoginPresenter.login(mEditUsername.getText().toString(), mEditPassword.getText().toString());
    }

    private void andfixTest(){
        Log.e("ZWH", "newnewnew ");
    }

    @Override
    public void showProgress() {
        mStubProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mStubProgress.setVisibility(View.GONE);
    }

    @Override
    public void hideSoftInput() {
        UIUtil.hideSoftInput(this, mEditUsername);
    }

    @Override
    public void showInvalidMsg(int invalidRes) {
        ToastUtil.showLong(this, invalidRes);
    }

    @Override
    public void onLoginFailt(int msgRes) {
        ToastUtil.showLong(this, msgRes);
    }

    @Override
    public void onLoginSuccess() {
        startActivity(new Intent(this, LoginSuccessActivity.class));
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    //public native String stringFromJNI();
}
