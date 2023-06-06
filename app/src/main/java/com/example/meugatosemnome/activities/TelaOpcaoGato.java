package com.example.meugatosemnome.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meugatosemnome.R;
import com.example.meugatosemnome.conexoes.ConexaoSQLite;
import com.example.meugatosemnome.entidades.Gato;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class TelaOpcaoGato extends AppCompatActivity {

    ImageButton activity_opcao_gato_buttonVoltarTelaGato;
    Button activity_opcao_gato_buttonAtualizarGato, activity_opcao_gato_buttonExcluirGato;
    CircleImageView activity_opcao_gato_icone;
    EditText activity_opcao_gato_descricao_recebida_EditText, activity_opcao_gato_filhotes_recebido_EditText,
            activity_opcao_gato_idade_recebida_EditText;
    TextView activity_opcao_gato_id_recebido_TextView;
    Spinner activity_opcao_gato_doenca_recebida_Spinner, activity_opcao_gato_castrado_recebido_Spinner;
    String doenca, castrado, imageString;
    int idAtual;
    Bitmap fotoRegistrada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_opcao_gato);
        Intent intent = getIntent();

        // LIGAÇÃO BACK-FRONT
        activity_opcao_gato_icone = findViewById(R.id.activity_opcao_gato_icone);
        activity_opcao_gato_id_recebido_TextView = findViewById(R.id.activity_opcao_gato_id_recebido_TextView);
        activity_opcao_gato_doenca_recebida_Spinner = findViewById(R.id.activity_opcao_gato_doenca_recebida_Spinner);
        activity_opcao_gato_descricao_recebida_EditText = findViewById(R.id.activity_opcao_gato_descricao_recebida_EditText);
        activity_opcao_gato_castrado_recebido_Spinner = findViewById(R.id.activity_opcao_gato_castrado_recebido_Spinner);
        activity_opcao_gato_filhotes_recebido_EditText = findViewById(R.id.activity_opcao_gato_filhotes_recebido_EditText);
        activity_opcao_gato_idade_recebida_EditText = findViewById(R.id.activity_opcao_gato_idade_recebida_EditText);

        // SETANDO A IMAGEM
        imageString = intent.getStringExtra("foto");
        Bitmap imagemDecodificada;
        if (intent.getStringExtra("foto") != null) {
            if (!intent.getStringExtra("foto").equals("")) {
                Log.d("Entrou", "no segundo if");
                byte[] imagemEmBytes = Base64.decode(intent.getStringExtra("foto"), Base64.DEFAULT);
                imagemDecodificada = BitmapFactory.decodeByteArray(imagemEmBytes, 0, imagemEmBytes.length);
                activity_opcao_gato_icone.setImageBitmap(imagemDecodificada);
            } else {
                Log.d("Entrou", "no else");
                activity_opcao_gato_icone.setImageResource(R.drawable.iconegato);
            }
        }



        // LIGAÇÃO BACK-FRONT DOS BOTÕES
        activity_opcao_gato_buttonVoltarTelaGato = findViewById(R.id.buttonVoltarTelaGato);
        activity_opcao_gato_buttonAtualizarGato = findViewById(R.id.activity_opcao_gato_buttonAtualizarGato);
        activity_opcao_gato_buttonExcluirGato = findViewById(R.id.activity_opcao_gato_buttonExcluirGato);

        // BOTÃO PARA ABRIR A CAMERA DO DISPOSITIVO

        activity_opcao_gato_icone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 2);
            }
        });

        // SETANDO O ID DO GATO ATUAL PARA AUXILIAR NO CODIGO
        idAtual = Integer.parseInt(intent.getStringExtra("id"));

        // IF PARA O SPINNER DOENÇAS
        if (intent.getStringExtra("doencas").equals("Sim")) {
            activity_opcao_gato_doenca_recebida_Spinner.setSelection(1);
            doenca = "Sim";
        } else {
            activity_opcao_gato_doenca_recebida_Spinner.setSelection(0);
            doenca = "Não";
        }
        activity_opcao_gato_doenca_recebida_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (activity_opcao_gato_doenca_recebida_Spinner.getSelectedItem().equals("Possui alguma doença")){
                    doenca = "Sim";
                } else {
                    doenca = "Não";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        // IF PARA O SPINNER CASTRADO
        if (intent.getStringExtra("castrado").equals("Sim")) {
            activity_opcao_gato_castrado_recebido_Spinner.setSelection(1);
            castrado = "Sim";
        } else {
            activity_opcao_gato_castrado_recebido_Spinner.setSelection(0);
            castrado = "Não";
        }
        activity_opcao_gato_castrado_recebido_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (activity_opcao_gato_castrado_recebido_Spinner.getSelectedItem().equals("É castrado")){
                    castrado = "Sim";
                } else {
                    castrado = "Não";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // SETANDO OS EDIT TEXTS
        activity_opcao_gato_id_recebido_TextView.setText(intent.getStringExtra("id"));
        activity_opcao_gato_descricao_recebida_EditText.setText(intent.getStringExtra("descricao"));
        activity_opcao_gato_filhotes_recebido_EditText.setText(intent.getStringExtra("filhotes"));
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
                atualizaGato();
                finish();
            }
        });
    }



    private void atualizaGato() {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(getApplicationContext());
        Gato gato = new Gato();
        gato.setId(idAtual);
        gato.setDoencas(doenca);
        gato.setDescricaoDoencas((activity_opcao_gato_descricao_recebida_EditText.getText().toString()));
        gato.setCastrado(castrado);
        gato.setFilhotes(Integer.valueOf(activity_opcao_gato_filhotes_recebido_EditText.getText().toString()));
        gato.setIdade(Integer.valueOf(activity_opcao_gato_idade_recebida_EditText.getText().toString()));
        gato.setImagemPath(imageString);
        conexaoSQLite.adicionarGato(gato);
        conexaoSQLite.close();
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            try {
                fotoRegistrada = (Bitmap) data.getExtras().get("data");
                activity_opcao_gato_icone.setImageBitmap(fotoRegistrada);
                byte[] fotoemBytes;
                ByteArrayOutputStream streamDaFotoEmBytes = new ByteArrayOutputStream();

                fotoRegistrada.compress(Bitmap.CompressFormat.PNG, 100, streamDaFotoEmBytes);
                fotoemBytes = streamDaFotoEmBytes.toByteArray();
                imageString = android.util.Base64.encodeToString(fotoemBytes, android.util.Base64.DEFAULT);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}