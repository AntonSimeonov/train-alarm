package ninja.paranoidandroid.traintraveler;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import ninja.paranoidandroid.traintraveler.db.InsertNewTrainAlarm;
import ninja.paranoidandroid.traintraveler.util.SQLiteFragment;

public class CreatTrainAlarm extends AppCompatActivity {

    private final static String SQLITE_FRAGMENT_TAG = "SQLITE_FRAGMENT_TAG";

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
        mCancel = (Button) findViewById(R.id.b_content_create_train_alarm_cancel);
        mOk = (Button) findViewById(R.id.b_content_create_train_alarm_ok);

        setFragmentManager();
        setSQLiteFragment();

    }

    private void setOkButtonClickListener(){

        mOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setFragmentManager();
                setSQLiteFragment();
                String trainStationName = mTrainStationName.getText().toString();
                String trainNumber = mTrainNumber.getText().toString();
                int hour = mTimePicker.getCurrentHour();
                int minutes = mTimePicker.getCurrentMinute();
                String alarmTime = "" + hour + ":" + minutes;
                InsertNewTrainAlarm insertNewTrainAlarm = new InsertNewTrainAlarm(trainStationName, trainNumber, alarmTime);
                SQLiteFragment sqLiteFragment = (SQLiteFragment) mFragmentManager.findFragmentByTag(SQLITE_FRAGMENT_TAG);
                sqLiteFragment.executeQuery(insertNewTrainAlarm);

            }
        });
    }

    private void setCancelButtonClickListener(){

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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


}
