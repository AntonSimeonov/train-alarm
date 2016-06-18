package ninja.paranoidandroid.traintraveler;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by anton on 01.06.16.
 */
public class Train {

    private final static String TAG = "TRAIN";
    private ArrayList<String> mTrainInfo;

    private int mNumber;
    private String mCategoryOfArrival;
    private String mTrakOfArrival;
    private String mDelayOfArrival;
    private String mReasonForDelayOfArrival;
    private String mCategoryOfDeparture;
    private String mTrakOfDeparture;
    private String mDelayOfDeparture;
    private String mReasonForDelayOfDeparture;
    private String mFrom;
    private String mTo;
    private String mTimeOfArrival;
    private String mTimeOfDeparture;

    public Train(ArrayList<String> trainInfo){

        mTrainInfo = trainInfo;
    }

    public void setReasonForDelayOfArrival() {

        mReasonForDelayOfArrival = mTrainInfo.get(7);
    }

    public void setReasonForDelayOfDeparture() {

        mReasonForDelayOfDeparture = mTrainInfo.get(21);
    }

    public String getReasonForDelayOfArrival() {

        return mReasonForDelayOfArrival;
    }

    public String getReasonForDelayOfDeparture() {

        return mReasonForDelayOfDeparture;
    }

    public void setmTrainInfo(ArrayList<String> mTrainInfo) {

        this.mTrainInfo = mTrainInfo;
    }

    public String getmDelayOfArrival() {

        return mDelayOfArrival;
    }

    public void setDelayOfArrival() {

        mDelayOfArrival = mTrainInfo.get(6);
    }

    public void setDelayOfDeparture() {

        mDelayOfDeparture = mTrainInfo.get(20);
    }

    public String getmDelayOfDeparture() {

        return mDelayOfDeparture;
    }

    public String getTrakOfArrival() {

        return mTrakOfArrival;
    }

    public void setTrakOfArrival() {

       mTrakOfArrival = mTrainInfo.get(5);
    }

    public void setTrakOfDeparture() {

        mTrakOfDeparture = mTrainInfo.get(19);
    }

    public String getTrakOfDeparture() {

        return mTrakOfDeparture;
    }

    public void setTrainInfo(ArrayList<String> mTrainInfo) {

        this.mTrainInfo = mTrainInfo;
    }

    public void setCategoryOfArrival() {

        mCategoryOfArrival = mTrainInfo.get(1);
    }

    public void setCategoryOfDeparture(){

        mCategoryOfDeparture = mTrainInfo.get(9);
    }

    public void setFrom() {

    }

    public void setTo() {

    }

    public void setTimeOfArrival() {

        mTimeOfArrival = mTrainInfo.get(4);
    }

    public void setTimeOfdeparture() {

        mTimeOfDeparture = mTrainInfo.get(18);
    }

    public void setNumber(){

    }

    public ArrayList<String> getmTrainInfo() {

        return mTrainInfo;
    }

    public int getNumber() {

        return mNumber;
    }

    public String getCategoryOfArrival() {

        return mCategoryOfArrival;
    }

    public String getCategoryOfDeparture(){

        return mCategoryOfDeparture;
    }

    public String getFrom() {

        return mFrom;
    }

    public String getTo() {

        return mTo;
    }

    public String getmimeOfArrival() {

        return mTimeOfArrival;
    }

    public String getTimeOfDeparture() {

        return mTimeOfDeparture;
    }

    public void logTrainInfo(){
        for (String line: mTrainInfo) {
            Log.i(TAG, "train properties " + line);
        }
    }


}
