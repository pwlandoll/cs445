package edu.jcu.kirsch.databaseexample1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marc on 4/14/2016.
 */
public class CommentsDataSource {
    // This the class to handle DAO. It maintains the database connection and supports adding new comments and fetching all comments.

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {MySQLiteHelper.COLUMNE_ID,MySQLiteHelper.COLUMN_COMMENT};

    public CommentsDataSource(Context context){
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {

    }

    public void close(){
        dbHelper.close();
    }

    public OneComment createComment(String comment){

    }

    public void deleteComment(OneComment comment){

    }

    public List<OneComment> getAllComments(){

    }

    private OneComment cursorToComment(Cursor cursor){

    }
}
