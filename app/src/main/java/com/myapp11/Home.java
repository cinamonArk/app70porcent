package com.myapp11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    Button btnIrRegistro;
    Button btnIrInicioSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        btnIrRegistro = findViewById(R.id.btn_registro);
        btnIrInicioSesion = findViewById(R.id.btn_Inicio_sesion);

        btnIrInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ir(); // Llamada a la función ir() para iniciar InicioSesion
            }
        });

        btnIrRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAlRegistro();
            }
        });
    }

    public void irAlRegistro() {
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }

    public void ir() {
        Intent intent = new Intent(this, InicioSesion.class);
        startActivity(intent);
    }
}
