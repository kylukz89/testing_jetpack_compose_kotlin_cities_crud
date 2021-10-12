package com.example.jetpackaula.Listagem

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.jetpackaula.Data.Entities.Cidades
import com.example.jetpackaula.Data.Repositories.CidadesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TelaListagemCidadeViewModel @Inject constructor (private val dao: CidadesRepository) : ViewModel() {

    val cidadeListagem: LiveData<List<Cidades>> = dao.getAllCidades.asLiveData()

}