package com.haixl.sqlitetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.telephony.ims.ImsReasonInfo;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.haixl.sqlitetest.adapter.listViewAdapter;
import com.haixl.sqlitetest.model.student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase database;
    EditText edName,edClass;
    ListView lvSinhVien;
    ArrayList<student> sinhvien;
    listViewAdapter listViewAdapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edName=findViewById(R.id.edName);
        edClass=findViewById(R.id.edClass);
        lvSinhVien=findViewById(R.id.listSinhVien);

        initData();
        readData();
    }

    private void readData() {
        sinhvien=new ArrayList<>();
        int i=1;
        String sql="SELECT * FROM thongtinsv";
        Cursor cursor=database.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            sinhvien.add(new student(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
            cursor.moveToNext();
            i++;
        }
        cursor.close();

        listViewAdapters=new listViewAdapter(this,sinhvien);
        lvSinhVien.setAdapter(listViewAdapters);
    }

    private void  initData(){
        database=openOrCreateDatabase("sinhvien.db",MODE_PRIVATE,null);
        String sql="CREATE TABLE IF NOT EXISTS thongtinsv (id integer primary key autoincrement, name text, lop text)";
        database.execSQL(sql);
    }

    public void btn_add(View view) {
        if (edName.getText().toString().equals("")){
            edName.setError("Tên không được bỏ trống");
        }else if (edClass.getText().toString().equals("")){
          edClass.setError("Lớp không được bỏ trống");
        }else{
            ContentValues values=new ContentValues();
            values.put("name",edName.getText().toString());
            values.put("lop",edClass.getText().toString());
            database.insert("thongtinsv",null,values);
            reset();
            readData();
        }
    }

    public void reset(){
        edName.setText("");
        edClass.setText("");
    }
}