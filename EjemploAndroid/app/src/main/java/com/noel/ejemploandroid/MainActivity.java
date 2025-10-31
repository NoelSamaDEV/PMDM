package com.noel.ejemploandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    String USER="admin";
    String PASS="admin";


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

        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonModificar = findViewById(R.id.buttonModify);
        EditText editTextNombre = findViewById(R.id.editTextNombre);
        EditText editTextPass = findViewById(R.id.editTextPass);

        //Result Launcher cuando se modifica el usuario y contraseña desde otra actividad
        ActivityResultLauncher<Intent> activityResultModificar = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intentLogin = result.getData();
                        String nuevoNombre = intentLogin.getStringExtra("nombre");
                        String nuevaPass = intentLogin.getStringExtra("pass");
                        USER = nuevoNombre;
                        PASS = nuevaPass;
                    }
                }
        );
        buttonModificar.setOnClickListener(v -> {
            Intent intentModificar = new Intent(MainActivity.this, ModificarCredenciales.class);
            activityResultModificar.launch(intentModificar);


        });

        buttonLogin.setOnClickListener(v -> {
            String nombre = editTextNombre.getText().toString();
            String pass = editTextPass.getText().toString();

            if (nombre.equals(USER) && pass.equals(PASS)) {
                Intent intent = new Intent(MainActivity.this, LoginCorrecto.class);
                intent.putExtra("username", nombre);
                intent.putExtra("password", pass);
                startActivity(intent);
            }
            else{
                Toast.makeText(MainActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        });



        }
    }

