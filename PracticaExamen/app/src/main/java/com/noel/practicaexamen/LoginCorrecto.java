package com.noel.practicaexamen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginCorrecto extends AppCompatActivity {

    private String usuario;
    private String contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_correcto);

        TextView textViewBienvenida = findViewById(R.id.textViewBienvenida);
        Button buttonModificarDatos = findViewById(R.id.buttonModificarDatos);
        Button buttonActividadAlarma = findViewById(R.id.buttonAlarma2);
        Button buttonRadioButtons = findViewById(R.id.buttonRadioButtons);



        // 1️⃣ Recibir usuario y contraseña que venían del login
        Intent intentEntrada = getIntent();
        usuario = intentEntrada.getStringExtra("usuario");
        contrasena = intentEntrada.getStringExtra("contrasena");

        textViewBienvenida.setText("Bienvenido " + usuario);

        // 2️⃣ Preparar launcher para recibir el resultado de ModificarDatos
        ActivityResultLauncher<Intent> activityResultLauncher =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            String nuevoUsuario = data.getStringExtra("Nuevo_usuario");
                            String nuevoPass    = data.getStringExtra("Nueva_contrasena");

                            if (nuevoUsuario != null) usuario = nuevoUsuario;
                            if (nuevoPass != null)    contrasena = nuevoPass;

                            textViewBienvenida.setText("Bienvenido " + usuario);
                        }
                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
                    }
                });

        // 3️⃣ Abrir ModificarDatos al pulsar el botón
        buttonModificarDatos.setOnClickListener(v -> {
            Intent intentModificar = new Intent(this, ModificarDatos.class);
            // Opcional: pasar usuario/contraseña actuales para pre-rellenar los campos
            intentModificar.putExtra("usuario", usuario);
            intentModificar.putExtra("contrasena", contrasena);
            activityResultLauncher.launch(intentModificar);
        });

        // 4️⃣ Abrir ActividadAlarma al pulsar el botón
        buttonActividadAlarma.setOnClickListener(v -> {
            Intent intentActividadAlarma = new Intent(this, ActividadAlarma.class);
            startActivity(intentActividadAlarma);
        });

        // 5️⃣ Abrir RadioButtons al pulsar el botón
        buttonRadioButtons.setOnClickListener(v -> {
            Intent intentRadioButtons = new Intent(this, RadioButtons.class);
            startActivity(intentRadioButtons);
        });
        }
    }

