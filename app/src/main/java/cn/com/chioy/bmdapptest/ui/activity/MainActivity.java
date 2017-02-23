package cn.com.chioy.bmdapptest.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.chioy.bmdapptest.R;
import cn.com.chioy.bmdapptest.utils.LogUtil;

public class MainActivity extends AppCompatActivity {

    //绑定View
    @BindView(R.id.text) TextView mTextView;
    //绑定颜色
    @BindColor(R.color.colorPrimary) int mColorPrimary;
    //绑定图片资源
    @BindDrawable(R.mipmap.ic_launcher) Drawable mIcLauncher;

    ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mTextView.setText("热更新成功。");


    }
    //绑定按钮点击事件
    @OnClick(R.id.btn_crash)void testCrash(View view){
        LogUtil.i("tag", "Log test!");

        Glide.with(MainActivity.this)
                .load("http://www.test.com/img.jpg") //从URL加载
                .placeholder(R.mipmap.ic_launcher) //默认图片
                .error(R.mipmap.ic_launcher) //加载图片失败后显示的图片
                .into(mImageView); //目标ImageView
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}

