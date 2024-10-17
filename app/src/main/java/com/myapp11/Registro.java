package com.myapp11;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registro extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();
    FirebaseAuth auth = FirebaseAuth.getInstance();

    Button btnVolverDesdeRegistroAHome;
    EditText inputIdUsuario;
    EditText inputNombreUsuario; // Asegúrate de que este EditText esté inicializado
    EditText inputApellidoUsuario;
    EditText inputCorreoUsuario;
    EditText inputTelefonoUsuario;
    EditText inputContrasena;
    Button btnGuardarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);

        btnVolverDesdeRegistroAHome = findViewById(R.id.btn_Volver_registro);
        inputIdUsuario = findViewById(R.id.input_user_id);
        inputNombreUsuario = findViewById(R.id.input_user_name); // Asegúrate de que este ID sea correcto
        inputApellidoUsuario = findViewById(R.id.input_user_lastname);
        inputCorreoUsuario = findViewById(R.id.input_user_email);
        inputTelefonoUsuario = findViewById(R.id.input_user_phone);
        inputContrasena = findViewById(R.id.input_user_password);
        btnGuardarUsuario = findViewById(R.id.btn_Registrar);

        btnGuardarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearUsuario();
            }
        });
    }

    public void crearUsuario() {
        String idUsuario = inputIdUsuario.getText().toString().trim();
        String nombre = inputNombreUsuario.getText().toString().trim();
        String apellido = inputApellidoUsuario.getText().toString().trim();
        String correo = inputCorreoUsuario.getText().toString().trim();
        String telefono = inputTelefonoUsuario.getText().toString().trim();
        String contrasena = inputContrasena.getText().toString().trim();

        // Verificar campos vacíos
        if (idUsuario.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || telefono.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference nuevoUsuario = reference.child(idUsuario);
        nuevoUsuario.child("Nombre").setValue(nombre);
        nuevoUsuario.child("Apellido").setValue(apellido);
        nuevoUsuario.child("correo").setValue(correo);
        nuevoUsuario.child("telefono").setValue(telefono);
        nuevoUsuario.child("contrasena").setValue(contrasena);

        registerAuth(correo, contrasena);
    }

    public void registerAuth(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Usuario Creado Exitosamente", Toast.LENGTH_LONG).show();
                        } else {
                            // Manejo de errores
                            Toast.makeText(Registro.this, "Fallo en la autenticación: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
