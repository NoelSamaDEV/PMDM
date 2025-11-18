package com.noel.examennoelfdez;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CargarJugador extends AppCompatActivity {

    private TextView tvNombre, tvPosicion;
    private Button btnFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_jugador);

        tvNombre = findViewById(R.id.textViewNombreCargar);
        tvPosicion = findViewById(R.id.textView2);
        btnFinalizar = findViewById(R.id.buttonFinalizarActividad);

        SharedPreferences sharedPreferences = getSharedPreferences("JugadorPrefs", MODE_PRIVATE);
        String nombre = sharedPreferences.getString("nombre", "");
        String posicion = sharedPreferences.getString("posicion", "");

        tvNombre.setText("Nombre: " + nombre);
        tvPosicion.setText("Posicion: " + posicion);

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("nombre", nombre);
                resultIntent.putExtra("posicion", posicion);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
