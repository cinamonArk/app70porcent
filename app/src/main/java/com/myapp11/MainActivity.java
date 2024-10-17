package com.myapp11;

import android.content.Intent; // Importación necesaria para Intent
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btn_Inicio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_Inicio = findViewById(R.id.btn_Inicio);
        btn_Inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAlHome();
            }
        });
    }


    public void irAlHome(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);

    }
}
