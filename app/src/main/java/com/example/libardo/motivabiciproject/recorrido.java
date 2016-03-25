package com.example.libardo.motivabiciproject;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

public class recorrido extends ActionBarActivity implements LocationListener {
        //Cambio en el cronometro del recorrido
        Button iniciar, pausar, detener, reestablecer, finalizar;
        Chronometer cronometro;

        long tiempoTranscurrido;

        LocationManager locationManager;
        String provider;
        Location location = new Location("l1");
        Location location2 = new Location("l2");
        String a;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_recorrido);

            cronometro = (Chronometer) findViewById(R.id.cronometro);
            iniciar = (Button) findViewById(R.id.botonIniciar);
            pausar = (Button) findViewById(R.id.botonPausar);
            detener = (Button) findViewById(R.id.botonDetener);
            reestablecer = (Button) findViewById(R.id.botonReestablecer);
            finalizar = (Button) findViewById(R.id.botonFinalizarRecorrido);

            ImageView androidImageField = (ImageView) findViewById(R.id.imagenEstadoRecorrido);
            iniciar.setEnabled(true);
            pausar.setEnabled(false);
            detener.setEnabled(false);
            reestablecer.setEnabled(false);
            finalizar.setEnabled(false);

            //codigo referente a la geolocalizacion
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


            /*provider = locationManager.getBestProvider(new Criteria(), false);
            int permissionCheck = ContextCompat.checkSelfPermission(recorrido.this, Manifest.permission.ACCESS_FINE_LOCATION);
            if (permissionCheck == 0) {
                location = locationManager.getLastKnownLocation(provider);
            }*/

            if (location != null) {
                Log.i("Location Info", "Si encontro ubicacion");
                Log.i("Location Info", String.valueOf(location.getAltitude()));

            } else {
                Log.i("Location Info", "No se encontro la ubiacion");
            }
        }

        public void iniciarRecorrido(View view) {
            cronometro.setBase(SystemClock.elapsedRealtime());
            cronometro.start(); //Inicia el cronometro

            ImageView androidImageField = (ImageView) findViewById(R.id.imagenEstadoRecorrido);
            iniciar.setEnabled(false);
            pausar.setEnabled(true);
            detener.setEnabled(true);
            reestablecer.setEnabled(false);
            if (pausar.getText().equals("Continuar")) {
                pausar.setText("Pausar");
            }
            androidImageField.setImageResource(R.drawable.play);
            finalizar.setEnabled(false);

            //codigo referente a geolocalizacion


            onLocationChanged(location);


        }

        public void pausarRecorrido(View view) {

            if (pausar.getText().equals("Pausar")) {
                cronometro.stop();
                ImageView androidImageField = (ImageView) findViewById(R.id.imagenEstadoRecorrido);
                pausar.setText("Continuar");
                androidImageField.setImageResource(R.drawable.pause);
            } else {
                int stoppedMilliseconds = 0;
                String chronoText = cronometro.getText().toString();
                String array[] = chronoText.split(":");
                if (array.length == 2) {
                    stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 1000 + Integer.parseInt(array[1]) * 1000;
                } else if (array.length == 3) {
                    stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60 * 1000 + Integer.parseInt(array[1]) * 60 * 1000 + Integer.parseInt(array[2]) * 1000;
                }
                cronometro.setBase(SystemClock.elapsedRealtime() - stoppedMilliseconds);
                cronometro.start();

                ImageView androidImageField = (ImageView) findViewById(R.id.imagenEstadoRecorrido);
                pausar.setText("Pausar");
                reestablecer.setEnabled(true);
                androidImageField.setImageResource(R.drawable.play);
            }

            iniciar.setEnabled(false);
            pausar.setEnabled(true);
            detener.setEnabled(true);
            reestablecer.setEnabled(false);
            finalizar.setEnabled(false);
        }

        public void pararCronometro(View view) {
            cronometro.stop();

            ImageView androidImageField = (ImageView) findViewById(R.id.imagenEstadoRecorrido);
            iniciar.setEnabled(false);
            pausar.setEnabled(false);
            detener.setEnabled(false);
            reestablecer.setEnabled(true);
            if (pausar.getText().equals("Continuar")) {
                pausar.setText("Pausar");
            }
            androidImageField.setImageResource(R.drawable.stop);
            finalizar.setEnabled(true);
        }

        public void detenerRecorrido(View view) {
            cronometro.stop();

            ImageView androidImageField = (ImageView) findViewById(R.id.imagenEstadoRecorrido);
            iniciar.setEnabled(false);
            pausar.setEnabled(false);
            detener.setEnabled(false);
            reestablecer.setEnabled(true);
            if (pausar.getText().equals("Continuar")) {
                pausar.setText("Pausar");
            }
            androidImageField.setImageResource(R.drawable.stop);
            finalizar.setEnabled(true);

            //Guarda el tiempo transcurrido en una variable tipo long
            tiempoTranscurrido = SystemClock.elapsedRealtime() - cronometro.getBase();
            String tiempoFinal = String.valueOf(((tiempoTranscurrido / 1000) * 0.000277778));

            //Envía el tiempo del recorrido a la actividad "Resumen recorrido"
            Intent pasarTiempo = new Intent(recorrido.this, resumen_recorrido.class);
            pasarTiempo.putExtra("tiempoRecorrido", tiempoFinal);
            startActivity(pasarTiempo);
        }

        public void restablecerCronometro(View view) {
            cronometro.setBase(SystemClock.elapsedRealtime());

            ImageView androidImageField = (ImageView) findViewById(R.id.imagenEstadoRecorrido);
            iniciar.setEnabled(true);
            pausar.setEnabled(false);
            detener.setEnabled(false);
            reestablecer.setEnabled(false);
            if (pausar.getText().equals("Continuar")) {
                pausar.setText("Pausar");
            }
            androidImageField.setImageResource(R.drawable.stopwatch);
            finalizar.setEnabled(false);
        }

        //Codigo nuevo referente a la geolocalizacion
        //--------------------------------------------------------------------------------------------------
        @Override
        public void onLocationChanged(Location location) {
            Double lat = location.getLatitude();
            Double lng = location.getLongitude();

            Log.i("Longitud", lat.toString());
            Log.i("Latitud", lng.toString());
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            Double lat = location.getLatitude();
            Double lng = location.getLongitude();

            Log.i("Longitud", lat.toString());
            Log.i("Latitud", lng.toString());
        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
}
