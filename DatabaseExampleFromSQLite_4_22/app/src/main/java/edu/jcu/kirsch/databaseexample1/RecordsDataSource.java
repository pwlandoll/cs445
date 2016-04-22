package edu.jcu.kirsch.databaseexample1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marc on 4/14/2016.
 */
public class RecordsDataSource {
    // This the class to handle DAO. It maintains the database connection and supports adding new Records and fetching all Records.

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {MySQLiteHelper.COLUMN_KEY,MySQLiteHelper.COLUMN_NAME,MySQLiteHelper.COLUMN_EMAIL};

    public RecordsDataSource(Context context)
    {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        dbHelper.close();
    }

    public OneRecord createRecord(String name, String email){
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME,name);
        values.put(MySQLiteHelper.COLUMN_EMAIL,email);
        long insertID = database.insert(MySQLiteHelper.TABLE_NAME,null,values);
        //Get the record we just created and return it as a OneRecord
        Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME, allColumns, MySQLiteHelper.COLUMN_KEY + "=" + insertID, null, null, null, null);
        cursor.moveToFirst();
        OneRecord newRecord = cursorToRecord(cursor);
        cursor.close();
        return newRecord;
    }

    public void deleteRecord(OneRecord Record){
        long id = Record.getId();
        //Print out a message - log it
        database.delete(MySQLiteHelper.TABLE_NAME, MySQLiteHelper.COLUMN_KEY + "=" + id, null);
    }

    public void updateRecord(OneRecord record)
    {
        ContentValues args = new ContentValues();
        args.put(MySQLiteHelper.COLUMN_NAME,record.getName());
        args.put(MySQLiteHelper.COLUMN_EMAIL, record.getEmail());
        database.update(MySQLiteHelper.TABLE_NAME,args,MySQLiteHelper.COLUMN_KEY + "=" + record.getId(),null);
    }

    public List<OneRecord> getAllRecords(){
        List<OneRecord> Records = new ArrayList<OneRecord>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME,allColumns,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            OneRecord Record = cursorToRecord(cursor);
            Records.add(Record);
            cursor.moveToNext();
        }
        cursor.close();
        return Records;
    }

    private OneRecord cursorToRecord(Cursor cursor)
    {
        OneRecord record = new OneRecord();
        record.setId(cursor.getLong(0));
        record.setName(cursor.getString(1));
        record.setEmail(cursor.getString(2));
        return record;
    }
}
