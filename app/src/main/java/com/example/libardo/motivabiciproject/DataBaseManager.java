package com.example.libardo.motivabiciproject;

import android.content.ContentValues;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ROLANDO on 24/03/2016.
 */
public class DataBaseManager {
    ContentValues registro = new ContentValues();
    public static final String TABLE_USUARIOS = "usuarios";
    public static final String TABLE_RECORRIDOS = "recorrido";

    private static final String CN_EMAIL = "email";
    private static final String CN_NAME = "nombre";
    private static final String CN_PASSWORD = "password";
    private static final String CN_AGE = "edad";
    private static final String CN_WEIGHT = "peso";
    private static final String CN_ID = "id";
    private static final String CN_DATE = "fecha";
    private static final String CN_CALORIES = "calorias";
    private static final String CN_TIME = "tiempo";
    private static final String CN_ALTITUDE = "altitud";
    private static final String CN_LONGITUDE = "longitud";
    private static final String CN_SPEED = "velocidad";
    private static final String CN_DISTANCE = "distancia";

    public static final String CREATE_TABLE_USUARIOS = "create table "+TABLE_USUARIOS+"("+CN_EMAIL+" text primary key, "+CN_NAME+" text, "+CN_PASSWORD+" text, "+CN_AGE+" integer, "+CN_WEIGHT+" integer)";
    public static final String CREATE_TABLE_RECORRIDOS = "create table "+TABLE_RECORRIDOS+"("+CN_ID+" integer primary key,"+CN_EMAIL+" text, "+CN_DATE+" text, "+CN_CALORIES+" real, "+CN_SPEED+" real, "+CN_DISTANCE+" real, "+CN_TIME+" text)";
    public static final String CREATE_TABLE_UBICACIONES = null;
    public static final String DROP_TABLE_USUARIOS = "drop table if exists "+TABLE_USUARIOS;


    public String getDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public void recolectarDatosRecorrido(String email){
        registro.put("email",email);

    }

    public void recolectarDatosRecorrido(String fecha,String calorias, String velocidad, String distancia, String tiempo){
        registro.put("fecha",fecha);
        registro.put("calorias",calorias);
        registro.put("velocidad",velocidad);
        registro.put("distancia",distancia);
        registro.put("tiempo",tiempo);
    }
}
