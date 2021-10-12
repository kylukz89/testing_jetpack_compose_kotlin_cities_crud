package com.example.jetpackaula.TelaCadastro

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackaula.Data.Entities.Cidades
import com.example.jetpackaula.Data.Repositories.CidadesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel // Injeção de dependencia- hilt dagger
class TelaCadastrarViewModel @Inject constructor(private val dao: CidadesRepository) : ViewModel() {

    // vals observáveis
    val nomeCidade = mutableStateOf("")
    val cepCidade = mutableStateOf("")
    val ufCidade = mutableStateOf("")

    fun onChangeNomeCidade(newValue: String) {
        nomeCidade.value = newValue
    }
    fun onChangeCepCidade(newValue: String) {
        cepCidade.value = newValue
    }
    fun onChangeUfCidade(newValue: String) {
        ufCidade.value = newValue
    }

    // Valida se deu certo ou não a gravação
    val status: MutableLiveData<Boolean> = MutableLiveData()

    fun cadastrar() {
        // Starta essa viewmodel que está
        viewModelScope.launch {
            // Thread separada
            withContext(Dispatchers.IO) {
                // Realiza o cadastro com os valores coletados das variaveis observáveis acima que foram alimentadas pelo campo de texto
                dao.addCidade(Cidades(nomeCidade = nomeCidade.value, cepCidade = cepCidade.value, ufCidade = ufCidade.value))
                // informa a variável booleana status que houve mutação do estado da lista após o cadastro (houve novos registros)
                status.postValue(true)
            }
        }
    }

    fun editar() {

    }

    fun deletar() {

    }


}