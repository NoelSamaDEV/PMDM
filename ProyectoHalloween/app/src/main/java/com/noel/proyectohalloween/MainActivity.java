package com.noel.proyectohalloween;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

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


        ImageView calabaza = findViewById(R.id.imageView);

        Glide.with(this).load("https://assets.hvmag.com/2023/09/pumpkin-world-AdobeStock_626050040.jpg")
                .into(calabaza);


        Button btnComer = findViewById(R.id.buttonCagalera);
        btnComer.setOnClickListener(v -> {
            Intent i = new Intent(this, RadioButtonActivity.class);
            startActivity(i);
        });



        Button btnGuardar = findViewById(R.id.buttonGuardar);
        EditText texto = findViewById(R.id.editTextGuardar);

        mostrarDatoGuardado();

        btnGuardar.setOnClickListener(v -> {
            //Guardar datos con SharedPreferences

            //Crear objeto SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("misDatos", MODE_PRIVATE);

            //Obtener editor de SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();

            //Añadir nuevo dato de tipo cadena
            editor.putString("TextoImportante", texto.getText().toString());

            //Guardar datos
            editor.apply(); //Guarda el dato de manera asincrona
            //editor.commit(); //Guarda el dato de manera sincrona
        });

    }

    private void mostrarDatoGuardado() {

        String msg;

        //Recuperar el dato guardado con SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("misDatos", MODE_PRIVATE);

        //Recuperar el dato guardado
        msg = sharedPreferences.getString("TextoImportante", "No hay datos guardados");


        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }




}