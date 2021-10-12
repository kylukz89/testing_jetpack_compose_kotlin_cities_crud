package com.example.jetpackaula.Listagem

import android.os.Bundle
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetpackaula.Data.Entities.Cidades
import java.util.*

@Composable
fun TelaListagemView(
    viewModel: TelaListagemCidadeViewModel = hiltViewModel(),
    navController: NavController
) {

    var cidades = viewModel.cidadeListagem.observeAsState(listOf())

    LazyColumn() {
        itemsIndexed(cidades.value) { index: Int, item ->
            cardCidade(navController = navController, cidade = item)
        }
    }
}

@Composable
fun cardCidade(navController: NavController, cidade: Cidades) {
// CardView
    Card(elevation = 4.dp,
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(2.dp, color = Color(0x77f5f5f5)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .height(50.dp)
            .clickable {
                // Evento click no botão e abre tela de detalhes...
                navController.currentBackStackEntry?.arguments = Bundle().apply {
                    putParcelable("cidade", cidade)
                }

                navController.navigate("telaExibir")
            }
    ) {
        // Conteúdo dentro deste cardview
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            // Text nome da cidade
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(5.dp),
                text = cidade.nomeCidade.toString().toUpperCase(),
                style = TextStyle(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center)
        }

    }
 
}