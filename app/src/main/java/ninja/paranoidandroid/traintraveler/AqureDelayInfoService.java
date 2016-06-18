package ninja.paranoidandroid.traintraveler;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import ninja.paranoidandroid.traintraveler.util.HtmlParser;

/**
 * Created by anton on 13.06.16.
 */
public class AqureDelayInfoService extends AqureBDZInfoService {

    private final static String TAG = "AQUIRE DELAy SERVICE";
    private int mNotificationId;

    public AqureDelayInfoService(){
        super();
    }

    //Here we get information from BDZ site, for train delay.
    @Override
    protected void aquireInformation() {
//
        HtmlParser htmlParser = new HtmlParser("2641");
//
        htmlParser.getTrainInfo("http://razpisanie.bdz.bg/SearchServlet?action=listStationDelay&fromStationName=Pleven");
        htmlParser.filterListElements();
        htmlParser.stripHtlmTags("<td>");
        htmlParser.stripHtlmTags("</td>");
        //htmlParser.logTrainInfo();
        Train train = htmlParser.createTrain();
        train.setTrainProperties();
        train.logTrainInfo();
        createNotification(train);

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
