package ninja.paranoidandroid.traintraveler.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ninja.paranoidandroid.traintraveler.R;
import ninja.paranoidandroid.traintraveler.util.DBStatementsExtractor;

/**
 * Created by anton on 19.06.16.
 */
public class DBHelper extends SQLiteOpenHelper {

    private Context mContext;

    public DBHelper(Context context){
        super(context, DBContract.NAME, null, DBContract.VERSION);
        mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String[] dbStatements = DBStatementsExtractor.getSQLiteStatemnts(mContext, R.raw.create_db);

        for (String statement: dbStatements) {

            sqLiteDatabase.execSQL(statement);

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
