package com.noel.practicaexamen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ModificarDatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modificar_datos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editTextNuevoUsuario = findViewById(R.id.editTextNuevoUsuario);
        EditText editTextNuevaPass = findViewById(R.id.editTextNuevaPass);
        Button buttonModificarDatosya = findViewById(R.id.buttonModificarDatosya);
        Button buttonCancelaModificar = findViewById(R.id.buttonCancelaModificar);

        buttonModificarDatosya.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = getSharedPreferences("datos",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("usuario",editTextNuevoUsuario.getText().toString());
            editor.putString("contrasena",editTextNuevaPass.getText().toString());
            editor.apply();

            Intent data = getIntent();
            data.putExtra("Nuevo_usuario",editTextNuevoUsuario.getText().toString());
            data.putExtra("Nueva_contrasena",editTextNuevaPass.getText().toString());
            setResult(RESULT_OK,data);
            finish();
        });

        buttonCancelaModificar.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });

    }
}