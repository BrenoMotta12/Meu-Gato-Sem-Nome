package com.example.meugatosemnome.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meugatosemnome.R;
import com.example.meugatosemnome.conexoes.ConexaoSQLite;

public class TelaOpcaoGato extends AppCompatActivity {

    ImageButton activity_opcao_gato_buttonVoltarTelaGato;
    Button activity_opcao_gato_buttonAtualizarGato, activity_opcao_gato_buttonExcluirGato;
    ImageView activity_opcao_gato_icone;
    EditText activity_opcao_gato_descricao_recebida_EditText, activity_opcao_gato_filhotes_recebido_EditText,
            activity_opcao_gato_idade_recebida_EditText;
    TextView activity_opcao_gato_id_recebido_TextView;
    Spinner activity_opcao_gato_doenca_recebida_Spinner, activity_opcao_gato_castrado_recebido_Spinner;
    String idAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_opcao_gato);

        // LIGAÇÃO BACK-FRONT DOS SPINNERS E TEXTS
        activity_opcao_gato_icone = findViewById(R.id.activity_opcao_gato_icone);
        activity_opcao_gato_id_recebido_TextView = findViewById(R.id.activity_opcao_gato_id_recebido_TextView);
        activity_opcao_gato_doenca_recebida_Spinner = findViewById(R.id.activity_opcao_gato_doenca_recebida_Spinner);
        activity_opcao_gato_descricao_recebida_EditText = findViewById(R.id.activity_opcao_gato_descricao_recebida_EditText);
        activity_opcao_gato_castrado_recebido_Spinner = findViewById(R.id.activity_opcao_gato_castrado_recebido_Spinner);
        activity_opcao_gato_filhotes_recebido_EditText = findViewById(R.id.activity_opcao_gato_filhotes_recebido_EditText);
        activity_opcao_gato_idade_recebida_EditText = findViewById(R.id.activity_opcao_gato_idade_recebida_EditText);

        // LIGAÇÃO BACK-FRONT DOS BOTÕES
        activity_opcao_gato_buttonVoltarTelaGato = findViewById(R.id.buttonVoltarTelaGato);
        activity_opcao_gato_buttonAtualizarGato = findViewById(R.id.activity_opcao_gato_buttonAtualizarGato);
        activity_opcao_gato_buttonExcluirGato = findViewById(R.id.activity_opcao_gato_buttonExcluirGato);


        Intent intent = getIntent();

        idAtual = intent.getStringExtra("id");

        // CONVERSOR DA FOTO
        if (intent.getStringExtra("foto") != null) {
            byte[] imagemEmBytes = Base64.decode(intent.getStringExtra("foto"), Base64.DEFAULT);
            Bitmap imagemDecodificada = BitmapFactory.decodeByteArray(imagemEmBytes, 0, imagemEmBytes.length);
            activity_opcao_gato_icone.setImageBitmap(imagemDecodificada);
        } else {
            activity_opcao_gato_icone.setImageResource(R.drawable.iconegato);
        }

        // IF PARA O SPINNER DOENÇAS
        if (intent.getStringExtra("doencas").equals("Sim")) {
            activity_opcao_gato_doenca_recebida_Spinner.setSelection(1);
        } else {
            activity_opcao_gato_doenca_recebida_Spinner.setSelection(0);
        }

        // IF PARA O SPINNER CASTRADO
        if (intent.getStringExtra("castrado").equals("Sim")) {
            activity_opcao_gato_castrado_recebido_Spinner.setSelection(1);
        } else {
            activity_opcao_gato_castrado_recebido_Spinner.setSelection(0);
        }


        // SETANDO OS EDIT TEXTS
        activity_opcao_gato_id_recebido_TextView.setText(intent.getStringExtra("id"));
        activity_opcao_gato_descricao_recebida_EditText.setText(intent.getStringExtra("descricao"));
        activity_opcao_gato_filhotes_recebido_EditText.setText(intent.getStringExtra("idade"));
        activity_opcao_gato_idade_recebida_EditText.setText(intent.getStringExtra("idade"));


    // FUNÇÃO CLICK DOS BOTÕES

        // BOTÃO VOLTAR
        activity_opcao_gato_buttonVoltarTelaGato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // BOTÃO QUE EXCLUI O GATO
        activity_opcao_gato_buttonExcluirGato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder confirmaExclusao = new AlertDialog.Builder(TelaOpcaoGato.this);
                confirmaExclusao.setTitle("ATENÇÃO!");
                confirmaExclusao.setMessage("Tem certeza que deseja excluir o gato de ID: " + idAtual + "?");
                confirmaExclusao.setCancelable(false);
                confirmaExclusao.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluiGato();
                    }
                });
                confirmaExclusao.setNegativeButton("Não", null);
                confirmaExclusao.create().show();

            }
        });

        // BOTÃO QUE ATUALIZA O GATO
        activity_opcao_gato_buttonAtualizarGato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                finish();
            }
        });
    }

    private void excluiGato() {
        try {
            ConexaoSQLite conexaoSQLite = new ConexaoSQLite(getApplicationContext());
            conexaoSQLite.excluirGato(idAtual);
            conexaoSQLite.close();
        } catch (SQLiteConstraintException e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        } finally {
            finish();
        }
    }
}