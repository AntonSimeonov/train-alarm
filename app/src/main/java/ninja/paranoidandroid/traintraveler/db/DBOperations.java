package ninja.paranoidandroid.traintraveler.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by anton on 11.05.16.
 */
public class DBOperations {

    private final static String TAG = "DB_OPERATIONS";
    private SQLiteDatabase mSQLiteDataBase;

    public DBOperations(SQLiteDatabase sqLiteDatabase){
        mSQLiteDataBase = sqLiteDatabase;

    }

    public long insertNewTrainAlarm(String trainStation, String trainNumber, String timeToNotify){
        ContentValues values = new ContentValues();
        values.put(DBContract.TrainAlarm.COLUMN_TRAIN_STATION, trainStation);
        values.put(DBContract.TrainAlarm.COLUMN_TRAIN_NUMBER, trainNumber);
        values.put(DBContract.TrainAlarm.COLUMN_START_NOTIFICATION, timeToNotify);

        long newEntryIndex = mSQLiteDataBase.insert(DBContract.TrainAlarm.TABLE_NAME, null, values);

        return newEntryIndex;
    }

    public void getTrainAlarm(){

        Cursor cursor = mSQLiteDataBase.query(DBContract.TrainAlarm.TABLE_NAME, new String[]{DBContract.TrainAlarm.COLUMN_TRAIN_STATION, DBContract.TrainAlarm.COLUMN_TRAIN_NUMBER, DBContract.TrainAlarm.COLUMN_START_NOTIFICATION}, null, null, null, null, null);
        cursor.moveToFirst();
        String trainStationName = cursor.getString(cursor.getColumnIndex(DBContract.TrainAlarm.COLUMN_TRAIN_STATION));
        Log.i(TAG, "trainstation name is " + trainStationName);
    }

    public void getAllTrainAlarms(){

        Cursor cursor = mSQLiteDataBase.query(DBContract.TrainAlarm.TABLE_NAME, new String[]{DBContract.TrainAlarm.COLUMN_TRAIN_STATION, DBContract.TrainAlarm.COLUMN_TRAIN_NUMBER, DBContract.TrainAlarm.COLUMN_START_NOTIFICATION}, null, null, null, null, null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                do{

                    String trainStationName = cursor.getString(cursor.getColumnIndex(DBContract.TrainAlarm.COLUMN_TRAIN_STATION));
                    String trainNumber = cursor.getString(cursor.getColumnIndex(DBContract.TrainAlarm.COLUMN_TRAIN_NUMBER));
                    String alarmTime = cursor.getString(cursor.getColumnIndex(DBContract.TrainAlarm.COLUMN_START_NOTIFICATION));
                    Log.i(TAG, "Trainstation name is " + trainStationName + "  with train number is " +trainNumber + " and alarm set at " + alarmTime);

                }while (cursor.moveToNext());
            }
        }


    }

}
