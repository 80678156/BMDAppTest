package cn.com.chioy.bmdapptest.ui.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.chioy.bmdapptest.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.text) TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        final StringBuilder sb = new StringBuilder();
        /*Observable<String> observable = Observable.fromArray("o n e", "t w o");
        observable.flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String s) throws Exception {
                return Observable.fromArray(s.split(" "));
            }
        }).filter(new Predicate<String>() {
            @Override
            public boolean test(String s) throws Exception {
                return !s.equals("o");
            }
        }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                sb.append(s).append(".");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                mTextView.setText(sb.toString());
            }
        });*/

        Observable<String> ob = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                SystemClock.sleep(10000);
                e.onNext("hello world!!");
                e.onComplete();
            }
        });
        ob.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                mTextView.setText(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Toast.makeText(MainActivity.this, "onComplete!!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

