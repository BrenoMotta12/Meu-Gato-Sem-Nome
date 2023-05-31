package com.example.meugatosemnome.entidades;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;


public class Gato implements Parcelable {

    private Integer id;
    private String doencas;
    private String descricaoDoencas;
    private String castrado;
    private Integer filhotes;
    private Integer idade;
    private String imagemPath;

    public Gato() {
    }


    // INICIO GETTERS AND SETTERS

    protected Gato(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        doencas = in.readString();
        descricaoDoencas = in.readString();
        castrado = in.readString();
        if (in.readByte() == 0) {
            filhotes = null;
        } else {
            filhotes = in.readInt();
        }
        if (in.readByte() == 0) {
            idade = null;
        } else {
            idade = in.readInt();
        }
    }

    public static final Creator<Gato> CREATOR = new Creator<Gato>() {
        @Override
        public Gato createFromParcel(Parcel in) {
            return new Gato(in);
        }

        @Override
        public Gato[] newArray(int size) {
            return new Gato[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    // FIM GETTERS AND SETTERS

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
    }
}
