package com.noel.practicaexamen;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActividadAlarma extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_actividad_alarma);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editTextNombreAlarma = findViewById(R.id.editTextTextNombreAlarma);
        EditText editTextHoraAlarma = findViewById(R.id.editTextTextHoraAlarma);
        EditText editTextMinutoAlarma = findViewById(R.id.editTextTextMinAlarma);
        Button buttonPonerAlarma = findViewById(R.id.buttonCrearAlarma);

        buttonPonerAlarma.setOnClickListener(v1 -> {
            String nombreAlarma = editTextNombreAlarma.getText().toString();
            String horaAlarma = editTextHoraAlarma.getText().toString();
            String minutoAlarma = editTextMinutoAlarma.getText().toString();

            if (nombreAlarma.isEmpty() || horaAlarma.isEmpty() || minutoAlarma.isEmpty()) {
                Toast.makeText(ActividadAlarma.this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            int hora = Integer.parseInt(horaAlarma);
            int minuto = Integer.parseInt(minutoAlarma);

            Intent intentAlarma = new Intent(AlarmClock.ACTION_SET_ALARM);
            intentAlarma.putExtra(AlarmClock.EXTRA_MESSAGE, nombreAlarma);
            intentAlarma.putExtra(AlarmClock.EXTRA_HOUR, hora);
            intentAlarma.putExtra(AlarmClock.EXTRA_MINUTES, minuto);
            if (intentAlarma.resolveActivity(getPackageManager()) != null) {
                startActivity(intentAlarma);
            } else {
                Toast.makeText(this, "No se puede establecer la alarma", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
