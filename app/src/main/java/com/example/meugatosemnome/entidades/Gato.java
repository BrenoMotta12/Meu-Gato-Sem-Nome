package com.example.meugatosemnome.entidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.meugatosemnome.conexoes.ConexaoSQLite;

import java.util.ArrayList;
import java.util.List;

public class Gato {

    private int id;
    private boolean doencas;
    private String descricaoDoencas;
    private boolean castrado;
    private int filhotes;
    private int idade;

    public Gato(){
    }

    public Gato(int id, boolean doencas, String descricaoDoencas, boolean castrado, int filhotes, int idade) {
        this.id = id;
        this.doencas = doencas;
        this.descricaoDoencas = descricaoDoencas;
        this.castrado = castrado;
        this.filhotes = filhotes;
        this.idade = idade;
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

    // FIM GETTERS AND SETTERS

    // METODO QUE ADICIONA UM NOVO GATO
    public static void adicionarGato(Gato gato, ConexaoSQLite conexaoSQLite) {
        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id_Gato", gato.getId());
        values.put("doencas", gato.isDoencas());
        values.put("descricao_doencas", gato.getDescricaoDoencas());
        values.put("castrado", gato.isCastrado());
        values.put("filhotes", gato.getFilhotes());
        values.put("idade", gato.getIdade());

        db.insert("Gato", null, values);
        db.close();
        conexaoSQLite.close();
    }

    public static List<Gato> buscaGato(ConexaoSQLite conexaoSQLite) {
        List<Gato> gatos = new ArrayList<>();

        SQLiteDatabase db = conexaoSQLite.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Gato", null);
        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex("id_Gato");
                int doencasIndex = cursor.getColumnIndex("doencas");
                int descricaoDoencasIndex = cursor.getColumnIndex("descricao_doencas");
                int castradoIndex = cursor.getColumnIndex("castrado");
                int filhotesIndex = cursor.getColumnIndex("filhotes");
                int idadeIndex = cursor.getColumnIndex("idade");

                int id = cursor.getInt(idIndex);
                boolean doencas = cursor.getInt(doencasIndex) != 0;
                String descricaoDoencas = cursor.getString(descricaoDoencasIndex);
                boolean castrado = cursor.getInt(castradoIndex) != 0;
                int filhotes = cursor.getInt(filhotesIndex);
                int idade = cursor.getInt(idadeIndex);

                Gato gato = new Gato();
                gato.setId(id);
                gato.setDoencas(doencas);
                gato.setDescricaoDoencas(descricaoDoencas);
                gato.setCastrado(castrado);
                gato.setFilhotes(filhotes);
                gato.setIdade(idade);

                gatos.add(gato);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        conexaoSQLite.close();

        return gatos;
    }

    public void excluirGato(SQLiteOpenHelper openHelper, Gato gato) {
        SQLiteDatabase db = openHelper.getWritableDatabase();
        db.delete("Gato", "id_Gato = ?", new String[]{String.valueOf(gato.getId())});
        db.close();
    }
}