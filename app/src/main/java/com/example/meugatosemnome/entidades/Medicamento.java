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

public class Medicamento extends ProdutosEstoque {
    private String nome;

    public Medicamento() {}

    public Medicamento(int id, int idCategoria, double quantidade, double valor, String nome) {
        super(id, idCategoria, quantidade, valor);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarProduto(Medicamento med) {

        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        String sqlInsert = " INSERT INTO Medicamento (" +
                "id_Medicamento," +
                "nome_medicamento," +
                "quantidade," +
                "valor," +
                "Categoria_id_Medicamento_Categoria" +
                ") VALUES(?,?,?,?,?)";

        PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(sqlInsert);
        try {
            preparedStatement.setInt(1, med.getId());
            preparedStatement.setString(2, med.getNome());
            preparedStatement.setDouble(3, med.getQuantidade());
            preparedStatement.setDouble(4, med.getValor());
            preparedStatement.setInt(5, med.getIdCategoria());

            int resultado = preparedStatement.executeUpdate();

            if (resultado == 1){
                System.out.println("Medicamento inserido!!!");
            } else {
                System.out.println("Medicamento não inserido!!!");
            }
        } catch (SQLException e) {
            System.out.println("Medicamento não inserido!!!");

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

    public static List<Medicamento> buscaMedicamento() {

        ResultSet resultSet = null;
        Statement statement = null;
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite();
        conexaoSQLite.conectar();
        statement = conexaoSQLite.criarStatement();

        String query = "SELECT * FROM Medicamento;";
        List<Medicamento> medicamentos = new ArrayList<Medicamento>();
        try {
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Medicamento med = new Medicamento();
                med.setId(resultSet.getInt("id_Medicamento"));
                med.setNome(resultSet.getString("nome_medicamento"));
                med.setQuantidade(resultSet.getDouble("quantidade"));
                med.setValor(resultSet.getDouble("valor"));
                med.setIdCategoria(resultSet.getInt("Categoria_id_Medicamento_Categoria"));
                medicamentos.add(med);
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
        return medicamentos;
    }
}
