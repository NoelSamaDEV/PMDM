package com.noel.comprapplist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;

public class AlimerkaFragment extends Fragment {

    private ArrayList<String> listaProductos;
    private ArrayAdapter<String> adaptador;

    // Nombre del fichero de preferencias y clave
    private static final String PREFS_NAME = "listas_supermercado";
    private static final String KEY_ALIMERKA = "lista_alimerka";

    public AlimerkaFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_alimerka, container, false);

        // Cargar lista desde SharedPreferences
        listaProductos = cargarLista();

        ListView lv = view.findViewById(R.id.listaProductos);
        EditText txt = view.findViewById(R.id.txtProductoNuevo);
        Button btn = view.findViewById(R.id.btnAgregar);

        adaptador = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                listaProductos
        );

        lv.setAdapter(adaptador);

        // AÑADIR PRODUCTO
        btn.setOnClickListener(v -> {
            String producto = txt.getText().toString().trim();
            if (!producto.isEmpty()) {
                listaProductos.add(producto);
                guardarLista();
                adaptador.notifyDataSetChanged();
                txt.setText("");
            }
        });

        // BORRAR PRODUCTO CON PULSACIÓN LARGA
        lv.setOnItemLongClickListener((parent, itemView, position, id) -> {
            listaProductos.remove(position);
            guardarLista();
            adaptador.notifyDataSetChanged();
            return true;
        });

        return view;
    }

    // -------------------------
    //      GUARDAR LISTA
    // -------------------------
    private void guardarLista() {
        SharedPreferences prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // Convertir lista en un único String separado por ;
        StringBuilder sb = new StringBuilder();
        for (String producto : listaProductos) {
            sb.append(producto).append(";");
        }

        editor.putString(KEY_ALIMERKA, sb.toString());
        editor.apply();
    }

    // -------------------------
    //      CARGAR LISTA
    // -------------------------
    private ArrayList<String> cargarLista() {
        SharedPreferences prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String datos = prefs.getString(KEY_ALIMERKA, "");

        if (datos.equals("")) {
            return new ArrayList<>();
        }

        // Dividir por ;
        String[] array = datos.split(";");
        return new ArrayList<>(Arrays.asList(array));
    }
}

