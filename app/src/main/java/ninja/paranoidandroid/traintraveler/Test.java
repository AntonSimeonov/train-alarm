package ninja.paranoidandroid.traintraveler;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;

import ninja.paranoidandroid.traintraveler.util.HtmlParser;

/**
 * Created by anton on 18.06.16.
 */
public class Test {

    private static final String TAG = "TEST CLASS :)";

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
        try {
            mTrainInfo.removeAll(Collections.singleton(null));
        }catch (Exception ex){

        }
        for (String line: mTrainInfo) {
            Log.i(TAG, "train properties at " + counter + line);
            counter++;
        }
    }

    private String filterLine(String line){


        return null;
    }

    private void filterListElements(){
        ArrayList<String> filteredList = new ArrayList<String>();

        for (String line: mTrainInfo) {

            if(line.contains("td")){
                filteredList.add(line);
            }

        }

        mTrainInfo = filteredList;

    }

    public void printLogStringList(){

        new TestAsyncRegEx().execute();

    }

    public class TestAsyncRegEx extends AsyncTask<Void, Void, Void> {
        public int mIndexNumber = 0;
        public String mHtmlText = null;
        public String mJavaScriptTrainClass = null;
        public String mTableContent = null;
        public String mTrainInfo = null;
        @Override
        protected Void doInBackground(Void... voids) {


                getTrainInfo("http://razpisanie.bdz.bg/SearchServlet?action=listStationDelay&fromStationName=Pleven");
                filterListElements();
                //stripHtlmTags("<td>");
                //stripHtlmTags("</td>");
                logTrainInfo();
                //Train train = createTrain();
                //train.logTrainInfo();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }

}
