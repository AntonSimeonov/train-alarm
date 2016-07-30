package ninja.paranoidandroid.traintraveler.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ninja.paranoidandroid.traintraveler.R;
import ninja.paranoidandroid.traintraveler.Train;

/**
 * Created by anton on 30.07.16.
 */
public class AlarmAdapter extends ArrayAdapter<TrainAlarm>{

    private final static String TAG = "AlarmAdapter";

    private Context mContext;
    private int mResource;
    private ArrayList<TrainAlarm> mTrainAlarms;

    public AlarmAdapter(Context context, int resource, ArrayList<TrainAlarm> trainAlarms) {
        super(context, resource, trainAlarms);

        mContext = context;
        mResource = resource;
        mTrainAlarms = trainAlarms;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        Holder holder = null;

        if(row == null){


            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(mResource, null);

            holder = new Holder();
            holder.mTrainStationName = (TextView) row.findViewById(R.id.tv_train_alarm_row_train_station_name);
            holder.mTrainNumber = (TextView) row.findViewById(R.id.tv_train_alarm_row_train_number);
            holder.mAlarmActivatonTime = (TextView) row.findViewById(R.id.tv_train_alarm_row_activation_time);
            row.setTag(holder);

        }else{
            holder = (Holder) row.getTag();
        }

        TrainAlarm trainAlarm = getItem(position);
        holder.mTrainStationName.setText(trainAlarm.getTrainStationName());
        holder.mTrainNumber.setText(trainAlarm.getTrainNumber());
        holder.mAlarmActivatonTime.setText(trainAlarm.getAlarmNotificationTime());

        return row;
    }

    @Override
    public int getCount() {
        return mTrainAlarms.size();
    }

    @Override
    public TrainAlarm getItem(int position) {
        return mTrainAlarms.get(position);
    }

    class Holder{

        public TextView mTrainStationName;
        public TextView mTrainNumber;
        public TextView mAlarmActivatonTime;

    }
}
