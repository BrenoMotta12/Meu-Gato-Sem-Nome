package com.example.meugatosemnome.entidades;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;


public class Gato {

    private Integer id;
    private String sexo;
    private String doencas;
    private String descricaoDoencas;
    private String castrado;
    private Integer filhotes;
    private Integer idade;
    private String imagemPath;


    public Gato() {
    }

    // INICIO GETTERS AND SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDoencas() {
        return doencas;
    }

    public void setDoencas(String doencas) {
        this.doencas = doencas;
    }

    public String getDescricaoDoencas() {
        return descricaoDoencas;
    }

    public void setDescricaoDoencas(String descricaoDoencas) {
        this.descricaoDoencas = descricaoDoencas;
    }

    public String getCastrado() {
        return castrado;
    }

    public void setCastrado(String castrado) {
        this.castrado = castrado;
    }

    public Integer getFilhotes() {
        return filhotes;
    }

    public void setFilhotes(Integer filhotes) {
        this.filhotes = filhotes;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }


    public String getImagemPath() {
        return imagemPath;
    }

    public void setImagemPath(String imagemPath) {
        this.imagemPath = imagemPath;
    }
}
    // FIM GETTERS AND SETTERS