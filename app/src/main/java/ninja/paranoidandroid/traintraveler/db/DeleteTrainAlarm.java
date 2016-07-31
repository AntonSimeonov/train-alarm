package ninja.paranoidandroid.traintraveler.db;

import android.database.Cursor;

import ninja.paranoidandroid.traintraveler.util.SQLiteQuery;

/**
 * Created by anton on 31.07.16.
 */
public class DeleteTrainAlarm extends SQLiteQuery {

    private int mAlarmId;

    public DeleteTrainAlarm(int alarmId) {

        mAlarmId = alarmId;

    }
    @Override
    public Cursor query() {

        mDBOperations.deletetrainAlarm(mAlarmId);

        return null;
    }
}
