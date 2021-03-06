package com.example.admin.achmadmuhaiminaziz_1202154219_studycase5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Admin on 3/25/2018.
 */

public class database extends SQLiteOpenHelper {
    //membuat variabel untuk database
    Context cntx;
    SQLiteDatabase db;
    public static final String nama_db = "listtodo.db";
    public static final String nama_tabel = "daftartodo";
    public static final String col_1 = "todo";
    public static final String col_2 = "description";
    public static final String col_3 = "priority";
    //kontruktor
    public database(Context context) {
        super(context, nama_db, null, 1);
        this.cntx = context;
        db = this.getWritableDatabase();
        db.execSQL("create table if not exists "+nama_tabel+" (todo varchar(35) primary key, description varchar(50), priority varchar(3))");
    }
    //ketika database dibuat
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists "+nama_tabel+" (todo varchar(35) primary key, description varchar(50), priority varchar(3))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+nama_tabel);
        onCreate(sqLiteDatabase);
    }

    public boolean inputdata(AddData list) {
        //mencocokkan kolom beserta nilainya
        ContentValues val = new ContentValues();
        val.put(col_1, list.getTodo());
        val.put(col_2, list.getDesc());
        val.put(col_3, list.getPrior());
        long hasil = db.insert(nama_tabel, null, val);
        //membuat kondisi untuk input data jika data kosong
        if (hasil==-1) {
            return false;
        }else {
            return true;
        }
    }

    //method untuk menghapus data pada database
    public boolean removedata(String ToDo) {
        return db.delete(nama_tabel, col_1+"=\""+ToDo+"\"", null)>0;
    }
    //method untuk mengakses dan membaca data dari database
    public void readdata(ArrayList<AddData> daftar){
        Cursor cursor = this.getReadableDatabase().rawQuery("select todo, description, priority from "+nama_tabel, null);
        while (cursor.moveToNext()){
            daftar.add(new AddData(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }
    }
}
