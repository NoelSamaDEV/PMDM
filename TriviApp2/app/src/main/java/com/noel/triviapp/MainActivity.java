package com.noel.triviapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    private TextView tvPregunta, tvScore;
    private Button btn1, btn2, btn3, btn4;

    private ArrayList<Pregunta> preguntas;
    private int indice = 0;
    private int score = 0;
    private final int totalPreguntas = 5;

    private Pregunta actual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPregunta = findViewById(R.id.tvPregunta);
        tvScore = findViewById(R.id.tvScore);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);

        cargarPreguntas();
        mostrarPregunta();

        View.OnClickListener listener = v -> {
            Button elegido = (Button) v;

            if (elegido.getText().equals(actual.getCorrecta())) {
                score++;
                tvScore.setText("Score " + score + "/" + totalPreguntas);
            }

            indice++;

            if (indice < preguntas.size()) {
                mostrarPregunta();
            } else {
                verificarResultado();
            }
        };

        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
        btn4.setOnClickListener(listener);
    }

    private void verificarResultado() {
        if (score >= 3) {
            new AlertDialog.Builder(this)
                    .setTitle("🎉 ¡Enhorabuena!")
                    .setMessage("Has ganado el trivial con " + score + " puntos.")
                    .setPositiveButton("Aceptar", (d, w) -> reiniciarTodo())
                    .show();
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("❌ Has conseguido menos de 3 puntos")
                    .setMessage("Se reinician las preguntas.\nPuntuación final: " + score)
                    .setPositiveButton("Reintentar", (d, w) -> reiniciarTodo())
                    .show();
        }
    }

    private void reiniciarTodo() {
        score = 0;
        indice = 0;
        tvScore.setText("Score 0/5");
        cargarPreguntas();
        mostrarPregunta();

        btn1.setVisibility(View.VISIBLE);
        btn2.setVisibility(View.VISIBLE);
        btn3.setVisibility(View.VISIBLE);
        btn4.setVisibility(View.VISIBLE);
    }

    private void cargarPreguntas() {
        preguntas = new ArrayList<>();

        preguntas.add(new Pregunta("¿Capital de Francia?", "París",
                new String[]{"París", "Roma", "Madrid", "Berlín"}));

        preguntas.add(new Pregunta("¿Cuánto es 5 × 5?", "25",
                new String[]{"25", "10", "30", "15"}));

        preguntas.add(new Pregunta("¿Animal más rápido del mundo?", "Guepardo",
                new String[]{"Guepardo", "Tiburón", "León", "Halcón peregrino"}));

        preguntas.add(new Pregunta("¿Cuántos días tiene un año bisiesto?", "366",
                new String[]{"366", "365", "364", "360"}));

        preguntas.add(new Pregunta("¿Cuál es el metal más ligero?", "Litio",
                new String[]{"Litio", "Hierro", "Plomo", "Aluminio"}));
    }

    private void mostrarPregunta() {
        actual = preguntas.get(indice);
        tvPregunta.setText(actual.getTexto());

        ArrayList<String> opciones = new ArrayList<>(Arrays.asList(actual.getRespuestas()));
        Collections.shuffle(opciones);

        btn1.setText(opciones.get(0));
        btn2.setText(opciones.get(1));
        btn3.setText(opciones.get(2));
        btn4.setText(opciones.get(3));
    }
}
