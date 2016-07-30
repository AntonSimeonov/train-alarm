package ninja.paranoidandroid.traintraveler;

import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

import ninja.paranoidandroid.traintraveler.db.DBOperations;
import ninja.paranoidandroid.traintraveler.db.InsertNewTrainAlarm;
import ninja.paranoidandroid.traintraveler.util.SQLiteFragment;

public class CreatTrainAlarm extends AppCompatActivity {

    private final static String SQLITE_FRAGMENT_TAG = "SQLITE_FRAGMENT_TAG";
    private final static String TAG = "CreateTrainAlarm";

    private EditText mTrainStationName;
    private EditText mTrainNumber;
    private TimePicker mTimePicker;
    private Button mCancel;
    private Button mOk;

    private SQLiteFragment mSQLiteFragment;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_creat_train_alarm);
        init();
        setOkButtonClickListener();
        setCancelButtonClickListener();

    }

    private void init(){

        mTrainStationName = (EditText) findViewById(R.id.et_content_create_train_alarm_train_station_name);
        mTrainNumber = (EditText) findViewById(R.id.et_content_create_train_alarm_train_number);
        mTimePicker = (TimePicker) findViewById(R.id.tp_content_create_train_alarm_time_of_activation);
        mTimePicker.setIs24HourView(true);
        mCancel = (Button) findViewById(R.id.b_content_create_train_alarm_cancel);
        mOk = (Button) findViewById(R.id.b_content_create_train_alarm_ok);

        setFragmentManager();
        setSQLiteFragment();

    }

    private void setOkButtonClickListener(){

        mOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "ok button clicked");
                //setFragmentManager();
                //setSQLiteFragment();
                String trainStationName = mTrainStationName.getText().toString();
                String trainNumber = mTrainNumber.getText().toString();
                int hour = mTimePicker.getCurrentHour();
                int minutes = mTimePicker.getCurrentMinute();
                String alarmTime = "" + hour + ":" + minutes;
                int alarmId = createUniqueAlarmId();

                Log.i(TAG, "Alarm id " + alarmId);

                InsertNewTrainAlarm insertNewTrainAlarm = new InsertNewTrainAlarm(alarmId, trainStationName, trainNumber, alarmTime);

                createAlarm(alarmId, trainStationName, trainNumber, hour, minutes);

                SQLiteFragment sqLiteFragment = (SQLiteFragment) mFragmentManager.findFragmentByTag(SQLITE_FRAGMENT_TAG);
                sqLiteFragment.executeQuery(insertNewTrainAlarm);

            }
        });
    }

    private void setCancelButtonClickListener(){

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreatTrainAlarm.this, AlarmList.class));
            }
        });
    }

    private void setFragmentManager(){

        mFragmentManager = getFragmentManager();

    }

    private void setSQLiteFragment(){

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.add(new SQLiteFragment(), SQLITE_FRAGMENT_TAG);
        ft.commit();

    }

    private void createAlarm(int alarmId, String trainStationName, String trainNumber, int hour, int minutes){

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, TrainAlarmBroadcastReciver.class);
        intent.putExtra("trainStationName", trainStationName);
        intent.putExtra("trainNumber", trainNumber);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, alarmId, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);

        startActivity(new Intent(this, AlarmList.class));
    }

    private int createUniqueAlarmId(){

        long time = new Date().getTime();
        String tmpStr = String.valueOf(time);
        String last4Str = tmpStr.substring(tmpStr.length() - 5);

        return Integer.valueOf(last4Str);
    }


}
