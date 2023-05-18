/*
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
public class Areia extends ProdutosEstoque {

    public Areia() {
    }
    public Areia(int id, int idCategoria, double quantidade, double valor) {
        super(id, idCategoria, quantidade, valor);
    }

    public void adicionarProduto(Areia areia) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        String sqlInsert = "INSERT INTO Areia (" +
                "id_Areia," +
                "quantidade," +
                "valor," +
                "Categoria_id_Areia_Categoria" +
                ") VALUES(?,?,?,?)";

        PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(sqlInsert);
        try {
            preparedStatement.setInt(1, areia.getId());
            preparedStatement.setDouble(2, areia.getQuantidade());
            preparedStatement.setDouble(3, areia.getValor());
            preparedStatement.setInt(4, areia.getIdCategoria());

            int resultado = preparedStatement.executeUpdate();

            if (resultado == 1){
                System.out.println("Areia inserida!!!");
            } else {
                System.out.println("Areia não inserida!!!");
            }
        } catch (SQLException e) {
            System.out.println("Areia não inserida!!!");
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

    public static List<Areia> buscaAreia() {
        ResultSet resultSet = null;
        Statement statement = null;
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        statement = conexaoSQLite.criarStatement();

        String query = "SELECT * FROM Areia;";
        List<Areia> areias = new ArrayList<>();
        try {
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Areia areia = new Areia();
                areia.setId(resultSet.getInt("id_Areia"));
                areia.setQuantidade(resultSet.getDouble("quantidade"));
                areia.setValor(resultSet.getDouble("valor"));
                areia.setIdCategoria(resultSet.getInt("Categoria_id_Areia_Categoria"));
                areias.add(areia);
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
        return areias;
    }



}
*/