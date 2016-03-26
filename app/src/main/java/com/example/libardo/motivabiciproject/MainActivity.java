package com.example.libardo.motivabiciproject;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    private EditText edtPass;
    private EditText edtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtPass = (EditText) findViewById(R.id.edtPass);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        Toast.makeText(getApplicationContext(), "¡Bienvenido(a) a MotivaBici!", Toast.LENGTH_LONG).show();

        //Al tocar sobre el título "Motivabici" pasa a la actividad de información de la aplicación
        findViewById(R.id.tituloMotivaBici).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FullscreenActivity.class));
            }
        });

        //Al hacer click en el botón "Iniciar Sesión" envía a la actividad de recorrido
        findViewById(R.id.iniciarSesionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Ingresar(v)) {
                    startActivity(new Intent(MainActivity.this, post_login.class));
                } else {
                    edtEmail.setText("");
                    edtPass.setText("");
                }
            }
        });


        //Al hacer click sobre el botón "Registrase" envía a la actividad de formulario de registro
        findViewById(R.id.registrarseButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, registro.class));
            }
        });

    }
    public boolean Ingresar(View v) {
        boolean bandera;
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);

        SQLiteDatabase bd = admin.getReadableDatabase();
        String email = edtEmail.getText().toString();
        String password = edtPass.getText().toString();

        String consulta = "select nombre, email from usuarios where email='" + email + "' and password='" + password + "'";
        Cursor fila = bd.rawQuery(
                consulta, null);
        if (fila.moveToFirst()) {
            Toast.makeText(this, "Consulta hecha satisfactoriamente",
                    Toast.LENGTH_SHORT).show();

            bandera = true;
        } else {
            Toast.makeText(this, "No existe ningún usuario con ese email",
                    Toast.LENGTH_SHORT).show();
            bandera = false;
        }

        bd.close();
        return bandera;

    }

}