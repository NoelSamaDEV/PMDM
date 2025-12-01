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

public class MercadonaFragment extends Fragment {

    private ArrayList<String> listaProductos;
    private ArrayAdapter<String> adaptador;

    private static final String PREFS_NAME = "listas_super";
    private static final String KEY_LISTA = "mercadona";

    public MercadonaFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mercadona, container, false);

        listaProductos = cargarLista();

        ListView lv = view.findViewById(R.id.listaProductos);
        EditText txt = view.findViewById(R.id.txtProductoNuevo);
        Button btn = view.findViewById(R.id.btnAgregar);

        adaptador = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listaProductos);
        lv.setAdapter(adaptador);

        btn.setOnClickListener(v -> {
            String producto = txt.getText().toString().trim();
            if (!producto.isEmpty()) {
                listaProductos.add(producto);
                guardarLista();
                adaptador.notifyDataSetChanged();
                txt.setText("");
            }
        });

        lv.setOnItemLongClickListener((parent, itemView, position, id) -> {
            listaProductos.remove(position);
            guardarLista();
            adaptador.notifyDataSetChanged();
            return true;
        });

        return view;
    }

    private void guardarLista() {
        SharedPreferences prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        StringBuilder sb = new StringBuilder();
        for (String p : listaProductos) sb.append(p).append(";");

        editor.putString(KEY_LISTA, sb.toString());
        editor.apply();
    }

    private ArrayList<String> cargarLista() {
        SharedPreferences prefs = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String datos = prefs.getString(KEY_LISTA, "");

        if (datos.equals("")) return new ArrayList<>();

        return new ArrayList<>(Arrays.asList(datos.split(";")));
    }
}
