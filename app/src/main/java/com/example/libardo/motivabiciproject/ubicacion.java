package com.example.libardo.motivabiciproject;

import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

public class ubicacion extends ActionBarActivity implements LocationListener {
  LocationManager locationManager;
    String provider;
    Button botonEmpezar;
    Button botonTerminar;
    Button botonDistancia;
    Button botonRestablecer;
    Button botonvelocidad;
    Location location1;
    Location location2;
    String cadenalocation1;
    String cadenalocation2;
   //calcular tiempo transcurrido
    Chronometer cronometro;
    long tiempoTranscurrido;

    //velocidad media
    Float velocidadMedia;
    Float distanciaEnMetros;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion);
        //botones y textViews
        botonEmpezar=(Button)findViewById(R.id.bttIniciar);
        botonTerminar=(Button)findViewById(R.id.bttTerminar);
        botonDistancia=(Button)findViewById(R.id.bttDistancia);
        botonRestablecer=(Button)findViewById(R.id.bttRestablecer);
        botonvelocidad=(Button)findViewById(R.id.bttVelocidadMedia);

        //ubicacion
        locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        provider=locationManager.getBestProvider(new Criteria(),false);
        Location location=locationManager.getLastKnownLocation(provider);

        if(location!=null){
            Toast.makeText(getApplicationContext(),"Si hay ubicacion",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"Si hay ubicacion",Toast.LENGTH_SHORT).show();

        }
        //tiempo
        cronometro = (Chronometer) findViewById(R.id.cronometro);

    }
    //metodos adicionaeles

    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(provider,400,1,this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    //gps-----------------------------------------------------
    @Override
    public void onLocationChanged(Location location) {
        Double lat=location.getLatitude();
        Double lng=location.getLongitude();

        Log.i("Longitud",lng.toString());
        Log.i("Latitud",lat.toString());

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

//generados automaticamente

public void empezarRecorrido(View v){
   location1=locationManager.getLastKnownLocation(provider);
    cadenalocation1= String.valueOf(location1.getAltitude());
    Toast.makeText(getApplicationContext(),cadenalocation1,Toast.LENGTH_SHORT).show();

    //iniciar cronometro
    cronometro.setBase(SystemClock.elapsedRealtime());
    cronometro.start();
    botonEmpezar.setEnabled(false);
    botonTerminar.setEnabled(true);
    botonRestablecer.setEnabled(false);
    botonDistancia.setEnabled(false);
    botonvelocidad.setEnabled(false);




}
    public  void TerminarRecorrido(View v){

        location2=locationManager.getLastKnownLocation(provider);
        cadenalocation2= String.valueOf(location2.getAltitude());
        Toast.makeText(getApplicationContext(),cadenalocation2,Toast.LENGTH_SHORT).show();


        //detener cronometro
        cronometro.stop();
        //Guarda el tiempo transcurrido en una variable tipo long
        tiempoTranscurrido = SystemClock.elapsedRealtime() - cronometro.getBase();
        String tiempoFinal = String.valueOf(((tiempoTranscurrido / 1000) * 0.000277778));
        Log.i("Tiempo sape", String.valueOf(tiempoTranscurrido));

        botonEmpezar.setEnabled(false);
        botonTerminar.setEnabled(false);
        botonRestablecer.setEnabled(true);
        botonDistancia.setEnabled(true);
        botonvelocidad.setEnabled(false);

    }
    public void restablecer(View v){
        cronometro.setBase(SystemClock.elapsedRealtime());
        botonEmpezar.setEnabled(true);
        botonTerminar.setEnabled(false);
        botonRestablecer.setEnabled(false);
        botonDistancia.setEnabled(false);
        botonvelocidad.setEnabled(false);

    }

    public void calcularDistancia(View v){
         distanciaEnMetros = location1.distanceTo(location2);
        Toast.makeText(getApplicationContext(),distanciaEnMetros.toString(),Toast.LENGTH_SHORT).show();
        botonEmpezar.setEnabled(false);
        botonTerminar.setEnabled(false);
        botonRestablecer.setEnabled(true);
        botonDistancia.setEnabled(false);
        botonvelocidad.setEnabled(true);
    }
    public  void velocidadMedia(View v){
        velocidadMedia=distanciaEnMetros/tiempoTranscurrido;

        Toast.makeText(getApplicationContext(),velocidadMedia.toString(),Toast.LENGTH_SHORT).show();

        botonEmpezar.setEnabled(false);
        botonTerminar.setEnabled(false);
        botonRestablecer.setEnabled(true);
        botonDistancia.setEnabled(false);
        botonvelocidad.setEnabled(false);
    }


}