package ninja.paranoidandroid.traintraveler;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.os.IBinder;
import android.support.annotation.Nullable;


public abstract class AqureBDZInfoService extends IntentService {

    private Train mTrain;


    public AqureBDZInfoService(){
        super("BDZInfoService");

    }

    protected abstract void aquireInformation(Intent intent);

    @Override
    public void onCreate() {

        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }



    @Override
    protected void onHandleIntent(Intent intent) {

        aquireInformation(intent);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        SleeplesBroadcastReciver.mWakeLock.release();

    }
}
