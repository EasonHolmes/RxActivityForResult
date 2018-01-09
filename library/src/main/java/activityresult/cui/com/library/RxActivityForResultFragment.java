package activityresult.cui.com.library;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by cuiyang on 2018/1/5.
 */

public class RxActivityForResultFragment extends Fragment {
    private Map<Integer, PublishSubject<RxActivityForResultInfo>> mSubjects = new HashMap<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public Observable<RxActivityForResultInfo> startActForResult(final Intent intent, final int requestCode) {
        PublishSubject<RxActivityForResultInfo> subject = PublishSubject.create();
        mSubjects.put(requestCode, subject);
        return subject.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                startActivityForResult(intent, requestCode);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PublishSubject<RxActivityForResultInfo> subject = mSubjects.remove(requestCode);
        if (subject != null) {
            subject.onNext(new RxActivityForResultInfo(requestCode, resultCode, data));
            subject.onComplete();
        }
    }
}
