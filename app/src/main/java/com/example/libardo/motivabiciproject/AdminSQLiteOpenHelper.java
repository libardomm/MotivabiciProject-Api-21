package com.example.libardo.motivabiciproject;

import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ROLANDO on 23/03/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME  = "AplicacionBD.sqlite";
    private static final int DB_SCHEME_VERSION = 1;

    public AdminSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //aquí creamos la tabla de usuario (dni, nombre, ciud
        //ad, numero)
        db.execSQL(DataBaseManager.CREATE_TABLE_USUARIOS);
        db.execSQL(DataBaseManager.CREATE_TABLE_RECORRIDOS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int version1, int version2) {
        db.execSQL(DataBaseManager.DROP_TABLE_USUARIOS);
        db.execSQL(DataBaseManager.CREATE_TABLE_USUARIOS);
    }
}
