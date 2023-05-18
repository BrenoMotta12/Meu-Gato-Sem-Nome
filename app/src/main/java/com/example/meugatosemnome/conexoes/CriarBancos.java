package com.example.meugatosemnome.conexoes;

import java.sql.SQLException;
import java.sql.Statement;

public class CriarBancos {

    private final ConexaoSQLite conexaoSQLite;

    public CriarBancos(ConexaoSQLite pconexaoSQLite) {
        this.conexaoSQLite = pconexaoSQLite;
    }

    public void criarTabelas() {

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


        boolean conectou = false;
        try {
            conectou = this.conexaoSQLite.conectar();
            Statement statement = this.conexaoSQLite.criarStatement();

            statement.execute(sqlAreiaCategoria);
            statement.execute(sqlAreia);
            statement.execute(sqlMedicamentoCategoria);
            statement.execute(sqlMedicamento);
            statement.execute(sqlGato);
            statement.execute(sqlRacaoCategoria);
            statement.execute(sqlRacao);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conectou) {
                this.conexaoSQLite.desconectar();
            }
        }

    }
}
