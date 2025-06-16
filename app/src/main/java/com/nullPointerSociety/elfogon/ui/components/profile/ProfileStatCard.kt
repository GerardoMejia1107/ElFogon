package com.nullPointerSociety.elfogon.ui.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileStatCard(
    stateTitle: String
) {
    Box(
        modifier = Modifier
            .background(Color(0xFF4A6E4D), shape = MaterialTheme.shapes.medium)
            .padding(16.dp)
            .clickable {}

    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "25", fontSize = 30.sp, color = Color.White)
            Text(
                text = stateTitle,
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}