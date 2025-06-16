package com.example.firebaseconnection

import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebaseconnection.pages.HomePage
import com.example.firebaseconnection.pages.LoginPage
import com.example.firebaseconnection.pages.SignupPage

import androidx.compose.ui.Modifier

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    // O NavController controla os dados e permite a navegação destes entre as páginas

    // NavHost é o contêiner que exibe o destino atual da navegação.
    NavHost(
        navController = navController,
        startDestination = "login", // Definição da tela principal como login
        modifier = modifier,

        // Definição de destinos (navegação)
        //Estilo if/else ("se reconhecer "login" deverá abrir a página LoginPage)
        builder = {
            composable("login") {
                LoginPage(navController = navController, authViewModel = authViewModel)
            }

            composable("signup") {
                SignupPage(navController = navController, authViewModel = authViewModel)
            }

            composable("home") {
                HomePage(navController = navController, authViewModel = authViewModel)
            }
        }
    )
}