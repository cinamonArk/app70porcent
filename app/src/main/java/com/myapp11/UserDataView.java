package com.myapp11;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserDataView extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("usuarios");





    Button btnConsultaUsuario;
    Button btnVolverAlDashBoard1;
    EditText inputIngresarId;

    TextView mostrarId;
    TextView mostrarNombre;
    TextView mostrarApellido;
    TextView mostrarTelefono;
    TextView mostrarCorreo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_data_view);

        btnConsultaUsuario = findViewById(R.id.btn_buscar_usuario);
        btnVolverAlDashBoard1 = findViewById(R.id.btn_volver_dash1);
        inputIngresarId = findViewById(R.id.input_id_buscar);
        mostrarId = findViewById(R.id.mostrar_id_usuario);
        mostrarNombre = findViewById(R.id.mostrar_nombre_usuario);
        mostrarApellido = findViewById(R.id.mostrar_apellido);
        mostrarCorreo = findViewById(R.id.mostrar_correo);
        mostrarTelefono = findViewById(R.id.mostrar_telefono);

        btnConsultaUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarDataUsuario();
            }
        });
    }

    public void recuperarDataUsuario(){

        String usuarioId = inputIngresarId.getText().toString();

        DatabaseReference referenciaUsuario = reference.child(usuarioId);

        referenciaUsuario.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){

                    String usuarioId = inputIngresarId.getText().toString();
                    mostrarId.setText(usuarioId);


                    String nombre = snapshot.child("Nombre").getValue(String.class);
                    mostrarNombre.setText("Nombre" + nombre);

                    String apellido = snapshot.child("Apellido").getValue(String.class);
                    mostrarApellido.setText("Apellido" + apellido);

                    String telefono = snapshot.child("Telefono").getValue(String.class);
                    mostrarTelefono.setText("Telefono" + telefono);

                    String correo = snapshot.child("Correo").getValue(String.class);
                    mostrarCorreo.setText("Correo" + correo);


                }else {
                    Toast.makeText(UserDataView.this,"Datos no encontrados",Toast.LENGTH_SHORT).show();
                }
            }








            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}