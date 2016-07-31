package ninja.paranoidandroid.traintraveler.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import ninja.paranoidandroid.traintraveler.util.TrainAlarm;

/**
 * Created by anton on 11.05.16.
 */
public class DBOperations {

    private final static String TAG = "DB_OPERATIONS";
    private SQLiteDatabase mSQLiteDataBase;

    public DBOperations(SQLiteDatabase sqLiteDatabase){
        mSQLiteDataBase = sqLiteDatabase;

    }

    public long insertNewTrainAlarm(int alarmId, String trainStation, String trainNumber, String timeToNotify){
        ContentValues values = new ContentValues();
        values.put(DBContract.TrainAlarm.COLUMN_ALARM_ID, alarmId);
        values.put(DBContract.TrainAlarm.COLUMN_TRAIN_STATION, trainStation);
        values.put(DBContract.TrainAlarm.COLUMN_TRAIN_NUMBER, trainNumber);
        values.put(DBContract.TrainAlarm.COLUMN_START_NOTIFICATION, timeToNotify);


        long newEntryIndex = mSQLiteDataBase.insert(DBContract.TrainAlarm.TABLE_NAME, null, values);

        return newEntryIndex;
    }

    public void getTrainAlarm(){

        Cursor cursor = mSQLiteDataBase.query(DBContract.TrainAlarm.TABLE_NAME, new String[]{DBContract.TrainAlarm.COLUMN_ALARM_ID, DBContract.TrainAlarm.COLUMN_TRAIN_STATION, DBContract.TrainAlarm.COLUMN_TRAIN_NUMBER, DBContract.TrainAlarm.COLUMN_START_NOTIFICATION}, null, null, null, null, null);
        cursor.moveToFirst();
        String trainStationName = cursor.getString(cursor.getColumnIndex(DBContract.TrainAlarm.COLUMN_TRAIN_STATION));
        Log.i(TAG, "trainstation name is " + trainStationName);
    }

//    public void getAllTrainAlarms(){
//
//        Cursor cursor = mSQLiteDataBase.query(DBContract.TrainAlarm.TABLE_NAME, new String[]{DBContract.TrainAlarm.COLUMN_ALARM_ID ,DBContract.TrainAlarm.COLUMN_TRAIN_STATION, DBContract.TrainAlarm.COLUMN_TRAIN_NUMBER, DBContract.TrainAlarm.COLUMN_START_NOTIFICATION}, null, null, null, null, null);
//        if(cursor != null){
//            if(cursor.moveToFirst()){
//                do{
//
//                    String trainStationName = cursor.getString(cursor.getColumnIndex(DBContract.TrainAlarm.COLUMN_TRAIN_STATION));
//                    String trainNumber = cursor.getString(cursor.getColumnIndex(DBContract.TrainAlarm.COLUMN_TRAIN_NUMBER));
//                    String alarmTime = cursor.getString(cursor.getColumnIndex(DBContract.TrainAlarm.COLUMN_START_NOTIFICATION));
//                    int alarmId = cursor.getInt(cursor.getColumnIndex(DBContract.TrainAlarm.COLUMN_ALARM_ID));
//                    Log.i(TAG,"Alarm id " + alarmId + "Trainstation name is " + trainStationName + "  with train number is " +trainNumber + " and alarm set at " + alarmTime);
//
//                }while (cursor.moveToNext());
//            }
//        }
//
//    }

    public ArrayList<TrainAlarm> getAllTrainAlarms(){

        ArrayList<TrainAlarm> trainAlarms = new ArrayList<TrainAlarm>();
        Cursor cursor = mSQLiteDataBase.query(DBContract.TrainAlarm.TABLE_NAME, new String[]{DBContract.TrainAlarm.COLUMN_ALARM_ID ,DBContract.TrainAlarm.COLUMN_TRAIN_STATION, DBContract.TrainAlarm.COLUMN_TRAIN_NUMBER, DBContract.TrainAlarm.COLUMN_START_NOTIFICATION}, null, null, null, null, null);

        if(cursor != null){
            if(cursor.moveToFirst()){
                do{

                    String trainStationName = cursor.getString(cursor.getColumnIndex(DBContract.TrainAlarm.COLUMN_TRAIN_STATION));
                    String trainNumber = cursor.getString(cursor.getColumnIndex(DBContract.TrainAlarm.COLUMN_TRAIN_NUMBER));
                    String alarmTime = cursor.getString(cursor.getColumnIndex(DBContract.TrainAlarm.COLUMN_START_NOTIFICATION));
                    int alarmId = cursor.getInt(cursor.getColumnIndex(DBContract.TrainAlarm.COLUMN_ALARM_ID));
                    //Log.i(TAG,"Alarm id " + alarmId + "Trainstation name is " + trainStationName + "  with train number is " +trainNumber + " and alarm set at " + alarmTime);
                    trainAlarms.add(new TrainAlarm(alarmId, trainStationName, trainNumber, alarmTime));
                }while (cursor.moveToNext());
            }
        }
        return trainAlarms;
    }

    public ArrayList<String[]> getDbTableDetails() {
        Cursor c = mSQLiteDataBase.rawQuery(
                "SELECT name FROM sqlite_master WHERE type='table'", null);
        ArrayList<String[]> result = new ArrayList<String[]>();
        int i = 0;
        result.add(c.getColumnNames());
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            String[] temp = new String[c.getColumnCount()];
            for (i = 0; i < temp.length; i++) {
                temp[i] = c.getString(i);
            }
            result.add(temp);
        }

        return result;
    }

    public boolean deletetrainAlarm(int alarmId){

        return mSQLiteDataBase.delete(DBContract.TrainAlarm.TABLE_NAME, DBContract.TrainAlarm.COLUMN_ALARM_ID + "=" + alarmId, null) > 0;
    }

}
