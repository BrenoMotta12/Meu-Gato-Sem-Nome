
package com.example.meugatosemnome.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.meugatosemnome.R;
import com.example.meugatosemnome.conexoes.ConexaoSQLite;
import com.example.meugatosemnome.entidades.Gato;
import java.io.ByteArrayOutputStream;

public class TelaAdicionarGato extends AppCompatActivity {

    private ImageButton buttonVoltarTelaGato;
    private Switch switchDoenca;
    private EditText editDescricaoDoenca;
    private Switch switchCastrado;
    private EditText editFilhotes;
    private EditText editIdade;
    private Button buttonSalvarGato;
    private Button buttonSelecionarImagem;
    private static final int REQUEST_CODE_SELECT_IMAGE = 1;
    private ImageView imageAdicionarGato;
    String imageString = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_adicionar_gato);

        // ATRIBUIÇÕES DO FRONT
        buttonVoltarTelaGato = findViewById(R.id.buttonVoltarTelaGato);
        switchDoenca = findViewById(R.id.switchDoenca);
        editDescricaoDoenca = findViewById(R.id.editDescricaoDoenca);
        switchCastrado = findViewById(R.id.switchCastrado);
        editFilhotes = findViewById(R.id.editFilhotes);
        editIdade = findViewById(R.id.editIdade);
        buttonSalvarGato = findViewById(R.id.buttonSalvarGato);
        buttonSelecionarImagem = findViewById(R.id.buttonSelecionarImagem);
        imageAdicionarGato = findViewById(R.id.imageAdicionarGato);

        // BOTÃO VOLTAR
        buttonVoltarTelaGato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaAdicionarGato.this, TelaGatos.class);
                startActivity(intent);
            }
        });

        // BOTÃO QUE ABRE A CAMERA DO DISPOSITIVO
        buttonSelecionarImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
            }
        });

        // BOTÃO QUE SALVA O GATO NA DB VOLTA PARA A TELA DE GATOS
        buttonSalvarGato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String doenca;
                if (switchDoenca.isChecked()) {
                    doenca = "Sim";
                } else {
                    doenca = "Não";
                }
                String castrado;
                if (switchCastrado.isChecked()) {
                    castrado = "Sim";
                } else {
                    castrado = "Não";
                }
                if (!(editFilhotes.getText().toString().equals("") || editIdade.getText().toString().equals(""))) {

                    try {
                        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(getApplicationContext());
                        Gato gato = new Gato();
                        gato.setDoencas(doenca);
                        gato.setDescricaoDoencas((editDescricaoDoenca.getText().toString()));
                        gato.setCastrado(castrado);
                        gato.setFilhotes(Integer.valueOf(editFilhotes.getText().toString()));
                        gato.setIdade(Integer.valueOf(editIdade.getText().toString()));
                        gato.setImagemPath(imageString);

                        conexaoSQLite.adicionarGato(gato);

                    } catch (SQLException e) {
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    } finally {
                        finish();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // FUNÇÃO PARA CAPTURAR A IMAGEM E TRANSFORMAR EM UMA STRING
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_IMAGE) {
            try {
                Bitmap fotoRegistrada = (Bitmap) data.getExtras().get("data");
                imageAdicionarGato.setImageBitmap(fotoRegistrada);
                byte[] fotoemBytes;
                ByteArrayOutputStream streamDaFotoEmBytes = new ByteArrayOutputStream();

                fotoRegistrada.compress(Bitmap.CompressFormat.PNG, 70, streamDaFotoEmBytes);
                fotoemBytes = streamDaFotoEmBytes.toByteArray();
                imageString = android.util.Base64.encodeToString(fotoemBytes, Base64.DEFAULT);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}