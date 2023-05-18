package com.example.meugatosemnome.entidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MergeCursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.meugatosemnome.conexoes.ConexaoSQLite;

import java.util.ArrayList;
import java.util.List;

public class Medicamento extends ProdutosEstoque {
    private String nome;

    public Medicamento() {
    }

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

    public void adicionarProduto(Context context, Medicamento med) {
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(context);
        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_Medicamento", med.getId());
        values.put("nome_medicamento", med.getNome());
        values.put("quantidade", med.getQuantidade());
        values.put("valor", med.getValor());
        values.put("Categoria_id_Medicamento_Categoria", med.getIdCategoria());

        long resultado = db.insert("Medicamento", null, values);
        if (resultado != -1) {
            System.out.println("Medicamento inserido!!!");
        } else {
            System.out.println("Medicamento não inserido!!!");
        }

        db.close();
    }

    public static List<Medicamento> buscaMedicamento(Context context) {
        List<Medicamento> medicamentos = new ArrayList<>();
        ConexaoSQLite conexaoSQLite = new ConexaoSQLite(context);
        SQLiteDatabase db = conexaoSQLite.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Medicamento", null);
        try {
            if (cursor.moveToFirst()) {
                int columnIndexId = cursor.getColumnIndex("id_Medicamento");
                int columnIndexNome = cursor.getColumnIndex("nome_medicamento");
                int columnIndexQuantidade = cursor.getColumnIndex("quantidade");
                int columnIndexValor = cursor.getColumnIndex("valor");
                int columnIndexIdCategoria = cursor.getColumnIndex("Categoria_id_Medicamento_Categoria");

                do {
                    Medicamento med = new Medicamento();
                    med.setId(cursor.getInt(columnIndexId));
                    med.setNome(cursor.getString(columnIndexNome));
                    med.setQuantidade(cursor.getDouble(columnIndexQuantidade));
                    med.setValor(cursor.getDouble(columnIndexValor));
                    med.setIdCategoria(cursor.getInt(columnIndexIdCategoria));

                    medicamentos.add(med);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
            db.close();
        }

        return medicamentos;
    }
}