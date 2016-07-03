package ninja.paranoidandroid.traintraveler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

/**
 * Created by anton on 26.06.16.
 */
public abstract class SleeplesBroadcastReciver extends BroadcastReceiver {

    private final static String WAKE_LOCK_TAG = "sleeplesBroadcastReiverWakeLock";
    private final static String TAG = "SLEEPLES_BR";

    public static PowerManager.WakeLock mWakeLock;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i(TAG, "Train alarm reciver");
        obtainWakeLock(context);;
        aquireWakeLock();
        startService(context, intent);

    }

    private void startService(Context context, Intent intent) {

        Intent serviceIntent = new Intent(context,getLRSClass());
        serviceIntent.putExtra("original_intent", intent);
        context.startService(serviceIntent);

    }

    private void obtainWakeLock(Context context){

        PowerManager powerManager = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
        mWakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, WAKE_LOCK_TAG);

    }

    private void aquireWakeLock(){

        mWakeLock.acquire();

    }

    public abstract Class getLRSClass();

}
