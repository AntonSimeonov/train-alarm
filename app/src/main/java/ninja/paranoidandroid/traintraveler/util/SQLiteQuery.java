package ninja.paranoidandroid.traintraveler.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ninja.paranoidandroid.traintraveler.db.DBOperations;

/**
 * Created by anton on 13.05.16.
 */
public abstract class SQLiteQuery {

    protected DBOperations mDBOperations;


    public abstract Cursor query();

    public void createDBOperation(SQLiteDatabase sqLiteDatabase){

        mDBOperations = new DBOperations(sqLiteDatabase);

    }





}
