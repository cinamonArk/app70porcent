package com.myapp11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DasBoardt extends AppCompatActivity {

    Button btnVerDatosUsuario;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_das_boardt);

        btnVerDatosUsuario = findViewById(R.id.btn_ver_datos);


        btnVerDatosUsuario.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                irVerDatosUsuario();
            }
        });

    }
    public void irVerDatosUsuario(){
        Intent intent = new Intent(this, UserDataView.class);
        startActivity(intent);
    }
}
