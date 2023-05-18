package com.example.meugatosemnome.conexoes;


import android.database.sqlite.SQLiteDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoSQLite {

    private Connection conexao;

    public Connection getConexao() {
        return this.conexao;
    }

    // Conecta a um banco de dados (cria o banco se ele não existir)
    public boolean conectar() {

        try {
            String url = "jdbc:sqlite:banco_de_dados/db_mgsn.db";

            this.conexao = DriverManager.getConnection(url);
            System.out.println("Conectado!!!");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean desconectar() {

        try {
            if (!this.conexao.isClosed()) {
                this.conexao.close();
            }
            System.out.println("desconectado!!!");
        } catch (SQLException e) {

            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }


    // Criar os statements para nossos sqls serem executados

    public Statement criarStatement() {
        try {
            return this.conexao.createStatement();
        } catch (SQLException e) {
            return null;
        }
    }
    public PreparedStatement criarPreparedStatement(String sql) {
        try {
            return this.conexao.prepareStatement(sql);
        } catch (SQLException e) {
            return null;
        }
    }
}
