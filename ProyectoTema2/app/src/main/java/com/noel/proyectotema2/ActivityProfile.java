package com.noel.proyectotema2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;

public class ActivityProfile extends AppCompatActivity {
    Uri photoUri=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Buscar elemento vista
        EditText usernametext = findViewById(R.id.editTextUsuario);

        //recuperar datos de la primera actividad
        String usuario=getIntent().getStringExtra("username");
        String password=getIntent().getStringExtra("password");

        //setear dato en editText
        usernametext.setText(usuario);
        Log.d("Pass", password);

        Button botonNavegador = findViewById(R.id.buttonNavegador);
        botonNavegador.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://unendo.es/"));


            startActivity(intent);

        });
        Button botonCrearAlarma =findViewById(R.id.buttonAlarma);
        botonCrearAlarma.setOnClickListener(view -> {
            Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                    .putExtra(AlarmClock.EXTRA_MESSAGE, "Hora del recreo")
                    .putExtra(AlarmClock.EXTRA_HOUR, 11)
                    .putExtra(AlarmClock.EXTRA_MINUTES,00);

            startActivity(intent);


        });

        ImageView imagenUser = findViewById(R.id.imageViewUser);
        //Crear intent result launcher para recibir foto capturada
        ActivityResultLauncher<Intent> launcherFoto = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && photoUri != null) {
                        //Forzar Recarga de la imagen para evitar cache
                        imagenUser.setImageURI(null);
                        //Dato recibido correctamente
                        imagenUser.setImageURI(photoUri);
                        Toast.makeText(this, "Foto capturada", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(this, "Operación cancelada", Toast.LENGTH_SHORT).show();
                    }


                });


        imagenUser.setOnClickListener(view -> {
            File photoFile = new File(getExternalFilesDir(null), "photo.jpg");

            //Usa FileProvider para crear la URI
            photoUri = FileProvider.getUriForFile(this,
                    getPackageName() + ".fileprovider", photoFile);

            Intent misco = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            misco.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            misco.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            launcherFoto.launch(misco);


        });

    }
}