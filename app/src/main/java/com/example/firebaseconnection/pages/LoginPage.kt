package com.example.firebaseconnection.pages

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
// import androidx.compose.ui.text.font.FontWeight // Não usado explicitamente para BebasNeue aqui
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.firebaseconnection.AuthState
import com.example.firebaseconnection.AuthViewModel
import com.example.firebaseconnection.R
import com.example.firebaseconnection.ui.theme.BebasNeueRegularFontFamily
import com.example.firebaseconnection.ui.theme.black // Supondo que você tenha 'black' definido
import com.example.firebaseconnection.ui.theme.green
// Importe suas cores personalizadas (exemplo)
// import com.example.firebaseconnection.ui.theme.MyGreen
// import com.example.firebaseconnection.ui.theme.MyWhite
import com.example.firebaseconnection.ui.theme.white01
import com.example.firebaseconnection.ui.theme.white02


// Defina suas cores aqui ou importe-as se já estiverem em Color.kt
val MyGreen = Color(0xFF4CAF50) // Exemplo de cor verde, substitua pela sua
val MyWhite = Color.White       // Usando Color.White diretamente

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    val emailInteractionSource = remember { MutableInteractionSource() }
    val passwordInteractionSource = remember { MutableInteractionSource() }

    val isEmailFocused by emailInteractionSource.collectIsFocusedAsState()
    val isPasswordFocused by passwordInteractionSource.collectIsFocusedAsState()

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

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.dd61630329171f614c5909b3dfd9ca30),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Login Page",
                fontSize = 55.sp,
                fontFamily = BebasNeueRegularFontFamily,
                color = white01,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black.copy(alpha = 0.5f),
                        offset = androidx.compose.ui.geometry.Offset(x = 8f, y = 8f),
                        blurRadius = 10f
                    )
                ),
                modifier = Modifier.offset(y = (0).dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            val textFieldShape = RoundedCornerShape(16.dp)

            val customTextFieldColors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedContainerColor = white01,
                unfocusedContainerColor = white01,
                disabledContainerColor = white02,
                cursorColor = Color.Black,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedLabelColor = white01,
                unfocusedLabelColor = black.copy(alpha = 0.7f)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email", fontFamily = BebasNeueRegularFontFamily) },
                colors = customTextFieldColors,
                singleLine = true,
                shape = textFieldShape,
                textStyle = TextStyle(fontFamily = BebasNeueRegularFontFamily, color = Color.Black),
                interactionSource = emailInteractionSource,
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (isEmailFocused) Modifier.shadow(
                            elevation = 8.dp,
                            shape = textFieldShape,
                            spotColor = Color.White,
                            ambientColor = Color.White
                        ) else Modifier
                    )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password", fontFamily = BebasNeueRegularFontFamily) },
                colors = customTextFieldColors,
                singleLine = true,
                shape = textFieldShape,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (passwordVisible) "Hide password" else "Show password"
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, description, tint = Color.Black.copy(alpha = 0.7f))
                    }
                },
                textStyle = TextStyle(fontFamily = BebasNeueRegularFontFamily, color = Color.Black),
                interactionSource = passwordInteractionSource,
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (isPasswordFocused) Modifier.shadow(
                            elevation = 8.dp,
                            shape = textFieldShape,
                            spotColor = Color.White,
                            ambientColor = Color.White
                        ) else Modifier
                    )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Interação para o botão de Login
            val loginButtonInteractionSource = remember { MutableInteractionSource() }
            val isLoginButtonPressed by loginButtonInteractionSource.collectIsPressedAsState()

            val loginButtonBackgroundColor by animateColorAsState(
                targetValue = if (isLoginButtonPressed) white01 else green,
                animationSpec = tween(durationMillis = 100)
            )
            val loginButtonContentColor by animateColorAsState(
                targetValue = if (isLoginButtonPressed) green else white01,
                animationSpec = tween(durationMillis = 100)
            )
            val loginButtonElevation by animateDpAsState(
                targetValue = if (isLoginButtonPressed) 6.dp else 0.dp, // Sombra ao pressionar
                animationSpec = tween(durationMillis = 100)
            )

            Button(
                onClick = { authViewModel.login(email, password) },
                enabled = authState.value != AuthState.Loading,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = loginButtonBackgroundColor,
                    contentColor = loginButtonContentColor,
                    disabledContainerColor = white02.copy(alpha = 0.5f),
                    disabledContentColor = Color.Gray
                ),
                interactionSource = loginButtonInteractionSource,
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 0.dp,
                    pressedElevation = loginButtonElevation, // Usa o Dp animado
                    focusedElevation = 0.dp, // Pode ajustar se necessário
                    hoveredElevation = 0.dp // Pode ajustar se necessário
                ),
                modifier = Modifier
                    .fillMaxWidth(0.9f) // Ocupa 90% da largura, um pouco menor que os inputs
                    .height(48.dp)      // Altura um pouco menor
            ) {
                Text(text = "Login", fontFamily = BebasNeueRegularFontFamily, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = { navController.navigate("signup") }
            ) {
                Text(
                    text = "Não tem conta? Inscreva-se já!",
                    color = white01,
                    fontFamily = BebasNeueRegularFontFamily,
                    fontSize = 20.sp
                )
            }
        }
    }
}