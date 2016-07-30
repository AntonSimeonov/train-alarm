package ninja.paranoidandroid.traintraveler.util;

import android.app.Activity;
import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;

import java.util.ArrayList;

import ninja.paranoidandroid.traintraveler.AlarmList;
import ninja.paranoidandroid.traintraveler.db.DBHelper;
import ninja.paranoidandroid.traintraveler.db.DBOperations;

/**
 * Created by anton on 30.07.16.
 */
public class AlarmListFragment extends Fragment {

    private final static String TAG = "AlarmListFragment";

    private AlarmList mAlarmListActivity;
    private ArrayList<TrainAlarm> mTrainAlarms;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mAlarmListActivity = (AlarmList) activity;
    }

    @Override
    public void onStart() {
        super.onStart();

        new AsyncAlarmListGetter().execute();
    }

    private class AsyncAlarmListGetter extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            DBHelper dbHelper = new DBHelper(mAlarmListActivity);
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            DBOperations dbOperations = new DBOperations(sqLiteDatabase);
            mTrainAlarms = dbOperations.getAllTrainAlarms();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            mAlarmListActivity.createListView(mTrainAlarms);


        }
    }

   public interface AlarmListAgent{

        void createListView(ArrayList<TrainAlarm> trainAlarms);

    }

}
