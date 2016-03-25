package com.example.libardo.motivabiciproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ROLANDO on 23/03/2016.
 */

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //aquí creamos la tabla de usuario (dni, nombre, ciud
        //ad, numero)
        db.execSQL("CREATE TABLE usuario(email TEXT PRIMARY KEY, nombre TEXT, password TEXT, edad INTEGER, peso INTEGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int version1, int version2) {
        //db.execSQL("drop table if exists usuario");
        db.execSQL("CREATE TABLE usuario(email TEXT PRIMARY KEY, nombre TEXT, password TEXT, edad INTEGER, peso INTEGER)");
    }
}
