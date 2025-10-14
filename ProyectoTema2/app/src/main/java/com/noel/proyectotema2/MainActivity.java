package com.noel.proyectotema2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    final String USER="admin";
    final String PASS="12345";

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

        Button botonLogin = findViewById(R.id.buttonLogin);
        EditText editTextUsuario = findViewById(R.id.editTextUsuario);
        EditText editTextPass = findViewById(R.id.editTextPass);

        //1 Aqui se utiliza una lambda
        botonLogin.setOnClickListener(v -> {

        //MUESTRA MENSAJE
        //Toast toast = Toast.makeText(this, "Boton clicado", Toast.LENGTH_SHORT);
        //toast.show();
        //String msg=editTextUsuario.getText()+" : "+editTextPass.getText();
        //
        //  Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();


        //PASAR DE UNA ACTIVIDAD A OTRA

            String user=editTextUsuario.getText().toString();
            String pass=editTextPass.getText().toString();

            if(user.equals(USER) && pass.equals(PASS)){
                Intent intent = new Intent(this, ActivityProfile.class);


                //pasar datos a la actividad perfil
                intent.putExtra("username",user);
                intent.putExtra("password",pass);

                //Poner delay de 2 segundos
                // try {
                //  Thread.sleep(2000);
                // }catch (InterruptedException e){
                //  throw new RuntimeException(e);
                // }

                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }


      });



//        //2. Aqui se sobreescirbe listener
//        botonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("Prueba", "Boton clicado");
//            }
//        });
//
//        //3. Creando listener
//        View.OnClickListener listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("Prueba", "Boton clicado");
//            }
//        };
//        botonLogin.setOnClickListener(listener);



//        //Ejemplo Lambda
//        ArrayList<String> paises=new ArrayList<String>(Arrays.asList
//                ("España","Francia","Alemania","Italia","Portugal"));
//        for (String pais: paises) {
//            Log.d("Prueba",pais);
//        }
//
//        paises.forEach(pais-> Log.d("Prueba",pais));
    }

//    //4. Otra manera de hacerlo
//    public void botonClicado(View view) {
//        Log.d("Prueba", "Boton clicado");
//    }

}