package com.example.jetpackaula

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackaula.Alteracoes.TelaDetalheView
import com.example.jetpackaula.Data.Entities.Cidades
import com.example.jetpackaula.Listagem.TelaListagemView
import com.example.jetpackaula.TelaCadastro.TelaCadastrarView
import com.example.jetpackaula.TelaCadastro.TelaCadastrarViewModel
import com.example.jetpackaula.ui.theme.JetpackAulaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Para dizer ponto de entrada do hilt dagger na aplicação
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackAulaTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "TelaInicial") {
                        composable("TelaInicial") {
                            TelaInicial(navController)
                        }
                        composable("telaCadastrar") {
                            TelaCadastrarView(navController = navController)
                        }
                        composable("telaExibir") {

                            val cidade = navController
                                .previousBackStackEntry?.arguments?.getParcelable<Cidades>("cidade")

                            cidade?.let {
                                TelaDetalheView(navController = navController, cidade = it)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TelaInicial(navController: NavHostController) {
    Scaffold(
        content = {
            TelaListagemView(navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("telaCadastrar")
            }) {
                Icon(Icons.Filled.Add, contentDescription = "")
            }
        }, topBar = {
            TopAppBar(title = { Text(text = "APP CRUD") })
        }
    )
}