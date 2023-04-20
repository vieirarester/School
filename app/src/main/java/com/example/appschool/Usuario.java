package com.example.appschool;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Usuario {

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo
    String nome;
    @ColumnInfo
    String categoria;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public Usuario(String nome, String categoria){
        this.nome = nome;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return this.nome + '\n' + this.categoria;
    }
}
