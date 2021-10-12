package com.example.jetpackaula.Alteracoes

import android.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetpackaula.Data.Entities.Cidades
import com.example.jetpackaula.R


@Composable
fun TelaDetalheView(
    navController: NavController,
    viewModel: TelaDetalheViewModel = hiltViewModel(),
    cidade: Cidades
) {

    val nome: String by viewModel.nomeCidade.observeAsState(cidade.nomeCidade.toString())
    val cep: String by viewModel.cepCidade.observeAsState(cidade.cepCidade.toString())
    val uf: String by viewModel.ufCidade.observeAsState(cidade.ufCidade.toString())

    // coleta id da cidade da viewmodel de listagem
    viewModel.id = cidade.id!!.toInt()
    //
    viewModel.onChangeNomeCidade(nome)
    viewModel.onChangeCepCidade(cep)
    viewModel.onChangeUfCidade(uf)

    val status = viewModel.status.observeAsState()

    if (status.value == true) {
        navController.popBackStack()
    }

    // FORM
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(10.dp))

        // CIDADE
        OutlinedTextField(
            label = { Text(text = "Informe o nome da cidade...") },
            value = nome,
            onValueChange = {
                viewModel.onChangeNomeCidade(it)
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_city),
                    contentDescription = null // decorative element
                )
            })

        // CEP
        OutlinedTextField(
            label = { Text(text = "Informe o cep da cidade...") },
            value = cep,
            onValueChange = {
                viewModel.onChangeCepCidade(it)
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cep),
                    contentDescription = null // decorative element
                )
            })

        // UF
        OutlinedTextField(
            label = { Text(text = "Informe o uf da cidade...") },
            value = uf,
            onValueChange = {
                viewModel.onChangeUfCidade(it)
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_uf),
                    contentDescription = null // decorative element
                )
            })
        Spacer(modifier = Modifier.height(10.dp))

        // Botão editar
        Button(
            onClick = {
                viewModel.alterar()
            }
        ) {
            Text(text = "Alterar")
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp))

        // Botão deletar
        Button(
            onClick = {
                viewModel.remover()
            }
        ) {
            Text(text = "Deletar")
        }
    }

}