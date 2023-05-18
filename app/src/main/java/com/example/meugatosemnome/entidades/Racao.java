package com.example.meugatosemnome.entidades;

import com.example.meugatosemnome.activities.MainActivity;
import com.example.meugatosemnome.conexoes.ConexaoSQLite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Racao extends ProdutosEstoque {

    public Racao() {
    }

    public Racao(int id, int idCategoria, double quantidade, double valor) {
        super(id, idCategoria, quantidade, valor);
    }

    public void adicionarProduto(Racao racao) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        String sqlInsert = "INSERT INTO Racao (" +
                "id_Racao," +
                "quantidade," +
                "valor," +
                "Categoria_id_Racao_Categoria" +
                ") VALUES(?,?,?,?)";

        PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(sqlInsert);
        try {
            preparedStatement.setInt(1, racao.getId());
            preparedStatement.setDouble(2, racao.getQuantidade());
            preparedStatement.setDouble(3, racao.getValor());
            preparedStatement.setInt(4, racao.getIdCategoria());

            int resultado = preparedStatement.executeUpdate();

            if (resultado == 1){
                System.out.println("Ração inserida!!!");
            } else {
                System.out.println("Ração não inserida!!!");
            }
        } catch (SQLException e) {
            System.out.println("Ração não inserida!!!");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            conexaoSQLite.desconectar();
        }
    }

    public static List<Racao> buscaRacao() {
        ResultSet resultSet = null;
        Statement statement = null;
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        statement = conexaoSQLite.criarStatement();

        String query = "SELECT * FROM Racao;";
        List<Racao> racoes = new ArrayList<>();
        try {
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Racao racao = new Racao();
                racao.setId(resultSet.getInt("id_Racao"));
                racao.setQuantidade(resultSet.getDouble("quantidade"));
                racao.setValor(resultSet.getDouble("valor"));
                racao.setIdCategoria(resultSet.getInt("Categoria_id_Racao_Categoria"));
                racoes.add(racao);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta");
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
                statement.close();
                conexaoSQLite.desconectar();
            } catch (SQLException ex){
                System.out.println("Erro de fechamentos");
            }
        }
        return racoes;
    }




}
