package ru.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String DB_LOG_TAG = "myDBtag";
    Button btnRec,btnRead,btnAdd,btnClear, btnAbout;
    EditText etName;
    EditText descr;
    DBHelper dbHelper;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnRead = findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);
        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
        btnAbout = findViewById(R.id.about);
        btnAbout.setOnClickListener(this);

        etName = findViewById(R.id.etName);
        descr = findViewById(R.id.desc);
        dbHelper = new DBHelper(this);
    }
    @Override
    public void onClick(View v) {

        ContentValues cv = new ContentValues();
        String name = etName.getText().toString();
        String description = descr.getText().toString();


        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch(v.getId()){
            case R.id.btnAdd:
                Log.d(DB_LOG_TAG,"Начинаем запись в БД");
                Date currentDate = new Date();
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy",Locale.getDefault());
                String dateText = dateFormat.format(currentDate);
                cv.put("name",name);
                cv.put("descr",description);
                cv.put("date",dateText);
                long rowID = db.insert("NOTES",null,cv);
                Log.d(DB_LOG_TAG,"Вставили запись в базу данных, id = " + rowID);
                break;
            case R.id.btnRead:
                Log.d(DB_LOG_TAG,"Начинаем чтение из БД");
                Cursor c = db.query("NOTES",null,null,null,null,null,null);
                if(c.moveToFirst()){
                    int idColIndex = c.getColumnIndex("id");
                    int nameColIndex = c.getColumnIndex("name");
                    int descrColIndex = c.getColumnIndex("descr");
                    int dateColIndex = c.getColumnIndex("date");

                    do{
                        Log.d(DB_LOG_TAG,
                                "id = " +c.getInt(idColIndex) +
                                     ", name = " +c.getString(nameColIndex) +
                                     ", descr = " +c.getString(descrColIndex) +
                                     ", date = " +c.getString(dateColIndex)
                                );
                    } while (c.moveToNext());
                } else
                    Log.d(DB_LOG_TAG,"Записи в бд отсутствуют");
                    c.close();
                    break;
            case R.id.btnClear:
                Log.d(DB_LOG_TAG,"Начали чистку бд");
                int clearCount = db.delete("NOTES",null,null);
                Log.d(DB_LOG_TAG,"Все записи удалены, количество = " + clearCount);
                break;

            case R.id.about:
                Toast.makeText(getApplicationContext(),"Автор приложения: Фролова СИ", Toast.LENGTH_SHORT).show();
        }
        dbHelper.close();
    }

}