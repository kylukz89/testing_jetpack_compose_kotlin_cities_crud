package com.example.jetpackaula.Data.DAO

import androidx.room.*
import com.example.jetpackaula.Data.Entities.Cidades
import kotlinx.coroutines.flow.Flow

@Dao
interface CidadeDAO {

    // suspend fun = thread separada, ou seja coroutines...

    @Insert
    suspend fun insert(cidade: Cidades)

    @Update
    suspend fun update(cidade: Cidades)

    @Delete
    suspend fun delete(cidade: Cidades)

    // Não é suspende pq o FLOW do coroutines faz isso
    @Query("select * from cidades")
    fun getAllCidades(): Flow<List<Cidades>>
}