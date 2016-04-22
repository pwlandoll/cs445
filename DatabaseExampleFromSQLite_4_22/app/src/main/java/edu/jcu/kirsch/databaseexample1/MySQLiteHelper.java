package edu.jcu.kirsch.databaseexample1;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Marc on 4/14/2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME =	"blogger";
    public static final String COLUMN_KEY = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    private static final String DATABASE_NAME = "blogger.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE="create table blogger(_id integer primary key autoincrement,name text not null,email text not null);";
    public MySQLiteHelper (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try
        {
            db.execSQL(DATABASE_CREATE);
        }
        catch (SQLException ex)
        {
            Log.d("SQL Error","Cannot create databasee");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + " , which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
