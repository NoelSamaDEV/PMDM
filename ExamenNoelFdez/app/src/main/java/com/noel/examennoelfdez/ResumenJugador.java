package com.noel.examennoelfdez;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResumenJugador extends AppCompatActivity {

    private TextView tvBienvenido, tvPosicion;
    private Button btnNavegador, btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_jugador);

        tvBienvenido = findViewById(R.id.textViewBienvenido);
        tvPosicion = findViewById(R.id.textViewPosicion);
        btnNavegador = findViewById(R.id.buttonNavegador);
        btnGuardar = findViewById(R.id.buttonGuardar);

        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String posicion = intent.getStringExtra("posicion");

        tvBienvenido.setText("Bienvenido: " + nombre);
        tvPosicion.setText("Posicion: " + posicion);

        btnNavegador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://espndeportes.espn.com/basquetbol/nba/estadisticas"));
                startActivity(browserIntent);
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("JugadorPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nombre", nombre);
                editor.putString("posicion", posicion);
                editor.apply();
                Toast.makeText(ResumenJugador.this, "Guardado correctamente", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
