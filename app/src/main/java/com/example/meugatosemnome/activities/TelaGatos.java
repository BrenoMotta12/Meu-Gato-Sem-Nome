package com.example.meugatosemnome.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.meugatosemnome.R;
import com.example.meugatosemnome.adapters.GatoAdapter;
import com.example.meugatosemnome.conexoes.ConexaoSQLite;
import com.example.meugatosemnome.entidades.Gato;

import java.util.ArrayList;

public class TelaGatos extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Gato> itens;
    private GatoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_gatos);

        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();


        listView = findViewById(R.id.ListGatos);
        itens = Gato.buscaGato(conexaoSQLite);
        adapter = new GatoAdapter(TelaGatos.this, itens);
        listView.setAdapter(adapter);

        conexaoSQLite.desconectar();


    }
}