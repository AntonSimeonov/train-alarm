package ninja.paranoidandroid.traintraveler;

/**
 * Created by anton on 26.06.16.
 */
public class TrainAlarmBroadcastReciver extends SleeplesBroadcastReciver {

    @Override
    public Class getLRSClass() {

        return AqureDelayInfoService.class;
    }

}
