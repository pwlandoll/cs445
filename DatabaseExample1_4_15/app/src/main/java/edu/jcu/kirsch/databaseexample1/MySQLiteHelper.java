package edu.jcu.kirsch.databaseexample1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Marc on 4/14/2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_COMMENTS =	"comments";
    public static final String COLUMNE_ID = "_id";
    public static final String COLUMN_COMMENT = "comment";
    private static final String DATABASE_NAME = "comments.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_DATABASE = "create table " + TABLE_COMMENTS + "(" + COLUMNE_ID + " integer primary key autoincrement," + COLUMN_COMMENT+ " text not null)";

    public MySQLiteHelper (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + " , which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }

}
