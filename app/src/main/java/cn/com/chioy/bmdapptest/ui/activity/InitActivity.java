package cn.com.chioy.bmdapptest.ui.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.EditText;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.chioy.bmdapptest.R;
import cn.com.chioy.bmdapptest.beans.UpdateInfo;
import cn.com.chioy.bmdapptest.presenter.IInitPresenter;
import cn.com.chioy.bmdapptest.presenter.ILoginPresenter;
import cn.com.chioy.bmdapptest.presenter.impl.InitPresenterImpl;
import cn.com.chioy.bmdapptest.presenter.impl.LoginPresenterImpl;
import cn.com.chioy.bmdapptest.utils.ToastUtil;
import cn.com.chioy.bmdapptest.utils.UIUtil;
import cn.com.chioy.bmdapptest.view.IInitView;
import cn.com.chioy.bmdapptest.view.ILoginView;

public class InitActivity extends AppCompatActivity implements ILoginView, IInitView{

    @BindView(R.id.edit_username) EditText mEditUsername;
    @BindView(R.id.edit_password) EditText mEditPassword;
    @BindView(R.id.stub_progress) ViewStub mStubProgress;

    private IInitPresenter mInitPresenter;
    private ILoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        ButterKnife.bind(this);

        mInitPresenter = new InitPresenterImpl(InitActivity.this, this);
        mLoginPresenter = new LoginPresenterImpl(InitActivity.this, this);

        mInitPresenter.checkAndShowIntro(InitActivity.this);
    }

    private void requestPermission(){

    }

    @OnClick(R.id.btn_login)void login(View view){
        mLoginPresenter.login(mEditUsername.getText().toString(), mEditPassword.getText().toString());
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
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void showIntro() {
        startActivity(new Intent(this, IntroActivity.class));
    }

    @Override
    public void showUpdateDialog(final UpdateInfo info) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(InitActivity.this);
                AlertDialog dialog = builder.setTitle(R.string.update_new)
                        .setMessage(info.getDescription())
                        .setCancelable(false)
                        .setPositiveButton(R.string.btn_confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String url = "http://211.94.114.47/apk.r1.market.hiapk.com/data/upload/apkres/2016/11_16/12/com.xxtoutiao.xxtt_121708.apk";
                                mInitPresenter.downloadUpdate(url);
                                dialog.dismiss();
                            }
                        }).setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
    }
    ProgressDialog mProgressDialog = null;
    @Override
    public void onDownloading(long currentSize, long totalSize, float progress, long networkSpeed){
        if(mProgressDialog==null){
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setTitle(R.string.download_update);
            mProgressDialog.setMessage(getString(R.string.download_msg));
            mProgressDialog.setMax(100);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();
        }else{
            mProgressDialog.setMessage(getString(R.string.download_msg)+(int)(100*progress)+"%");
        }
    }

    @Override
    public void onDownloadComplete(File file) {
        if(mProgressDialog!=null){
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
        ToastUtil.showShort(this, R.string.download_finished);
    }

    @Override
    public void onDownloadError(Throwable e) {
        if(mProgressDialog!=null){
            mProgressDialog.dismiss();
            mProgressDialog = null;
            ToastUtil.showShort(this, R.string.download_error);
        }
    }
}
