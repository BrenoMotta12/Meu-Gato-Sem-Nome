package com.example.meugatosemnome.entidades;

import android.graphics.drawable.Drawable;

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

public class Gato {

    private int id;
    private boolean doencas;
    private String descricaoDoencas;
    private boolean castrado;
    private int filhotes;
    private int idade;
    // private byte[] foto;

    public Gato(){
    }

    // INICIO GETTERS AND SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDoencas() {
        return doencas;
    }

    public void setDoencas(boolean doencas) {
        this.doencas = doencas;
    }

    public String getDescricaoDoencas() {
        return descricaoDoencas;
    }

    public void setDescricaoDoencas(String descricaoDoencas) {
        this.descricaoDoencas = descricaoDoencas;
    }

    public boolean isCastrado() {
        return castrado;
    }

    public void setCastrado(boolean castrado) {
        this.castrado = castrado;
    }

    public int getFilhotes() {
        return filhotes;
    }

    public void setFilhotes(int filhotes) {
        this.filhotes = filhotes;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    /*

    public Drawable getFoto() {
        return foto;
    }


    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

     */

    // FIM GETTERS AND SETTERS

    // METODO QUE ADICIONA UM NOVO GATO

    public void adicionarGato(Gato gato, ConexaoSQLite conexaoSQLite) {

        String sqlInsert = " INSERT INTO Gato (" +
                "id_Gato," +
                "doencas," +
                "descricao_doencas," +
                "castrado" +
                "filhotes" +
                "idade" +
                ") VALUES(?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(sqlInsert);
        try {
            preparedStatement.setInt(1, gato.getId());
            preparedStatement.setBoolean(2, gato.isDoencas());
            // preparedStatement.setBytes(3, gato.getFoto());
            preparedStatement.setString(3, gato.getDescricaoDoencas());
            preparedStatement.setBoolean(4, gato.isCastrado());
            preparedStatement.setInt(5, gato.getFilhotes());
            preparedStatement.setInt(6, gato.getIdade());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    // MÉTODO QUE LISTA OS GATOS
    public static ArrayList<Gato> buscaGato(ConexaoSQLite conexaoSQLite) {

        ResultSet resultSet = null;
        Statement statement = null;
        statement = conexaoSQLite.criarStatement();

        String query = "SELECT * FROM Gato;";
        List<Gato> gatos = new ArrayList<Gato>();
        try {
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Gato gato = new Gato();
                gato.setId(resultSet.getInt("id_Gato"));
                gato.setDoencas(resultSet.getBoolean("doencas"));
                // gato.setFoto(resultSet.getBytes("foto"));
                gato.setDescricaoDoencas(resultSet.getString("descricao_doencas"));
                gato.setCastrado(resultSet.getBoolean("castrado"));
                gato.setFilhotes(resultSet.getInt("filhotes"));
                gato.setIdade(resultSet.getInt("idade"));

                gatos.add(gato);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta");
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
                statement.close();
            } catch (SQLException ex){
                System.out.println("Erro de fechamentos");
            }
        }
        return (ArrayList<Gato>) gatos;
    }

    public void excluirGato (ConexaoSQLite conexaoSQLite, Gato gato){
        String sql = "DELETE FROM Gato WHERE id_Gato = ?;";

        PreparedStatement preparedStatement = conexaoSQLite.criarPreparedStatement(sql);
        try {
            preparedStatement.setInt(1, gato.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}