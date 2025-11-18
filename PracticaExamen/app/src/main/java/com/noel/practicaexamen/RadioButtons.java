package com.noel.practicaexamen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RadioButtons extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_radio_buttons);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RadioGroup radioGroupColorGato = findViewById(R.id.radioGroupColorGatos);
        RadioGroup radioGroupRazaGato = findViewById(R.id.radioGroupRazaGatos);
        Button buttonEnviarGato = findViewById(R.id.buttonEnviarGato);

        SharedPreferences prefs = getSharedPreferences("gato_prefs", MODE_PRIVATE);

        int opcionColorGuardada = prefs.getInt("opcionColor", 0);
        int opcionRazaGuardada = prefs.getInt("opcionRaza", 0);

        if (opcionColorGuardada != 0) {
            radioGroupColorGato.check(opcionColorGuardada);
        }
        if (opcionRazaGuardada != 0) {
            radioGroupRazaGato.check(opcionRazaGuardada);
        }

        radioGroupColorGato.setOnCheckedChangeListener((group, checkedId) -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("opcionColor", checkedId);
            editor.apply();
            if (checkedId == R.id.radioButtonGatoNaranja) {
                Toast.makeText(this, "Gato naranja", Toast.LENGTH_SHORT).show();
            } else if (checkedId == R.id.radioButtonGatoNegro) {
                Toast.makeText(this, "Gato negro", Toast.LENGTH_SHORT).show();
            } else if (checkedId == R.id.radioButtonGatoBlanco) {
                Toast.makeText(this, "Gato blanco", Toast.LENGTH_SHORT).show();
            }
        });

        radioGroupRazaGato.setOnCheckedChangeListener((group, checkedId) -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("opcionRaza", checkedId);
            editor.apply();
            if (checkedId == R.id.radioButtonGatoSiames) {
                Toast.makeText(this, "Gato siames", Toast.LENGTH_SHORT).show();
            } else if (checkedId == R.id.radioButtonGatoPersa) {
                Toast.makeText(this, "Gato persa", Toast.LENGTH_SHORT).show();
            } else if (checkedId == R.id.radioButtonGatoSomali) {
                Toast.makeText(this, "Gato somali", Toast.LENGTH_SHORT).show();
            }
        });

        buttonEnviarGato.setOnClickListener(v -> {
            int opcionColorSeleccionada = radioGroupColorGato.getCheckedRadioButtonId();
            int opcionRazaSeleccionada = radioGroupRazaGato.getCheckedRadioButtonId();

            if (opcionColorSeleccionada == -1 || opcionRazaSeleccionada == -1) {
                Toast.makeText(this, "Selecciona una opción de cada grupo", Toast.LENGTH_SHORT).show();
                return;
            }

            android.widget.RadioButton radioButtonColor = findViewById(opcionColorSeleccionada);
            android.widget.RadioButton radioButtonRaza = findViewById(opcionRazaSeleccionada);
            String color = radioButtonColor.getText().toString();
            String raza = radioButtonRaza.getText().toString();

            Intent intent = new Intent(this, ActividadResultadoRadioButtons.class);

            intent.putExtra("color", color);
            intent.putExtra("raza", raza);

            startActivity(intent);
        });
    }
}