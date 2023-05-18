package com.example.meugatosemnome.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.meugatosemnome.R;
import com.example.meugatosemnome.adapters.GatoAdapter;
import com.example.meugatosemnome.conexoes.ConexaoSQLite;
import com.example.meugatosemnome.entidades.Gato;

import java.util.ArrayList;
import java.util.List;

public class TelaGatos extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Gato> itens;
    private GatoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_gatos);

        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(getApplicationContext());

        Gato g1 = new Gato(1, true, "Raiva", true, 2, 7);
        Gato g2 = new Gato(2, false, null, false, 0, 9);
        Gato g3 = new Gato(3, true, "Gripe", false, 1, 5);
        Gato g4 = new Gato(4, true, "Diabetes", true, 0, 3);
        Gato g5 = new Gato(5, false, null, false, 0, 2);
        Gato g6 = new Gato(6, true, "Alergia", true, 3, 8);
        Gato g7 = new Gato(7, false, null, false, 0, 6);
        Gato g8 = new Gato(8, true, "Obesidade", true, 2, 4);

        Gato.adicionarGato(g1, conexaoSQLite);
        Gato.adicionarGato(g2, conexaoSQLite);
        Gato.adicionarGato(g3, conexaoSQLite);
        Gato.adicionarGato(g4, conexaoSQLite);
        Gato.adicionarGato(g5, conexaoSQLite);
        Gato.adicionarGato(g6, conexaoSQLite);
        Gato.adicionarGato(g7, conexaoSQLite);
        Gato.adicionarGato(g8, conexaoSQLite);

        listView = findViewById(R.id.ListGatos);
        itens = (ArrayList<Gato>) Gato.buscaGato(conexaoSQLite);
        adapter = new GatoAdapter(TelaGatos.this, itens);
        listView.setAdapter(adapter);



    }
}