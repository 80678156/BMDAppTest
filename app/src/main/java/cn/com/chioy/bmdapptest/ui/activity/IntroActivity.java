package cn.com.chioy.bmdapptest.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import cn.com.chioy.bmdapptest.R;
import cn.com.chioy.bmdapptest.ui.fragment.SlideTwoFragment;

/**
 * Created by zhaowh on 2017/2/20.
 */

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.newInstance("欢迎页", "欢迎使用", R.mipmap.ic_launcher, Color.BLUE));
        addSlide(new SlideTwoFragment());
        addSlide(AppIntroFragment.newInstance("AppIntroFragment", "AppIntroFragment", R.mipmap.ic_launcher, Color.BLUE));
        addSlide(AppIntroFragment.newInstance("最后一页", "这是最后一页", R.mipmap.ic_launcher, Color.BLUE));

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
        showSkipButton(false);
        setProgressButtonEnabled(true);
        setDoneText("进入");
        setSkipText("跳过");

        setGoBackLock(true);
        //setBackButtonVisibilityWithDone(true);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        //setVibrate(true);
        //setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }

}
