package com.example.appschool;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UsuarioDao {

    @Insert
    void inserir(Usuario usuario);

    @Query("SELECT * FROM Usuario")
    List<Usuario> listarTodos();

    @Delete
    void deletar(Usuario usuario);

}
