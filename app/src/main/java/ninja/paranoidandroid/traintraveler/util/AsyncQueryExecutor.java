package ninja.paranoidandroid.traintraveler.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;


/**
 * Created by anton on 22.05.16.
 */
public class AsyncQueryExecutor extends AsyncTask<SQLiteQuery, Void, Cursor> {

    private SQLiteQuery mSQLiteQuery;
    private SQLiteDatabase mSQLitedatabase;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

    }

    public void setSQLiteQuery(SQLiteQuery mSQLiteQuery) {
        this.mSQLiteQuery = mSQLiteQuery;

    }

    public void setSQLitedatabase(SQLiteDatabase mSQLitedatabase) {
        this.mSQLitedatabase = mSQLitedatabase;

    }

    @Override
    protected Cursor doInBackground(SQLiteQuery... queries) {

        mSQLiteQuery = queries[0];
        mSQLiteQuery.createDBOperation(mSQLitedatabase);
        Cursor cursor = mSQLiteQuery.query();

        return cursor;
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
        super.onPostExecute(cursor);

    }
}
