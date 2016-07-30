package ninja.paranoidandroid.traintraveler.db;

import android.database.Cursor;

import ninja.paranoidandroid.traintraveler.util.SQLiteQuery;

/**
 * Created by anton on 19.06.16.
 */
public class InsertNewTrainAlarm extends SQLiteQuery {

    private String mTrainStation;
    private String mTrainNumber;
    private String mTimeToNotify;
    private int mAlarmId;

    public InsertNewTrainAlarm(int alarmId, String trainStation, String trainNumber, String timeToNotify){

        mAlarmId = alarmId;
        mTrainStation = trainStation;
        mTrainNumber = trainNumber;
        mTimeToNotify = timeToNotify;



    }

    @Override
    public Cursor query() {

        mDBOperations.insertNewTrainAlarm(mAlarmId, mTrainStation, mTrainNumber, mTimeToNotify);

        return null;
    }

}
