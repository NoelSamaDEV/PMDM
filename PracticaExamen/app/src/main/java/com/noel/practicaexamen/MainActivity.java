package com.noel.practicaexamen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1️⃣ Abrimos las SharedPreferences donde guardamos usuario/contraseña
        SharedPreferences prefs = getSharedPreferences("datos", MODE_PRIVATE);

        // 2️⃣ Solo la PRIMERA vez que se crea MainActivity en este proceso:
        //     inicializamos las credenciales a admin / admin.

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("usuario", "admin");
            editor.putString("contrasena", "admin");
            editor.apply();


        EditText editTextUsuario = findViewById(R.id.editTextUsuario);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(v -> {
            String usuarioText = editTextUsuario.getText().toString();
            String contrasenaText = editTextPassword.getText().toString();

            // 3️⃣ MUY IMPORTANTE: leer SIEMPRE las credenciales actuales
            //     por si se han cambiado en ModificarDatos.
            SharedPreferences prefsClick = getSharedPreferences("datos", MODE_PRIVATE);
            String usuarioGuardado = prefsClick.getString("usuario", "admin");
            String contrasenaGuardada = prefsClick.getString("contrasena", "admin");

            // 4️⃣ Comprobamos contra lo que haya ahora mismo en SharedPreferences
            if (usuarioText.equals(usuarioGuardado) && contrasenaText.equals(contrasenaGuardada)) {
                Intent intentLogin = new Intent(this, LoginCorrecto.class);
                intentLogin.putExtra("usuario", usuarioText);
                intentLogin.putExtra("contrasena", contrasenaText);
                startActivity(intentLogin);
            } else {
                Toast.makeText(this, "Usuario o contrasena incorrectos", Toast.LENGTH_SHORT).show();
            }
        });


    }
}