package com.noel.ejemploandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ModificarCredenciales extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modificar_credenciales);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button buttonCancelar = findViewById(R.id.buttonCancelar);
        Button buttonGuardar = findViewById(R.id.buttonGuardar);
        EditText editTextNombre = findViewById(R.id.editTextNuevoNombre);
        EditText editTextPass = findViewById(R.id.editTextNuevaPass);


        buttonGuardar.setOnClickListener(v -> {
            Intent IntentLogin = getIntent();

            IntentLogin.putExtra("nombre", editTextNombre.getText().toString());
            IntentLogin.putExtra("pass", editTextPass.getText().toString());

            //Setear el resultado a OK
            setResult(RESULT_OK, IntentLogin);

            //Terminar la actividad
            finish();
        });

        buttonCancelar.setOnClickListener(v -> {
            setResult(Activity.RESULT_CANCELED);
            finish();
        });

        }
    }

