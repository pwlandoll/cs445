package edu.jcu.kirsch.databaseexample1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Marc on 4/14/2016.
 */
public class CommentsDataSource {
    // This the class to handle DAO. It maintains the database connection and supports adding new comments and fetching all comments.

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {MySQLiteHelper.COLUMN_KEY, MySQLiteHelper.COLUMN_COMMENT};

    public CommentsDataSource(Context context){
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public OneComment createComment(String comment){
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
        long insertID = database.insert(MySQLiteHelper.TABLE_NAME, null, values);
        // Get the record we just created and return it as a OneComment object
        Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME, allColumns, MySQLiteHelper.COLUMN_KEY + "=" + insertID, null, null, null, null);
        cursor.moveToFirst();
        OneComment newComment = cursorToComment(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteComment(OneComment comment){
        long id = comment.getId();
        database.delete(MySQLiteHelper.TABLE_NAME, MySQLiteHelper.COLUMN_KEY + "=" + id, null);
    }

    public List<OneComment> getAllComments(){

    }

    private OneComment cursorToComment(Cursor cursor){
        OneComment comment = new OneComment();
        comment.setId(cursor.getLong(0));
        comment.setComment(cursor.getString(1));
        return comment;
    }
}
