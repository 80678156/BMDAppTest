package cn.com.chioy.bmdapptest.rx;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import cn.com.chioy.bmdapptest.utils.CollectionUtil;
import cn.com.chioy.bmdapptest.utils.LogUtil;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by zhaowh on 2017/2/17.
 */

public class RxBus {
    private static RxBus instance;

    public static synchronized RxBus get() {
        if (null == instance) {
            instance = new RxBus();
        }
        return instance;
    }

    private RxBus() {
    }

    private ConcurrentHashMap<Object, List<Subject>> subjectMapper = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public <T> Observable<T> register(@NonNull Object tag, @NonNull Class<T> clazz) {
        List<Subject> subjectList = subjectMapper.get(tag);
        if (null == subjectList) {
            subjectList = new ArrayList<>();
            subjectMapper.put(tag, subjectList);
        }

        Subject<T> subject;
        subjectList.add(subject = PublishSubject.create());
        LogUtil.d("[register]subjectMapper: " + subjectMapper);
        return subject;
    }

    public void unregister(@NonNull Object tag, @NonNull Observable observable) {
        List<Subject> subjects = subjectMapper.get(tag);
        if (null != subjects) {
            subjects.remove((Subject) observable);
            if (CollectionUtil.isEmpty(subjects)) {
                subjectMapper.remove(tag);
            }
        }

        LogUtil.d( "[unregister]subjectMapper: " + subjectMapper);
    }

    public void post(@NonNull Object content) {
        post(content.getClass().getName(), content);
    }

    @SuppressWarnings("unchecked")
    public void post(@NonNull Object tag, @NonNull Object content) {
        List<Subject> subjectList = subjectMapper.get(tag);

        if (!CollectionUtil.isEmpty(subjectList)) {
            for (Subject subject : subjectList) {
                subject.onNext(content);
            }
        }
        LogUtil.d("[send]subjectMapper: " + subjectMapper);
    }

    public void clear(){
        if(!CollectionUtil.isEmpty(subjectMapper)){
            subjectMapper.clear();
        }
    }
}
