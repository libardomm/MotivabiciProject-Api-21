package com.example.libardo.motivabiciproject;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

public class recorrido extends ActionBarActivity implements LocationListener {
    //Cambio en el cronometro del recorrido
    Button iniciar, pausar, detener, reestablecer, finalizar;
    Chronometer cronometro;

    long tiempoTranscurrido;

    LocationManager locationManager;
    String provider;
    Location location1 = new Location("l1");
    Location location2 = new Location("l2");
    String cadenalocation1;
    String cadenalocation2;
    String a;
    Float velocidadMedia;
    Float distanciaEnMetros;
    float distanciaPro;

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

        //código referente a la geolocalización


        //ubicación
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);
        Location location = locationManager.getLastKnownLocation(provider);
        String altitud;
        altitud= String.valueOf(location.getAltitude());
        if (location != null) {
            Toast.makeText(getApplicationContext(), "Si hay ubicacion", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), "No hay ubicacion", Toast.LENGTH_SHORT).show();

        }
        Toast.makeText(getApplicationContext(),"Altitud es "+String.valueOf(location.getAltitude()),Toast.LENGTH_SHORT);
    }

    public void iniciarRecorrido(View view) {

        location1=locationManager.getLastKnownLocation(provider);
        cadenalocation1= String.valueOf(location1.getLatitude());
        Toast.makeText(getApplicationContext(),"location 1 Latitud"+cadenalocation1,Toast.LENGTH_SHORT).show();

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

        //código referente a geolocalización
        onLocationChanged(location1);
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

    public void pararCronometro(View view) { //Este método detiene el recorrido, por lo cual se detiene el recorrido
        /*
        location2 = locationManager.getLastKnownLocation(provider);
        cadenalocation2 = String.valueOf(location2.getAltitude());
        Toast.makeText(getApplicationContext(),cadenalocation2,Toast.LENGTH_SHORT).show();
        */

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

    public void detenerRecorrido(View view) { //Esta función lanza la actividad "resumen_recorrido"
        cronometro.stop();

        location2=locationManager.getLastKnownLocation(provider);
        cadenalocation2= String.valueOf(location2.getLatitude());
        Toast.makeText(getApplicationContext(),"loction 2, latitud"+cadenalocation2,Toast.LENGTH_SHORT).show();


        distanciaPro = location1.distanceTo(location2);
        Toast.makeText(getApplicationContext(),"distancia "+distanciaPro,Toast.LENGTH_SHORT).show();
        Log.i("distancia para yurs", String.valueOf(distanciaPro));

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
        String distanciaPro1=String.valueOf(distanciaPro);
        //Envía el tiempo del recorrido a la actividad "Resumen recorrido"
        Intent pasarTiempo = new Intent(recorrido.this, resumen_recorrido.class);
        pasarTiempo.putExtra("tiempoRecorrido", tiempoFinal);


        pasarTiempo.putExtra("distanciaRecorrida", distanciaPro1);
        startActivity(pasarTiempo);

        //Envía el tiempo del recorrido a la actividad "Resumen recorrido"
       /* Intent pasarDistancia = new Intent(recorrido.this, resumen_recorrido.class);
        pasarDistancia.putExtra("distanciaRecorrida", distanciaPro);
        startActivity(pasarDistancia); */



        //Calcular la velocidad media
  //        velocidadMedia = distanciaEnMetros / tiempoTranscurrido;//metros por segundo
       // Toast.makeText(getApplicationContext(), velocidadMedia.toString(), Toast.LENGTH_SHORT).show();
       // pasarTiempo.putExtra("velocidadMedia", velocidadMedia);
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
    //métodos adicionales

    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        Double lat = location.getLatitude();
        Double lng = location.getLongitude();

        Log.i("Longitud", lat.toString());
        Log.i("Latitud", lng.toString());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Double lat = location1.getLatitude();
        Double lng = location1.getLongitude();

        Log.i("Longitud", lat.toString());
        Log.i("Latitud", lng.toString());
    }

    @Override
    public void onProviderEnabled(String s) {}

    @Override
    public void onProviderDisabled(String s) {}


    //metodos nuevos relacionados con ubicacion

    public float calcularDistancia() {
        //float midistancia = location1.distanceTo(location2);
         //String distance = String.valueOf(midistancia);
        //Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_SHORT).show();
       // return midistancia;


       return distanciaPro;


    }

    public  Float velocidadMedia(){
        velocidadMedia=distanciaEnMetros/tiempoTranscurrido;//metros por segundo

       // Toast.makeText(getApplicationContext(),velocidadMedia.toString(),Toast.LENGTH_SHORT).show();

     return velocidadMedia;
    }



}
