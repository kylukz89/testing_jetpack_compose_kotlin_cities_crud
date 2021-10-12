package com.example.jetpackaula.Alteracoes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.jetpackaula.Data.Entities.Cidades
import com.example.jetpackaula.Data.Repositories.CidadesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TelaDetalheViewModel @Inject constructor(private val dao: CidadesRepository) : ViewModel() {

    // Id da cidade
    var id by mutableStateOf(0)

    // Padrão LiveData - cria variável privada
    private var _nomeCidade = MutableLiveData<String>()
    // E declara uma publica que trabalha var que trabalha em cima da _nomeCidade
    val nomeCidade: LiveData<String> = _nomeCidade


    // Padrão LiveData - cria variável privada
    private var _cepCidade = MutableLiveData<String>()
    // E declara uma publica que trabalha var que trabalha em cima da _nomeCidade
    val cepCidade: LiveData<String> = _cepCidade


    // Padrão LiveData - cria variável privada
    private var _ufCidade = MutableLiveData<String>()
    // E declara uma publica que trabalha var que trabalha em cima da _nomeCidade
    val ufCidade: LiveData<String> = _ufCidade




    fun onChangeNomeCidade(newValue: String) {
        _nomeCidade.value = newValue
    }
    fun onChangeCepCidade(newValue: String) {
        _cepCidade.value = newValue
    }
    fun onChangeUfCidade(newValue: String) {
        _ufCidade.value = newValue
    }


    // Valida se deu certo ou não a gravação
    val status: MutableLiveData<Boolean> = MutableLiveData()

    fun alterar() {
        // thread por coroutines
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dao.updateCidade(Cidades(
                    nomeCidade = nomeCidade.value,
                    cepCidade = cepCidade.value,
                    ufCidade = ufCidade.value,
                    id = id
                ))
                status.postValue(true)
            }
        }
    }

    fun remover() {
        // thread por coroutines
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dao.removeCidade(Cidades(
                    id = id
                ))
            }
            status.postValue(true)
        }
    }
}

@Composable
fun DetalheCidade(navController: NavController, cidade: Cidades) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        // nome cidade
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
        ) {
            Text(text = cidade.nomeCidade.toString())

        }
        // cep cidade
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
        ) {
            Text(text = cidade.cepCidade.toString())

        }
        // uf cidade
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
        ) {
            Text(text = cidade.ufCidade.toString())

        }
    }
}

