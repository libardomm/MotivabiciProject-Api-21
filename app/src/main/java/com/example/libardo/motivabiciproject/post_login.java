package com.example.libardo.motivabiciproject;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class post_login extends ActionBarActivity {
    private int mCurrent = 0;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login);

        //Al hacer click en el botón "Iniciar Recorrido" envía a la actividad de recorrido
        findViewById(R.id.iniciarRecorridoButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(post_login.this, recorrido.class));
            }
            public void cerrar(View view) {
                finish();
            }
        });

        //Al hacer click en el botón "Iniciar Sesión" envía a la actividad de recorrido
        findViewById(R.id.recorridosAnterioresButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(post_login.this, recorridos_anteriores.class));
            }
        });

    }

    public void informacionImportante(View view){

        ImageView informacionImportante1 = (ImageView)findViewById(R.id.informacionImportante1View);
        ImageView informacionImportante2 = (ImageView)findViewById(R.id.informacionImportante2View);
        ImageView informacionImportante3 = (ImageView)findViewById(R.id.informacionImportante3View);
        ImageView informacionImportante4 = (ImageView)findViewById(R.id.informacionImportante4View);
        ImageView informacionImportante5 = (ImageView)findViewById(R.id.informacionImportante5View);
        ImageView informacionImportante6 = (ImageView)findViewById(R.id.informacionImportante6View);
        ImageView informacionImportante7 = (ImageView)findViewById(R.id.informacionImportante7View);
        ImageView informacionImportante8 = (ImageView)findViewById(R.id.informacionImportante8View);

        if (informacionImportante1.getAlpha() == 1f){
            informacionImportante1.animate().alpha(0f).setDuration(2000);
            informacionImportante2.animate().alpha(1f).setDuration(2000);

        }
        else if(informacionImportante2.getAlpha() == 1f){
            informacionImportante2.animate().alpha(0f).setDuration(2000);
            informacionImportante3.animate().alpha(1f).setDuration(2000);
        }
        else if(informacionImportante3.getAlpha() == 1f){
            informacionImportante3.animate().alpha(0f).setDuration(2000);
            informacionImportante4.animate().alpha(1f).setDuration(2000);
        }
        else if(informacionImportante4.getAlpha() == 1f){
            informacionImportante4.animate().alpha(0f).setDuration(2000);
            informacionImportante5.animate().alpha(1f).setDuration(2000);
        }
        else if(informacionImportante5.getAlpha() == 1f){
            informacionImportante5.animate().alpha(0f).setDuration(2000);
            informacionImportante6.animate().alpha(1f).setDuration(2000);
        }
        else if(informacionImportante6.getAlpha() == 1f){
            informacionImportante6.animate().alpha(0f).setDuration(2000);
            informacionImportante7.animate().alpha(1f).setDuration(2000);
        }
        else if(informacionImportante7.getAlpha() == 1f){
            informacionImportante7.animate().alpha(0f).setDuration(2000);
            informacionImportante8.animate().alpha(1f).setDuration(2000);
        }
        else if(informacionImportante8.getAlpha() == 1f){
            informacionImportante8.animate().alpha(0f).setDuration(2000);
            informacionImportante1.animate().alpha(1f).setDuration(2000);
        }

    }
}
