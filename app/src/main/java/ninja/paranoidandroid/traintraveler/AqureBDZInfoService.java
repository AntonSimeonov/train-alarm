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

    protected abstract void aquireInformation();

    @Override
    public void onCreate() {

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        aquireInformation();
    }
}
