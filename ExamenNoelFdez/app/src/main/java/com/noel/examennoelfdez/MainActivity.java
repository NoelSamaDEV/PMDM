package com.noel.examennoelfdez;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre;
    private RadioGroup rgPosiciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.editTextTextNombreMain);
        rgPosiciones = findViewById(R.id.radioGroupPosicionesMain);
        Button btnCrearJugador = findViewById(R.id.buttonCrearJugador);
        Button btnCargarDatos = findViewById(R.id.buttonCargarDatos);

        // Este 'Launcher' se encarga de recibir el resultado de CargarJugador
        ActivityResultLauncher<Intent> cargarDatosLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Intent data = result.getData();
                            String nombre = data.getStringExtra("nombre");
                            String posicion = data.getStringExtra("posicion");


                            etNombre.setText(nombre);


                            for (int i = 0; i < rgPosiciones.getChildCount(); i++) {
                                RadioButton radioButton = (RadioButton) rgPosiciones.getChildAt(i);
                                if (radioButton.getText().toString().equalsIgnoreCase(posicion)) {
                                    radioButton.setChecked(true);
                                    break;
                                }
                            }
                        }
                    }
                });

        btnCrearJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                int selectedRadioButtonId = rgPosiciones.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                String posicion = selectedRadioButton.getText().toString();

                Intent intent = new Intent(MainActivity.this, ResumenJugador.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("posicion", posicion);
                startActivity(intent);
            }
        });

        btnCargarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CargarJugador.class);
                cargarDatosLauncher.launch(intent);
            }
        });
    }
}
