package com.example.meugatosemnome.conexoes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.meugatosemnome.entidades.Gato;

import java.util.ArrayList;
import java.util.List;

public class ConexaoSQLite extends SQLiteOpenHelper {

    private static final String NOME_BANCO_DE_DADOS = "db_mgsn.db";
    private static final int VERSAO_BANCO_DE_DADOS = 19;

    public ConexaoSQLite(Context context) {
        super(context, NOME_BANCO_DE_DADOS, null, VERSAO_BANCO_DE_DADOS);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlGato = "CREATE TABLE IF NOT EXISTS Gato (" +
                "id_Gato INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "sexo TEXT," +
                "doencas TEXT," +
                "descricao_doencas TEXT," +
                "castrado TEXT," +
                "filhotes INTEGER," +
                "idade INTEGER," +
                "imagem_path String" +
                ");";

        String sqlProduto = "CREATE TABLE IF NOT EXISTS Produto (" +
                "id_Produto INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "tipo_Produto" +
                "categoria TEXT," +
                "nome TEXT," +
                "quantidade REAL" +
                ");";

        db.execSQL(sqlProduto);
        db.execSQL(sqlGato);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlAreiaCategoria = "DROP TABLE IF EXISTS Areia_Categoria;";
        String sqlAreia = "DROP TABLE IF EXISTS Areia;";
        String sqlMedicamentoCategoria = "DROP TABLE IF EXISTS Medicamento_Categoria;";
        String sqlMedicamento = "DROP TABLE IF EXISTS Medicamento;";
        String sqlGato = "DROP TABLE IF EXISTS Gato;";
        String sqlRacaoCategoria = "DROP TABLE IF EXISTS Racao_Categoria;";
        String sqlRacao = "DROP TABLE IF EXISTS Racao;";

        db.execSQL(sqlAreiaCategoria);
        db.execSQL(sqlAreia);
        db.execSQL(sqlMedicamentoCategoria);
        db.execSQL(sqlMedicamento);
        db.execSQL(sqlRacaoCategoria);
        db.execSQL(sqlRacao);
        db.execSQL(sqlGato);

        onCreate(db);
    }
    public void realizarVacuum() {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("VACUUM");

        db.close();
    }


    // METODO QUE ADICIONA UM NOVO GATO
    public void adicionarGato(Gato gato) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_Gato", gato.getId());
        values.put("sexo", gato.getSexo());
        values.put("doencas", gato.getDoencas());
        values.put("descricao_doencas", gato.getDescricaoDoencas());
        values.put("castrado", gato.getCastrado());
        values.put("filhotes", gato.getFilhotes());
        values.put("idade", gato.getIdade());

        if (!gato.getImagemPath().equals("") && gato.getImagemPath() != null) {
            values.put("imagem_path", gato.getImagemPath());
        } else {
            values.put("imagem_path", "");
        }

        long rowId = db.insertWithOnConflict("Gato", null, values, SQLiteDatabase.CONFLICT_IGNORE);

        if (rowId == -1) {
            db.update("Gato", values, "id_Gato = ?", new String[]{String.valueOf(gato.getId())});
        }
    }

    public List<Gato> buscaGato(String filtro) {

        List<Gato> gatos = new ArrayList<Gato>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Gato " + filtro + "ORDER BY id_Gato;", null);
        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex("id_Gato");
            int sexoIndex = cursor.getColumnIndex("sexo");
            int doencasIndex = cursor.getColumnIndex("doencas");
            int descricaoDoencasIndex = cursor.getColumnIndex("descricao_doencas");
            int castradoIndex = cursor.getColumnIndex("castrado");
            int filhotesIndex = cursor.getColumnIndex("filhotes");
            int idadeIndex = cursor.getColumnIndex("idade");
            int imagem_pathIndex = cursor.getColumnIndex("imagem_path");

            Integer id = cursor.getInt(idIndex);
            String sexo = cursor.getString(sexoIndex);
            String doencas = cursor.getString(doencasIndex);
            String descricaoDoencas = cursor.getString(descricaoDoencasIndex);
            String castrado = cursor.getString(castradoIndex);
            Integer filhotes = cursor.getInt(filhotesIndex);
            Integer idade = cursor.getInt(idadeIndex);
            String imagem_path = cursor.getString(imagem_pathIndex);

            Gato gato = new Gato();
            gato.setId(id);
            gato.setSexo(sexo);
            gato.setDoencas(doencas);
            gato.setDescricaoDoencas(descricaoDoencas);
            gato.setCastrado(castrado);
            gato.setFilhotes(filhotes);
            gato.setIdade(idade);
            gato.setImagemPath(imagem_path);

            gatos.add(gato);
        }
        cursor.close();
        return gatos;
    }
    public void excluirGato(int id) {
        SQLiteDatabase db = getWritableDatabase();

        String sql = "DELETE FROM Gato WHERE id_Gato = " + "'" + id + "'";
        db.execSQL(sql);
        db.close();
    }







}
