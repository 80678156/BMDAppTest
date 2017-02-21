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
import cn.com.chioy.bmdapptest.base.BaseApplication;
import cn.com.chioy.bmdapptest.beans.User;
import cn.com.chioy.bmdapptest.dao.UserDao;
import cn.com.chioy.bmdapptest.utils.ToastUtil;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.text) TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



    }

    @OnClick(R.id.btn_crash)void testCrash(View view){
        User user = new User();
        user.setId(10000L);
        user.setUsername("zhangsan");
        user.setPassword("123456");

        UserDao userDao = BaseApplication.getInstances().getDaoSession().getUserDao();
        long result = userDao.insert(user);
        ToastUtil.showShort(this, "result:"+result);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

