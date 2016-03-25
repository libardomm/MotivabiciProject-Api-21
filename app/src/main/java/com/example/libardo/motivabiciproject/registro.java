package com.example.libardo.motivabiciproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class registro extends ActionBarActivity {
    private EditText etNombre, etEmail, etPass, etEdad, etPeso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        etNombre = (EditText) findViewById(R.id.edtNombre);
        etEmail = (EditText) findViewById(R.id.edtEmail);
        etPass = (EditText) findViewById(R.id.edtPass);
        etEdad = (EditText) findViewById(R.id.edtEdad);
        etPeso = (EditText) findViewById(R.id.edtPeso);

        //Al hacer click en el botón "Enviar" envía a la actividad de recorrido
        findViewById(R.id.enviarButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registrar(v);
                startActivity(new Intent(registro.this, post_login.class));
            }
        });

    }

    public void Registrar(View v){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "AplicacionBD", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String email = etEmail.getText().toString();
        String nombre = etNombre.getText().toString();
        String password = etPass.getText().toString();
        String edad = etEdad.getText().toString();
        String peso = etPeso.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("email", email);
        registro.put("nombre", nombre);
        registro.put("password", password);
        registro.put("edad", edad);
        registro.put("peso", peso);

        // los inserto en la base de datos
        bd.insert("usuario", null, registro);
        bd.close();

        // ponemos los campos a vacío para insertar el siguiente usuario
        etNombre.setText(""); etEmail.setText(""); etPass.setText(""); etEdad.setText(""); etPeso.setText("");
        Toast.makeText(this, "Datos del usuario cargados", Toast.LENGTH_SHORT).show();
    }
}
