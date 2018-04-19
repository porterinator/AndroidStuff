package merryweather.com.azurposservice.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import merryweather.com.azurposservice.MainActivity;
import merryweather.com.azurposservice.R;
import merryweather.com.azurposservice.api.NsodeService;
import merryweather.com.azurposservice.di.ComponentManager;

/**
 * Created by S on 03.04.2018.
 */

public class AzurposService extends Service {


    private Post mLastPost;

    private Disposable mDisposable;

    @Inject
    NsodeService apiService;


    @Override
    public void onCreate() {
        super.onCreate();
        ComponentManager.getInstance().getServiceComponent(this).inject(this);
    }

    public Post getmLastPost() {
        return mLastPost;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mDisposable =  io.reactivex.Observable.interval(0,10, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    apiService.getPost(ThreadLocalRandom.current().nextInt(0,100))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(post -> {
                                createNotification(post.id + " " + post.title, post.body, (int)post.id);
                                mLastPost = post;
                            }, throwable -> {
                                mLastPost = null;
                            });
                });

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
    }

    private void createNotification(String messageTitle, String messageBody, int id) {
        Intent intent;
        intent = new Intent( this , MainActivity.class );

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent resultIntent = PendingIntent.getActivity( this , 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri notificationSoundURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(messageBody))
                .setAutoCancel(true)
                .setSound(notificationSoundURI)
                .setContentIntent(resultIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(id, mNotificationBuilder.build());
    }

    private final IAzurposService.Stub mBinder = new IAzurposService.Stub() {
        public Post getLastPost(){
            return AzurposService.this.getmLastPost();
        }
    };

}
