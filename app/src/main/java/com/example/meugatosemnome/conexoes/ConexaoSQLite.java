package com.example.meugatosemnome.conexoes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoSQLite extends SQLiteOpenHelper {

    private static final String NOME_BANCO_DE_DADOS = "db_mgsn.db";
    private static final int VERSAO_BANCO_DE_DADOS = 1;

    public ConexaoSQLite(Context context) {
        super(context, NOME_BANCO_DE_DADOS, null, VERSAO_BANCO_DE_DADOS);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlAreiaCategoria = "CREATE TABLE IF NOT EXISTS Areia_Categoria (" +
                "id_Areia_Categoria INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "descricao_areia TEXT NOT NULL" +
                ")";

        String sqlAreia = "CREATE TABLE IF NOT EXISTS Areia (" +
                "id_Areia INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "quantidade REAL NOT NULL," +
                "valor REAL NOT NULL," +
                "Categoria_id_Areia_Categoria INTEGER REFERENCES Areia_Categoria (id_Areia_Categoria) NOT NULL" +
                ")";

        String sqlMedicamentoCategoria = "CREATE TABLE IF NOT EXISTS Medicamento_Categoria (" +
                "id_Medicamento_Categoria INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "descricao_medicamento TEXT NOT NULL" +
                ")";

        String sqlMedicamento = "CREATE TABLE IF NOT EXISTS Medicamento (" +
                "id_Medicamento INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "nome_medicamento TEXT NOT NULL," +
                "quantidade REAL NOT NULL," +
                "valor REAL NOT NULL," +
                "Categoria_id_Medicamento_Categoria INTEGER REFERENCES Medicamento_Categoria (id_Medicamento_Categoria) NOT NULL" +
                ")";

        String sqlGato = "CREATE TABLE IF NOT EXISTS Gato (" +
                "id_Gato INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "doencas NUMERIC NOT NULL," +
                "descricao_doencas TEXT," +
                "castrado NUMERIC NOT NULL," +
                "filhotes INTEGER NOT NULL," +
                "idade INTEGER" +
                ")";

        String sqlRacaoCategoria = "CREATE TABLE IF NOT EXISTS Racao_Categoria (" +
                "id_Racao_Categoria INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "descricao_racao TEXT NOT NULL" +
                ")";

        String sqlRacao = "CREATE TABLE IF NOT EXISTS Racao (" +
                "id_Racao INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "quantidade REAL NOT NULL," +
                "valor REAL NOT NULL," +
                "Categoria_id_Racao_Categoria INTEGER REFERENCES Racao_Categoria (id_Racao_Categoria) NOT NULL" +
                ")";

        db.execSQL(sqlAreiaCategoria);
        db.execSQL(sqlAreia);
        db.execSQL(sqlMedicamentoCategoria);
        db.execSQL(sqlMedicamento);
        db.execSQL(sqlRacaoCategoria);
        db.execSQL(sqlRacao);
        db.execSQL(sqlGato);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
