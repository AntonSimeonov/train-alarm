package ninja.paranoidandroid.traintraveler;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ninja.paranoidandroid.traintraveler.util.HtmlParser;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Test test = new Test();
//        test.printLogStringList();

        Intent intent = new Intent(this, AqureDelayInfoService.class);
        startService(intent);
       // new TestAsyncRegEx().execute();
    }


    public class TestAsyncRegEx extends AsyncTask<Void, Void, Void>{
        public int mIndexNumber = 0;
        public String mHtmlText = null;
        public String mJavaScriptTrainClass = null;
        public String mTableContent = null;
        public String mTrainInfo = null;
        @Override
        protected Void doInBackground(Void... voids) {

            HtmlParser htmlParser = new HtmlParser("2164");

            try {

                  htmlParser.getTrainInfo("http://razpisanie.bdz.bg/SearchServlet?action=listStationDelay&fromStationName=Pleven");
                  htmlParser.stripHtlmTags("<td>");
                  htmlParser.stripHtlmTags("</td>");

                  Train train = htmlParser.createTrain();
                  train.logTrainInfo();

            } catch(Exception ex){

            }



            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }


}
