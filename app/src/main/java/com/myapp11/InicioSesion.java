package com.myapp11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class InicioSesion extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();

    Button btnInicioSesion;
    Button btnVolverAlHome2;
    EditText inputCorreoSession;
    EditText inputPasswordSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio_sesion);

        btnInicioSesion = findViewById(R.id.btn_Iniciar_sesion);
        btnVolverAlHome2 = findViewById(R.id.btn_Volver_Iniciosesion);
        inputCorreoSession = findViewById(R.id.input_email_iniciosesion);
        inputPasswordSesion = findViewById(R.id.input_password);

        btnInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String correo = inputCorreoSession.getText().toString();
                String contrasena = inputPasswordSesion.getText().toString();

                iniciarSesion(correo,contrasena);
            }
        });
    }
    public void irDashboardDesdeInicioSesion(){
        Intent intent = new Intent(this, DasBoardt.class);
        startActivity(intent);
    }
    public void iniciarSesion(String email, String password){

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            irDashboardDesdeInicioSesion();
                            Toast.makeText(InicioSesion.this, "Has iniciado session", Toast.LENGTH_SHORT).show();
                            /*
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);*/
                        } else {

                            Toast.makeText(InicioSesion.this, "Valide sus credenciales", Toast.LENGTH_SHORT).show();
                           /* // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);*/
                        }
                    }
                });

    }
}