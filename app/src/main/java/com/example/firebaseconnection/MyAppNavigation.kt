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
fun MyAppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) { // Agora 'Modifier' é o correto
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier,
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