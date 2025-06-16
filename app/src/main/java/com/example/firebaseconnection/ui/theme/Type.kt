package com.example.firebaseconnection.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font // Importe a classe Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.firebaseconnection.R // Importe o R do seu projeto

// Definição da FontFamily para Bebas Neue Regular
val BebasNeueRegularFontFamily = FontFamily(
    Font(R.font.bebasneue_regular, FontWeight.Normal) // Assumindo que você renomeou o arquivo para bebasneue_regular.ttf
    // A fonte Bebas Neue Regular geralmente é um único estilo.
    // Se você tivesse outras variações de peso (ex: bebasneue_bold.ttf),
    // você as adicionaria aqui:
    // Font(R.font.bebasneue_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default, // Ou BebasNeueRegularFontFamily se quiser como padrão para corpo de texto
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    // Exemplo: Se você quisesse que o estilo titleLarge usasse Bebas Neue por padrão
    titleLarge = TextStyle(
        fontFamily = BebasNeueRegularFontFamily, // Usando a fonte Bebas Neue aqui
        fontWeight = FontWeight.Normal, // A própria fonte Bebas Neue já tem um peso/estilo definido
        fontSize = 30.sp, // Ajuste o tamanho conforme necessário para títulos
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    /* Outros estilos de texto que você pode querer sobrescrever ou adicionar,
       possivelmente usando BebasNeueRegularFontFamily ou outras fontes.
    displayLarge = TextStyle(
        fontFamily = BebasNeueRegularFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    headlineMedium = TextStyle(
        fontFamily = BebasNeueRegularFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        lineHeight = 36.sp
    )
    */
)