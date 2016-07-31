package ninja.paranoidandroid.traintraveler;

import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import ninja.paranoidandroid.traintraveler.db.DBHelper;
import ninja.paranoidandroid.traintraveler.db.DBOperations;
import ninja.paranoidandroid.traintraveler.db.DeleteTrainAlarm;
import ninja.paranoidandroid.traintraveler.util.AlarmAdapter;
import ninja.paranoidandroid.traintraveler.util.AlarmListFragment;
import ninja.paranoidandroid.traintraveler.util.SQLiteFragment;
import ninja.paranoidandroid.traintraveler.util.TrainAlarm;

public class AlarmList extends AppCompatActivity implements AlarmListFragment.AlarmListAgent, DeleteItemDialogFragment.DeleteAlarmAgent{

    private final static String TAG = "AlarmList";
    private static final String ALARM_LIST_TAG = "alarm_list_tag";
    private static final String SQLITE_FRAGMENT = "sqlite_fragment";

    private ListView mAlarmsListView;
    private AlarmAdapter mAlarmAdapter;
    private ArrayList<TrainAlarm> mTrainAlarms;
    private FloatingActionButton mFab;

    private FragmentManager mFragmentManager;

    private int mTrainAlarmIdForDeletion;
    private int mArrayListIndexForDeletion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarms);

        init();
        setFabOnClickListener();
        setOnAlarmListItemClick();

    }

    private void init(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAlarmsListView = (ListView) findViewById(R.id.lv_alarm_list_alarms);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        //setAlarmsAdapter();
        setFragmentManager();
        setAlarmListFragment();
        setSQLiteFragment();

    }

    private void setAlarmsAdapter(){

        mAlarmAdapter = new AlarmAdapter(this, R.layout.train_alarm_row, mTrainAlarms);
        mAlarmsListView.setAdapter(mAlarmAdapter);

    }

    private void setOnAlarmListItemClick(){

        mAlarmsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                mArrayListIndexForDeletion = i;
                mTrainAlarmIdForDeletion = mTrainAlarms.get(i).getAlarmId();
                new DeleteItemDialogFragment().show(mFragmentManager, "delete_item");

            }
        });

    }

    private void setFabOnClickListener(){

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(getBaseContext(), CreatTrainAlarm.class);
                startActivity(intent);
            }
        });
    }

    private void setFragmentManager(){
        mFragmentManager = getFragmentManager();
    }

    private void setAlarmListFragment(){

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(new AlarmListFragment(), ALARM_LIST_TAG);
        fragmentTransaction.commit();

    }

    @Override
    public void createListView(ArrayList<TrainAlarm> trainAlarms) {
        mTrainAlarms = trainAlarms;
        setAlarmsAdapter();
    }

    private void setSQLiteFragment(){
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.add(new SQLiteFragment(), SQLITE_FRAGMENT);
        ft.commit();
    }

    @Override
    public void onDeleteAlarm() {

        DeleteTrainAlarm deleteTrainAlarm = new DeleteTrainAlarm(mTrainAlarmIdForDeletion);
        SQLiteFragment sqLiteFragment = (SQLiteFragment) mFragmentManager.findFragmentByTag(SQLITE_FRAGMENT);
        sqLiteFragment.executeQuery(deleteTrainAlarm);
        deleteAlarmFromAlarmManeger(mTrainAlarmIdForDeletion);

        mTrainAlarms.remove(mArrayListIndexForDeletion);
        mAlarmAdapter.notifyDataSetChanged();


    }

    private void deleteAlarmFromAlarmManeger(int alarmId){

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, TrainAlarmBroadcastReciver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, alarmId, intent, 0);

        alarmManager.cancel(alarmIntent);
    }
}
