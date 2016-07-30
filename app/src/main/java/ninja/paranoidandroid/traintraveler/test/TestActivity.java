package ninja.paranoidandroid.traintraveler.test;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import ninja.paranoidandroid.traintraveler.R;
import ninja.paranoidandroid.traintraveler.db.DBContract;
import ninja.paranoidandroid.traintraveler.db.DBHelper;
import ninja.paranoidandroid.traintraveler.db.DBOperations;

public class TestActivity extends AppCompatActivity {

    private final static String TAG = "TEST_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        new WriteToDB().execute();
    }

    private class WriteToDB extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            DBHelper dbHelper = new DBHelper(TestActivity.this);
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            DBOperations dbOperations = new DBOperations(sqLiteDatabase);
//            long index = dbOperations.insertNewTrainAlarm("Pleven", "2461", "16:30");
//            Log.i(TAG," DB index is " + index);
            //dbOperations.getAllTrainAlarms();

//            Cursor ti = sqLiteDatabase.rawQuery("PRAGMA table_info(train_alarm)", null);
//            if ( ti.moveToFirst() ) {
//                do {
//                    Log.i(TAG, "col: " + ti.getString(1));
//                } while (ti.moveToNext());
//            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }



}
