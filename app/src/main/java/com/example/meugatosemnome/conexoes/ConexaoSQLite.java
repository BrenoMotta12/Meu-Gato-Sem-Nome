package com.example.meugatosemnome.conexoes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.meugatosemnome.entidades.Gato;

import java.util.ArrayList;
import java.util.List;

public class ConexaoSQLite extends SQLiteOpenHelper {

    private static final String NOME_BANCO_DE_DADOS = "db_mgsn.db";
    private static final int VERSAO_BANCO_DE_DADOS = 16;

    public ConexaoSQLite(Context context) {
        super(context, NOME_BANCO_DE_DADOS, null, VERSAO_BANCO_DE_DADOS);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlAreiaCategoria = "CREATE TABLE IF NOT EXISTS Areia_Categoria (" +
                "id_Areia_Categoria INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "descricao_areia TEXT NOT NULL" +
                ");";

        String sqlAreia = "CREATE TABLE IF NOT EXISTS Areia (" +
                "id_Areia INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "quantidade REAL NOT NULL," +
                "valor REAL NOT NULL," +
                "Categoria_id_Areia_Categoria INTEGER REFERENCES Areia_Categoria (id_Areia_Categoria) NOT NULL" +
                ");";

        String sqlMedicamentoCategoria = "CREATE TABLE IF NOT EXISTS Medicamento_Categoria (" +
                "id_Medicamento_Categoria INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "descricao_medicamento TEXT NOT NULL" +
                ");";

        String sqlMedicamento = "CREATE TABLE IF NOT EXISTS Medicamento (" +
                "id_Medicamento INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "nome_medicamento TEXT NOT NULL," +
                "quantidade REAL NOT NULL," +
                "valor REAL NOT NULL," +
                "Categoria_id_Medicamento_Categoria INTEGER REFERENCES Medicamento_Categoria (id_Medicamento_Categoria) NOT NULL" +
                ");";

        String sqlGato = "CREATE TABLE IF NOT EXISTS Gato (" +
                "id_Gato INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "doencas TEXT," +
                "descricao_doencas TEXT," +
                "castrado TEXT," +
                "filhotes INTEGER," +
                "idade INTEGER," +
                "imagem_path TEXT" +
                ");";

        String sqlRacaoCategoria = "CREATE TABLE IF NOT EXISTS Racao_Categoria (" +
                "id_Racao_Categoria INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "descricao_racao TEXT NOT NULL" +
                ");";

        String sqlRacao = "CREATE TABLE IF NOT EXISTS Racao (" +
                "id_Racao INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "quantidade REAL NOT NULL," +
                "valor REAL NOT NULL," +
                "Categoria_id_Racao_Categoria INTEGER REFERENCES Racao_Categoria (id_Racao_Categoria) NOT NULL" +
                ");";

        db.execSQL(sqlAreiaCategoria);
        db.execSQL(sqlAreia);
        db.execSQL(sqlMedicamentoCategoria);
        db.execSQL(sqlMedicamento);
        db.execSQL(sqlRacaoCategoria);
        db.execSQL(sqlRacao);
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
        values.put("doencas", gato.getDoencas());
        values.put("descricao_doencas", gato.getDescricaoDoencas());
        values.put("castrado", gato.getCastrado());
        values.put("filhotes", gato.getFilhotes());
        values.put("idade", gato.getIdade());
        values.put("imagem_path", gato.getImagemPath());

        db.insert("Gato", null, values);
    }

    public List<Gato> buscaGato() {

        List<Gato> gatos = new ArrayList<Gato>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Gato", null);
        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex("id_Gato");
            int doencasIndex = cursor.getColumnIndex("doencas");
            int descricaoDoencasIndex = cursor.getColumnIndex("descricao_doencas");
            int castradoIndex = cursor.getColumnIndex("castrado");
            int filhotesIndex = cursor.getColumnIndex("filhotes");
            int idadeIndex = cursor.getColumnIndex("idade");
            int imagem_pathIndex = cursor.getColumnIndex("imagem_path");

            Integer id = cursor.getInt(idIndex);
            String doencas = cursor.getString(doencasIndex);
            String descricaoDoencas = cursor.getString(descricaoDoencasIndex);
            String castrado = cursor.getString(castradoIndex);
            Integer filhotes = cursor.getInt(filhotesIndex);
            Integer idade = cursor.getInt(idadeIndex);
            //String imagem_path = cursor.getString(imagem_pathIndex);

            Gato gato = new Gato();
            gato.setId(id);
            gato.setDoencas(doencas);
            gato.setDescricaoDoencas(descricaoDoencas);
            gato.setCastrado(castrado);
            gato.setFilhotes(filhotes);
            gato.setIdade(idade);
            //gato.setImagemPath(imagem_path);

            gatos.add(gato);
        }
        cursor.close();
        return gatos;
    }
    public void excluirGato(String id) {
        SQLiteDatabase db = getWritableDatabase();

        String sql = "DELETE FROM Gato WHERE id_Gato = " + "'" + id + "'";
        db.execSQL(sql);
        db.close();
    }







}
