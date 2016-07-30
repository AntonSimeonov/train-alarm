package ninja.paranoidandroid.traintraveler.util;

/**
 * Created by anton on 30.07.16.
 */
public class TrainAlarm {

    private int mAlarmId;
    private String mTrainStationName;
    private String mTrainNumber;
    private String mAlarmNotificationTime;

    public TrainAlarm(int alarmId, String trainStationName, String trainNumber, String alarmNotificationTime){

        mAlarmId = alarmId;
        mTrainStationName = trainStationName;
        mTrainNumber = trainNumber;
        mAlarmNotificationTime = alarmNotificationTime;

    }

    public String getTrainStationName() {
        return mTrainStationName;
    }

    public String getTrainNumber() {
        return mTrainNumber;
    }

    public String getAlarmNotificationTime() {
        return mAlarmNotificationTime;
    }

    public int getAlarmId() {
        return mAlarmId;
    }
}
