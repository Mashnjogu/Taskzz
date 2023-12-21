package com.example.taskzz.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.taskzz.R


val quickSandFonts = FontFamily(
    Font(R.font.quicksand_bold, FontWeight.Bold),
    Font(R.font.quicksand_semibold, FontWeight.SemiBold),
    Font(R.font.quicksand_medium, FontWeight.Medium),
    Font(R.font.quicksand_regular, FontWeight.Normal),
    Font(R.font.quicksand_light, FontWeight.Light)
)
// Set of Material typography styles to start with
val Typography = Typography(
//    bodyLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    ),
    //h1
    bodyMedium = TextStyle(
        fontFamily = quickSandFonts,
        fontSize = 40.sp
    ),
    //h2
    headlineLarge = TextStyle(
        fontFamily = quickSandFonts,
        fontSize = 36.sp,
    ),
    //h3
    headlineMedium = TextStyle(
        fontFamily = quickSandFonts,
        fontSize = 13.sp,
    ),
    //for subtitle1
    headlineSmall = TextStyle(
        fontFamily = quickSandFonts,
        fontSize = 15.sp,
    ),
    //for body1
    labelMedium = TextStyle(
        fontFamily = quickSandFonts,
        fontSize = 13.sp,
    ),
    //for button
    displayMedium = TextStyle(
        fontFamily = quickSandFonts,
        fontSize = 13.sp,
    ),
    //for caption
    displaySmall = TextStyle(
        fontFamily = quickSandFonts,
        fontSize = 12.sp,
    )




    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)