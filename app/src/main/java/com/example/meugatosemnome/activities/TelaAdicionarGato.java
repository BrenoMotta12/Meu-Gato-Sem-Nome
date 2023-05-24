package com.example.meugatosemnome.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.meugatosemnome.R;
import com.example.meugatosemnome.conexoes.ConexaoSQLite;
import com.example.meugatosemnome.entidades.Gato;


public class TelaAdicionarGato extends AppCompatActivity {

    private ImageButton buttonVoltarTelaGato;
    private Switch switchDoenca;
    private EditText editDescricaoDoenca;
    private Switch switchCastrado;
    private EditText editFilhotes;
    private EditText editIdade;
    private Button buttonSalvarGato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_adicionar_gato);

        buttonVoltarTelaGato = findViewById(R.id.buttonVoltarTelaGato);
        switchDoenca = findViewById(R.id.switchDoenca);
        editDescricaoDoenca = findViewById(R.id.editDescricaoDoenca);
        switchCastrado = findViewById(R.id.switchCastrado);
        editFilhotes = findViewById(R.id.editFilhotes);
        editIdade = findViewById(R.id.editIdade);
        buttonSalvarGato = findViewById(R.id.buttonSalvarGato);

        buttonVoltarTelaGato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaAdicionarGato.this, TelaGatos.class);
                startActivity(intent);
            }
        });

        buttonSalvarGato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean doenca;
                if (switchDoenca.isChecked()) {
                    doenca = true;
                } else {
                    doenca = false;
                }
                boolean castrado;
                if (switchDoenca.isChecked()) {
                    castrado = true;
                } else {
                    castrado = false;
                }
                if (!(editFilhotes.getText().toString().equals("") || editIdade.getText().toString().equals(""))){

                    try {
                        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(getApplicationContext());
                        Gato gato = new Gato();
                        gato.setDoencas(doenca);
                        gato.setDescricaoDoencas((editDescricaoDoenca.getText().toString()));
                        gato.setCastrado(castrado);
                        gato.setFilhotes(Integer.valueOf(editFilhotes.getText().toString()));
                        gato.setIdade(Integer.valueOf(editIdade.getText().toString()));
                        Gato.adicionarGato(gato,conexaoSQLite);
                    } catch (SQLException e) {
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    } finally {
                        Intent intent = new Intent(TelaAdicionarGato.this, TelaGatos.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                }




            }
        });

    }
}