package com.example.jetpackaula.Data.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetpackaula.Data.DAO.CidadeDAO
import com.example.jetpackaula.Data.Entities.Cidades


// Extends roomdatabase
// Pode-se conter um array de classes/entidades
// entities = array das classes entidades
// version = versão do banco sqlite (incremental pra cada alteração)
@Database(entities = [Cidades::class], version = 1, exportSchema = false)
abstract class AppDB: RoomDatabase() {

    // função do tipo cidadedao
    abstract fun CidadeDAO(): CidadeDAO


}