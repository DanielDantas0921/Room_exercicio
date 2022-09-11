package com.danx0921.room_exercicio.model

import androidx.room.*

@Dao
interface CadastroDao {

    @Insert
    fun insert(cadastro: Cadastro)

    @Query("SELECT * FROM Cadastro")
     fun getRegisterAll() : List<Cadastro>

    @Query("SELECT Username FROM Cadastro WHERE id = :id")
    fun getUsername(id: Int) : String

    @Query("SELECT Password FROM Cadastro WHERE id = :id")
    fun getPassword(id: Int) : String

    @Delete
     fun delete(cadastro: Cadastro): Int // FIXME: retorna 1 se deu sucesso

    @Update
     fun update(cadastro: Cadastro)


}