package ninja.paranoidandroid.traintraveler;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import ninja.paranoidandroid.traintraveler.util.HtmlParser;

/**
 * Created by anton on 13.06.16.
 */
public class AqureDelayInfoService extends AqureBDZInfoService {

    private final static String TAG = "AQUIRE_DELAY_SERVICE";
    private int mNotificationId;

    private String mTrainScheduleURL = "http://razpisanie.bdz.bg/SearchServlet?action=listStationDelay&fromStationName=";

    private String mTrainStationName;
    private String mTrainNumber;

    public AqureDelayInfoService(){
        super();
    }

    //Here we get information from BDZ site, for train delay.
    @Override
    protected void aquireInformation(Intent intent) {


        setTrainAlarmProperties(intent);

        HtmlParser htmlParser = new HtmlParser(mTrainNumber);

        Log.i(TAG, "Path is " + mTrainScheduleURL + mTrainStationName);

        htmlParser.getTrainInfo(mTrainScheduleURL + mTrainStationName);
        htmlParser.filterListElements();
        htmlParser.stripHtlmTags("<td>");
        htmlParser.stripHtlmTags("</td>");
        Train train = htmlParser.createTrain();
        train.setTrainProperties();
        createNotification(train);

    }

    private void setTrainAlarmProperties(Intent intent){

        Intent alarmIntent = intent.getParcelableExtra("original_intent");
        mTrainStationName = alarmIntent.getStringExtra("trainStationName");
        mTrainNumber = alarmIntent.getStringExtra("trainNumber");

        Log.i(TAG, "trainstation name " + mTrainStationName + " train number " + mTrainNumber);

    }

    private void createNotification(Train train){
        mNotificationId = 1;
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.loko)
                        .setContentTitle("Time of arrival")
                        .setContentText(train.getTimeOfArrival());

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(mNotificationId ,mBuilder.build());

    }
}
