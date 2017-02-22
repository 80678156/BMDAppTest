package cn.com.chioy.bmdapptest.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.chioy.bmdapptest.R;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.text) TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mTextView.setText("热更新成功。");
    }

    @OnClick(R.id.btn_crash)void testCrash(View view){
        throw new NullPointerException("test");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

