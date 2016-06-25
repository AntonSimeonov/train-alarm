package ninja.paranoidandroid.traintraveler.util;

import android.app.Activity;
import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ninja.paranoidandroid.traintraveler.db.DBHelper;

/**
 * Created by anton on 24.05.16.
 */
public class SQLiteFragment extends Fragment {

    private DBHelper mDBHelper;
    private SQLiteDatabase mSQLiteDatabase;
    private AsyncQueryExecutor mAsyncQueryExecutor;

    private Activity mContainerActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //Makes fragment headless with null returned value.
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContainerActivity = activity;
    }

    public void executeQuery(SQLiteQuery sqLiteQuery){

        mAsyncQueryExecutor = new AsyncQueryExecutor();
        createDBHelper();
        setSQLiteDatabase();
        mAsyncQueryExecutor.setSQLitedatabase(mSQLiteDatabase);
        mAsyncQueryExecutor.execute(sqLiteQuery);

    }

    public void createDBHelper(){

        mDBHelper = new DBHelper(mContainerActivity);

    }

    public void setSQLiteDatabase(){

        mSQLiteDatabase = mDBHelper.getWritableDatabase();

    }

    interface CursorResult{

    }


}
