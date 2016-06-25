package ninja.paranoidandroid.traintraveler;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ninja.paranoidandroid.traintraveler.db.DBHelper;
import ninja.paranoidandroid.traintraveler.test.TestActivity;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new InitDatabase().execute();

        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }


    private class InitDatabase extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            new DBHelper(Welcome.this).getReadableDatabase();


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

}
