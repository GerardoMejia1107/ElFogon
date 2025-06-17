package com.nullPointerSociety.elfogon.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(

    // Para títulos gigantes como el nombre de la app en pantalla de bienvenida
    displayLarge = TextStyle(
        fontFamily = EB_Garamond,
        fontWeight = FontWeight.Bold,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),

    // Para títulos grandes de secciones principales
    displayMedium = TextStyle(
        fontFamily = EB_Garamond,
        fontWeight = FontWeight.Bold,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),

    // Para títulos grandes secundarios como nombres de categorías
    displaySmall = TextStyle(
        fontFamily = EB_Garamond,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),

    // Para encabezados de pantallas o subtítulos importantes
    headlineLarge = TextStyle(
        fontFamily = EB_Garamond,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),

    // Para encabezados un poco más discretos
    headlineMedium = TextStyle(
        fontFamily = EB_Garamond,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),

    // Para encabezados o títulos pequeños dentro de tarjetas, diálogos, etc.
    headlineSmall = TextStyle(
        fontFamily = EB_Garamond,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),

    // Para texto principal grande (por ejemplo, en descripciones largas o artículos)
    bodyLarge = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.25.sp
    ),

    // Para el cuerpo normal de texto (es la que usaré por defecto en la app)
    bodyMedium = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.25.sp
    ),

    // Para textos ligeramente más pequeños, como en tarjetas o labels informativos
    bodySmall = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.4.sp
    ),

    // Para botones destacados o elementos interactivos (como filtros, badges)
    labelLarge = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),

    // Para etiquetas más pequeñas, como indicadores o ítems dentro de listas
    labelMedium = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),

    // Para textos mínimos, como pistas dentro de campos de texto
    labelSmall = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),

    // Para títulos visibles y elegantes dentro de secciones o pantallas
    titleLarge = TextStyle(
        fontFamily = EB_Garamond,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 38.sp,
        letterSpacing = 0.sp
    ),

    // Para títulos secundarios de secciones (como en cards grandes o headers)
    titleMedium = TextStyle(
        fontFamily = EB_Garamond,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),

    // Para títulos pequeños o nombres dentro de componentes interactivos
    titleSmall = TextStyle(
        fontFamily = EB_Garamond,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    )
)
