package com.example.jetpackaula.TelaCadastro

import android.widget.Spinner
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetpackaula.R

@Composable
fun TelaCadastrarView(viewModel: TelaCadastrarViewModel = hiltViewModel(), navController: NavController) {

    val nome = viewModel.nomeCidade.value
    val cep = viewModel.cepCidade.value
    val uf = viewModel.ufCidade.value

    val status = viewModel.status.observeAsState()

    if (status.value == true) {
        navController.popBackStack()
    }

    // FORM
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(10.dp))

        // CIDADE
        OutlinedTextField(label = { Text(text = "Informe o nome da cidade...")}, value = nome, onValueChange = {
            viewModel.onChangeNomeCidade(it)
        }, leadingIcon = { Icon(
            painter = painterResource(id = R.drawable.ic_city),
            contentDescription = null // decorative element
        ) })

        // CEP
        OutlinedTextField(label = { Text(text = "Informe o cep da cidade...")}, value = cep, onValueChange = {
            viewModel.onChangeCepCidade(it)
        }, leadingIcon = { Icon(
            painter = painterResource(id = R.drawable.ic_cep),
            contentDescription = null // decorative element
        ) })

        // UF
        OutlinedTextField(label = { Text(text = "Informe o uf da cidade...")}, value = uf, onValueChange = {
            viewModel.onChangeUfCidade(it)
        }, leadingIcon = { Icon(
            painter = painterResource(id = R.drawable.ic_uf),
            contentDescription = null // decorative element
        ) })
        Spacer(modifier = Modifier.height(10.dp))

        // Botão
        Button(
            onClick = {
                viewModel.cadastrar()
            }
        ) {

        }
    }

}