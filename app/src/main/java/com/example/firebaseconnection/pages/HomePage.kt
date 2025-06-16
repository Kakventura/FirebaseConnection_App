package com.example.firebaseconnection.pages

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.firebaseconnection.AuthState.Unauthenticated
import com.example.firebaseconnection.AuthViewModel
import com.example.firebaseconnection.R
import com.example.firebaseconnection.ui.theme.BebasNeueRegularFontFamily
import com.example.firebaseconnection.ui.theme.clear_green // Alterado de green para clear_green
import com.example.firebaseconnection.ui.theme.white01
import com.example.firebaseconnection.ui.theme.white02

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val authStateValue by authViewModel.authState.observeAsState()

    LaunchedEffect(authStateValue) {
        if (authStateValue is Unauthenticated) {
            navController.navigate("login") {
                popUpTo(navController.graph.startDestinationId) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.d7315d22680bf96823feff162693094e),
            contentDescription = "Background Image",
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
                text = "Bem-vindo!",
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
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Você está na sua página inicial.",
                fontSize = 22.sp,
                fontFamily = BebasNeueRegularFontFamily,
                color = white01,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black.copy(alpha = 0.3f),
                        offset = androidx.compose.ui.geometry.Offset(x = 4f, y = 4f),
                        blurRadius = 6f
                    )
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(48.dp))

            val signoutButtonInteractionSource = remember { MutableInteractionSource() }
            val isSignoutButtonPressed by signoutButtonInteractionSource.collectIsPressedAsState()

            val signoutButtonBackgroundColor by animateColorAsState(
                targetValue = if (isSignoutButtonPressed) white01 else clear_green, // Alterado para clear_green
                animationSpec = tween(durationMillis = 100)
            )
            val signoutButtonContentColor by animateColorAsState(
                targetValue = if (isSignoutButtonPressed) clear_green else white01, // Alterado para clear_green
                animationSpec = tween(durationMillis = 100)
            )
            val signoutButtonElevation by animateDpAsState(
                targetValue = if (isSignoutButtonPressed) 6.dp else 0.dp,
                animationSpec = tween(durationMillis = 100)
            )

            Button(
                onClick = {
                    authViewModel.signout()
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = signoutButtonBackgroundColor,
                    contentColor = signoutButtonContentColor,
                    disabledContainerColor = white02.copy(alpha = 0.5f),
                    disabledContentColor = Color.Gray
                ),
                interactionSource = signoutButtonInteractionSource,
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 0.dp,
                    pressedElevation = signoutButtonElevation,
                    focusedElevation = 0.dp,
                    hoveredElevation = 0.dp
                ),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(48.dp)
            ) {
                Text(
                    text = "Sair da conta",
                    fontFamily = BebasNeueRegularFontFamily,
                    fontSize = 18.sp
                )
            }
        }
    }
}