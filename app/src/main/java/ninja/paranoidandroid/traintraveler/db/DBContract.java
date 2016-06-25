package ninja.paranoidandroid.traintraveler.db;

/**
 * Created by anton on 19.06.16.
 */
public final class DBContract {

    public final static String NAME = "train_traveler.db";
    public final static int VERSION = 1;



    public class TrainAlarm{

        public final static String TABLE_NAME = "train_alarm";

        public final static String COLUMN_ID = "id";
        public final static String COLUMN_TRAIN_STATION = "train_station";
        public final static String COLUMN_TRAIN_NUMBER = "train_number";
        public final static String COLUMN_START_NOTIFICATION = "start_notification";

    }

}
