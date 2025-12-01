package com.noel.tema3;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView listView = findViewById(R.id.listaCalvos);

        String[] nombrescalvos = {"Homer Simpson", "Don Limpio", "Paco Sanz", "Antonio Lobato", "Amador Rivas", "Zinedine Zidane", "Jhonny Sins", "The Rock", "Jason Statham", "Toreto", "Tomas Huerta"};
        Integer[] imagenesCalvos = {R.drawable.homer, R.drawable.donlimpio, R.drawable.pacosanz, R.drawable.lobato, R.drawable.amador, R.drawable.zidane, R.drawable.sins, R.drawable.therock, R.drawable.statham, R.drawable.toreto, R.drawable.tomascalvo};

        ArrayList<HashMap<String, Object>> listaCalvos = new ArrayList<>();

        for(int i = 0; i < nombrescalvos.length; i++) {
            HashMap<String, Object> calvo = new HashMap<>();
            calvo.put("textViewNombreCalvo", nombrescalvos[i]);
            calvo.put("imagenViewCalvo", imagenesCalvos[i]);
            listaCalvos.add(calvo);
        }
        //Crear Adaptador
        String [] from = {"textViewNombreCalvo", "imagenViewCalvo"};
        int [] to = {R.id.textViewNombreCalvo, R.id.imagenViewCalvo};
        SimpleAdapter adapter = new SimpleAdapter(this, listaCalvos, R.layout.calvo_list, from, to);
        //Setear Adaptador
        listView.setAdapter(adapter);
    }
}