package merryweather.com.agentplus.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import merryweather.com.agentplus.di.ComponentManager;
import merryweather.com.agentplus.repository.AgentplusRepository;

public class AgentplusService extends Service {

    @Inject
    public AgentplusRepository mRepository;

    private CompositeDisposable mDisposable = new CompositeDisposable();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        ComponentManager.getInstance().getServiceComponent(this).inject(this);
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mDisposable.add(
                Observable.interval(1, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((l) -> {
                            mRepository.tick();
                        })
        );
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
    }
}
