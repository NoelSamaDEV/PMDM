package com.noel.ejemploandroid;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Alarma extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alarma);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button buttonPonerAlarma = findViewById(R.id.buttonPonerAlarma);
        EditText editTextNombreAlarma = findViewById(R.id.editTextNombreAlarma);
        EditText editTextHoraAlarma = findViewById(R.id.editTextHoraAlarma);
        EditText editTextMinutosAlarma = findViewById(R.id.editTextMinAlarma);

        Intent alarma = new Intent(AlarmClock.ACTION_SET_ALARM);
        alarma.putExtra(AlarmClock.EXTRA_MESSAGE, editTextNombreAlarma.getText().toString());
        alarma.putExtra(AlarmClock.EXTRA_HOUR, Integer.parseInt(editTextHoraAlarma.getText().toString()));
        alarma.putExtra(AlarmClock.EXTRA_MINUTES, Integer.parseInt(editTextMinutosAlarma.getText().toString()));

        startActivity(alarma);
    }
}