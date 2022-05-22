package ru.example.mynotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "NOTES";
    public static final String NAME = "NAME";
    public static final String DESCR = "DESCR";
    public static final String DATE = "DATE";
    public DBHelper(@Nullable Context context){
        super(context,"notesDB",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE NOTES ("
                        + "id integer primary key autoincrement,"
                        +"name text,"
                        +"descr text,"
                        +"date text" +");"
                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
