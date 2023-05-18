package com.example.meugatosemnome.activities;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.meugatosemnome.R;
import com.example.meugatosemnome.conexoes.ConexaoSQLite;

public class MainActivity extends AppCompatActivity {

    private ConexaoSQLite conexaoSQLite;
    private Button buttonAnimais;
    private Button buttonDoacoes;
    private Button buttonRelatorios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conexaoSQLite = new ConexaoSQLite(this);
        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();

        // BOTÃO QUE LEVA PARA A TELA DOS GATOS
        buttonAnimais = findViewById(R.id.buttonAnimais);
        buttonAnimais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TelaGatos.class);
                startActivity(intent);
            }
        });

        // BOTÃO QUE LEVA PARA A TELA DE DOAÇÕES
        buttonDoacoes = findViewById(R.id.buttonDoacoes);
        buttonDoacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TelaDoacoes.class);
                startActivity(intent);
            }
        });

        //BOTÃO QUE LEVA PARA A TELA DE RELATÓRIOS
        buttonRelatorios = findViewById(R.id.buttonRelatorios);
        buttonRelatorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TelaRelatorios.class);
                startActivity(intent);
            }
        });
        db.close();
    }
}

