package com.pogreb.leasingshift.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
object CustomTextStyle {

    val primary: TextStyle = TextStyle(
        color = TextPrimary,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
    )

    val secondary: TextStyle = TextStyle(
        color = TextSecondary,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
    )

    val appTitle: TextStyle = TextStyle(
        color = TextPrimary,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
    )
}