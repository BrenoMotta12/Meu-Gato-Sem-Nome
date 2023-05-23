package com.example.meugatosemnome.entidades;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.meugatosemnome.conexoes.ConexaoSQLite;

import java.util.ArrayList;
import java.util.List;
public class Areia extends ProdutosEstoque {

    public Areia() {
    }
    public Areia(int id, int idCategoria, double quantidade, double valor) {
        super(id, idCategoria, quantidade, valor);
    }

    public void adicionarProduto(ConexaoSQLite conexaoSQLite, Areia ar) {
        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_Areia", ar.getId());
        values.put("quantidade", ar.getQuantidade());
        values.put("valor", ar.getValor());
        values.put("Categoria_id_Areia_Categoria", ar.getIdCategoria());

        db.insert("Areia", null, values);
        db.close();
    }

    public static List<Areia> buscaAreia(ConexaoSQLite conexaoSQLite) {
        List<Areia> areia = new ArrayList<>();
        SQLiteDatabase db = conexaoSQLite.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Areia", null);
        try {
            if (cursor.moveToFirst()) {
                int columnIndexId = cursor.getColumnIndex("id_Areia");
                int columnIndexQuantidade = cursor.getColumnIndex("quantidade");
                int columnIndexValor = cursor.getColumnIndex("valor");
                int columnIndexIdCategoria = cursor.getColumnIndex("Categoria_id_Areia_Categoria");

                do {
                    Areia ar = new Areia();
                    ar.setId(cursor.getInt(columnIndexId));
                    ar.setQuantidade(cursor.getDouble(columnIndexQuantidade));
                    ar.setValor(cursor.getDouble(columnIndexValor));
                    ar.setIdCategoria(cursor.getInt(columnIndexIdCategoria));

                    areia.add(ar);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
            db.close();
        }

        return areia;
    }
}