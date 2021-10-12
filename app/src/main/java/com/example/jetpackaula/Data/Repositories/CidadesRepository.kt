package com.example.jetpackaula.Data.Repositories

import com.example.jetpackaula.Data.Database.AppDB
import com.example.jetpackaula.Data.Entities.Cidades
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped // ciclo de vida de viewmodel, sempre vai existir dentro de uma viewmodel
class CidadesRepository  @Inject constructor(appDB: AppDB) {

    private val dao = appDB.CidadeDAO()
    val getAllCidades: Flow<List<Cidades>> = dao.getAllCidades()

    // Cadastra cidade
    suspend fun addCidade(c: Cidades) {
        dao.insert(c)
    }
    // Atualiza cidade
    suspend fun updateCidade(c: Cidades) {
        dao.update(c)
    }
    // Deleta cidade
    suspend fun removeCidade(c: Cidades) {
        dao.delete(c)
    }

}