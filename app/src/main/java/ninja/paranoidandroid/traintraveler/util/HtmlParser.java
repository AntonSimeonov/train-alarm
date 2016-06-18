package ninja.paranoidandroid.traintraveler.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ninja.paranoidandroid.traintraveler.Train;

/**
 * Created by anton on 01.06.16.
 */
public class HtmlParser {

    private static final String TAG = "HTMPL_PARSER";

    private ArrayList<String> mTrainInfo = new ArrayList<String>();

    public InputStream connectTOURL(String url) throws IOException{
        URLConnection connection = (new URL(url)).openConnection();
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.connect();
        return connection.getInputStream();
    }


    public void getTrainInfo(String url){

        InputStream in = null;
        BufferedReader reader = null;
        String line = null;

        try {

            in = connectTOURL(url);
            reader = new BufferedReader(new InputStreamReader(in));

            while((line = reader.readLine()) != null){

                if(line.contains("9647")){

                    while(!line.contains("/tr")){

                        mTrainInfo.add(line);
                        line = reader.readLine();
                    }
                }
            }
            in.close();
        }catch (IOException ex){

        }
    }

    public void stripHtlmTags(String tagToBeStripped){
        int size = mTrainInfo.size();

        for (int i = 0; i < size; i++) {

            mTrainInfo.set(i, mTrainInfo.get(i).replace(tagToBeStripped, "").trim());
        }
    }

    public Train createTrain(){

        Train train = new Train(mTrainInfo);
        return train;
    }

    public void logTrainInfo(){
        int counter = 0;

        for (String line: mTrainInfo) {
            Log.i(TAG, "train properties at " + counter + line);
            counter++;
        }
    }

    private String filterLine(String line){


        return null;
    }


}
