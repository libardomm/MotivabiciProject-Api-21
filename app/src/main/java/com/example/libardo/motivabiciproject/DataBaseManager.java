package com.example.libardo.motivabiciproject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ROLANDO on 24/03/2016.
 */
public class DataBaseManager {
    public static final String TABLE_USUARIOS = "usuarios";
    public static final String TABLE_RECORRIDOS = "recorrido";

    private static final String CN_EMAIL = "email";
    private static final String CN_NAME = "nombre";
    private static final String CN_PASSWORD = "password";
    private static final String CN_AGE = "edad";
    private static final String CN_WEIGHT = "peso";

    public static final String CREATE_TABLE_USUARIOS = "create table "+TABLE_USUARIOS+"("+CN_EMAIL+" text primary key, "+CN_NAME+" text, "+CN_PASSWORD+" text, "+CN_AGE+" integer, "+CN_WEIGHT+" integer)";
    public static final String CREATE_TABLE_RECORRIDOS = null;
    public static final String CREATE_TABLE_UBICACIONES = null;
    public static final String DROP_TABLE_USUARIOS = "drop table if exists "+TABLE_USUARIOS;

    public String getDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
