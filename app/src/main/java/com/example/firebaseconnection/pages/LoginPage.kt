package com.example.firebaseconnection.pages

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.firebaseconnection.AuthState
import com.example.firebaseconnection.AuthViewModel
import com.example.firebaseconnection.ui.theme.green_bakcgorund
import com.example.firebaseconnection.ui.theme.white01
import com.example.firebaseconnection.ui.theme.white02

@Composable
fun LoginPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> navController.navigate("home")
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT
            ).show()
            else -> Unit
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(green_bakcgorund)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Login Page",
            fontSize = 32.sp,
            color = white01
        )

        Spacer(modifier = Modifier.height(32.dp))


        val textFieldColors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Black,
            unfocusedTextColor = Black,
            focusedContainerColor = white01,
            unfocusedContainerColor = white01,
            disabledContainerColor = white02,
            cursorColor = Black,
            focusedBorderColor = white01,
            unfocusedBorderColor = white01,
            focusedLabelColor = Black,
            unfocusedLabelColor = Black.copy(alpha = 0.7f)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            colors = textFieldColors,
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            colors = textFieldColors,
            singleLine = true
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { authViewModel.login(email, password) },
            enabled = authState.value != AuthState.Loading,
            colors = ButtonDefaults.buttonColors(
                containerColor = white01,
                contentColor = Black
            )
        ) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { navController.navigate("signup") }
        ) {
            Text(
                text = "Não tem conta? Inscreva-se já!",
                color = white01
            )
        }
    }
}
