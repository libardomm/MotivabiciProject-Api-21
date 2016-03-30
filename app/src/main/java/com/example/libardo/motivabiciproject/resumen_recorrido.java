package com.example.libardo.motivabiciproject;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class resumen_recorrido extends ActionBarActivity {

    Bundle datos;
    String tiempoRecorrido;
    String velocidadMedia;
    String distanciaRecorrido;
    TextView caloriasRecorrido;
    TextView velocidadPromedio;
    TextView duracionRecorrido;
    TextView altitudMaxima;
    TextView distanciaRecorrida;
    Float dist;
    String distanciaPro;
    String altitudString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_recorrido);




        //Al pulsar el botón "Finalizar recorrido" pasa a la actividad de resumen del recorrido de la aplicación
        findViewById(R.id.botonGuardar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(resumen_recorrido.this, post_login.class));
            }
        });

        //Al tocar el texto "Calorías Quemadas" envía a la actividad de calorías
        findViewById(R.id.caloriasTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(resumen_recorrido.this, calorias.class));
            }
        });

        //Al tocar el texto "Duración del Recorrido" envía a la actividad de duracion_recorrido
        findViewById(R.id.duracionRecorridoTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(resumen_recorrido.this, duracion_recorrido.class));
            }
        });

        //Al tocar el texto "Distancia recorrida" envía a la actividad de distancia_recorrida
        findViewById(R.id.distanciaTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(resumen_recorrido.this, distancia_recorrida.class));
            }
        });

        //Al tocar el texto "Altitud máxima" envía a la actividad de altitud_maxima
        findViewById(R.id.altitudTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(resumen_recorrido.this, altitud_maxima.class));
            }
        });

        //Estas líneas de código estan solo para probar como funcionan los campos de texto!!! Los datos que aparecen en el resumen son falsos
        caloriasRecorrido = (TextView)findViewById(R.id.caloriasEditText);
        velocidadPromedio = (TextView)findViewById(R.id.velocidadEditText);
        duracionRecorrido = (TextView)findViewById(R.id.duracionEditText);
        altitudMaxima = (TextView)findViewById(R.id.altitudEditText);
        distanciaRecorrida = (TextView)findViewById(R.id.distanciaEditText);

        //CALCULO DE CALORIAS (Recibe el tiempo del recorrido desde la actividad "Recorrido")
        datos = getIntent().getExtras();
        tiempoRecorrido = datos.getString("tiempoRecorrido");
        distanciaPro=datos.getString("distanciaRecorrida");
        altitudString=datos.getString("altitudRecorrido");
        Log.i("altitud en resumen", String.valueOf(altitudString));
        Double t = Double.parseDouble(tiempoRecorrido);
        Double cal = (Double)(8 * 70 * t); //70 es el peso de la persona. Este dato se debe traer de la base de datos
        DecimalFormat df = new DecimalFormat("0.00");
        String sPi=df.format(cal);
        String caloriasQuemadas = sPi.toString();
        caloriasRecorrido.setText(caloriasQuemadas + " [cal]");//----------------------------------------------------------------------------CALORIIAS

        //CALCULO DE DURACIÓN DEL RECORRIDO
        Double time = (Double) (t / 0.000277778);
        Double horas = time / 3600;
        Double minutos = time / 60 % 60;
        Double segundos = time % 60;
        DecimalFormat df2 = new DecimalFormat("0");
        String Horas = df2.format(horas);
        String Minutos = df2.format(minutos);
        String Segundos = df2.format(segundos);
        duracionRecorrido.setText(Horas + " h: "+ Minutos + " min: " + Segundos + " seg");
       String tiempoPro=Horas + " h: "+ Minutos + " min: " + Segundos + " seg";//--------------------------------------------------------- TIEMPO PROMEDIO.


        //VER LA DISTANCIA RECORRIDA EN LA PANTALLA DE RESUMEN DEL RECORRIDO
        //recorrido rec=new recorrido();

        // float distan=rec.calcularDistancia();

        //String distanciaRecorridoString = String.valueOf(distan);
        distanciaRecorrida.setText(distanciaPro + " [m]");//-------------------------------------------------------------------------------DISTANCIA RECORRIDA
       // Log.i("distancia desde resumen",distanciaPro.toString());

        //VER LA VELOCIDAD MEDIA EN LA PANTALLA DE RESUMEN DEL RECORRIDO
        Double tiempoTotalSegundos;
        tiempoTotalSegundos=time;
        Double veloci;
        float distanciaFloat= Float.parseFloat(distanciaPro);
        veloci=distanciaFloat/tiempoTotalSegundos;

        velocidadPromedio.setText(veloci.toString()+" [m/s]");//--------------------------------------------------------------------------VELOCIDAD PROMEDIO


        //altitud
        altitudMaxima.setText(altitudString+" [m]");//-----------------------------------------------------------------------------------ALITUD MAXIMA

        caloriasRecorrido.setEnabled(false);
        velocidadPromedio.setEnabled(false);
        duracionRecorrido.setEnabled(false);
        altitudMaxima.setEnabled(false);
        distanciaRecorrida.setEnabled(false);

        //Agregar valores a la base de datos



    }

}
