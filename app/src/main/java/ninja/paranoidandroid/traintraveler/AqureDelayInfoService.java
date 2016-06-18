package ninja.paranoidandroid.traintraveler;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.io.IOException;

import ninja.paranoidandroid.traintraveler.util.HtmlParser;

/**
 * Created by anton on 13.06.16.
 */
public class AqureDelayInfoService extends AqureBDZInfoService {

    private final static String TAG = "AQUIRE DELAy SERVICE";

    public AqureDelayInfoService(){
        super();
    }

    //Here we get information from BDZ site, for train delay.
    @Override
    protected void aquireInformation() {
//
        HtmlParser htmlParser = new HtmlParser();
//
        htmlParser.getTrainInfo("http://razpisanie.bdz.bg/SearchServlet?action=listStationDelay&fromStationName=Pleven");
        htmlParser.stripHtlmTags("<td>");
        htmlParser.stripHtlmTags("</td>");
        //htmlParser.logTrainInfo();
        Train train = htmlParser.createTrain();
        createNotification(train);

    }

    private void createNotification(Train train){

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.loko)
                        .setContentTitle("Delay of arrival")
                        .setContentText(train.getmDelayOfArrival());
        Log.i(TAG, "in service" + train.getmDelayOfArrival());
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(1 ,mBuilder.build());

    }
}
