package com.example.meugatosemnome.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.meugatosemnome.R;
import com.example.meugatosemnome.adapters.RecyclerViewAdapter;
import com.example.meugatosemnome.conexoes.ConexaoSQLite;
import com.example.meugatosemnome.entidades.Gato;

import java.util.ArrayList;
import java.util.List;

public class TelaGatos extends AppCompatActivity {

    RecyclerView recyclerViewGatos;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    RecyclerViewAdapter recyclerViewAdapter;
    Context context;
    private ImageButton buttonHome;
    private Button buttonAddGato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_gatos);
        context = getApplicationContext();

        recyclerViewGatos = findViewById(R.id.recyclerViewGatos);
        buscaNoBanco();


        // BOTÃO QUE LEVA PARA A TELA DE ADIÇÃO DE GATOS
        buttonAddGato = findViewById(R.id.buttonAddGato);
        buttonAddGato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaGatos.this, TelaAdicionarGato.class);
                startActivity(intent);
            }
        });


        // BOTÃO QUE VOLTA PRA TELA INICIAL
        buttonHome = findViewById(R.id.buttonHome);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaGatos.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void buscaNoBanco() {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(getApplicationContext());
        List<Gato> gatos = conexaoSQLite.buscaGato();

        List<String> ids = new ArrayList<String>();
        List<String> doencas = new ArrayList<String>();
        List<String> descricoes = new ArrayList<String>();
        List<String> castrados = new ArrayList<String>();
        List<String> filhotes = new ArrayList<String>();
        List<String> idades = new ArrayList<String>();
        List<String> fotos = new ArrayList<String>();

        for (Gato gatoBuscado : gatos) {
            ids.add(String.valueOf(gatoBuscado.getId()));
            doencas.add(String.valueOf(gatoBuscado.getDoencas()));
            descricoes.add(gatoBuscado.getDescricaoDoencas());
            castrados.add(String.valueOf(gatoBuscado.getCastrado()));
            filhotes.add(String.valueOf(gatoBuscado.getFilhotes()));
            idades.add(String.valueOf(gatoBuscado.getIdade()));
            fotos.add(gatoBuscado.getImagemPath());
        }

        String[] dados_ids = new String[] {};
        String[] dados_doencas = new String[] {};
        String[] dados_descricoes = new String[] {};
        String[] dados_castrados = new String[] {};
        String[] dados_filhotes = new String[] {};
        String[] dados_idades = new String[] {};
        String[] dados_fotos = new String[] {};

        dados_ids = ids.toArray(new String[0]);
        dados_doencas = doencas.toArray(new String[0]);
        dados_descricoes = descricoes.toArray(new String[0]);
        dados_castrados = castrados.toArray(new String[0]);
        dados_filhotes = filhotes.toArray(new String[0]);
        dados_idades = idades.toArray(new String[0]);
        dados_fotos = fotos.toArray(new String[0]);

        recyclerViewLayoutManager = new LinearLayoutManager(context);
        recyclerViewGatos.setLayoutManager(recyclerViewLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(context, dados_ids, dados_doencas,
                dados_descricoes, dados_castrados, dados_filhotes, dados_idades, dados_fotos);
        conexaoSQLite.close();
        recyclerViewGatos.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        buscaNoBanco();
    }
}