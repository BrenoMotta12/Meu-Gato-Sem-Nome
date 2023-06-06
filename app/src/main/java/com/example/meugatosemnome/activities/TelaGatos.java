package com.example.meugatosemnome.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

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
    Spinner spinner_filtro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_gatos);
        context = getApplicationContext();

        spinner_filtro = findViewById(R.id.spinner_filtro);

        recyclerViewGatos = findViewById(R.id.recyclerViewGatos);
        buscaNoBanco("");

        spinner_filtro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        buscaNoBanco("");
                        break;
                    case 1:
                        buscaNoBanco("WHERE doencas = 'Sim' ");
                        break;
                    case 2:
                        buscaNoBanco("WHERE doencas = 'Não' ");
                        break;
                    case 3:
                        buscaNoBanco("WHERE castrado = 'Sim' ");
                        break;
                    case 4:
                        buscaNoBanco("WHERE castrado = 'Não' ");
                        break;
                    case 5:
                        buscaNoBanco("WHERE filhotes > 0 ");
                        break;
                    case 6:
                        buscaNoBanco("WHERE filhotes = 0 ");
                        break;
                    case 7:
                        buscaNoBanco("WHERE idade < 1 ");
                        break;
                    case 8:
                        buscaNoBanco("WHERE idade >= 1 ");
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

    private void buscaNoBanco(String filtro) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(getApplicationContext());
        List<Gato> gatos = conexaoSQLite.buscaGato(filtro);
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
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerViewGatos.getContext(), LinearLayoutManager.VERTICAL);
        recyclerViewGatos.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onResume() {
        super.onResume();
        buscaNoBanco("");
    }
}