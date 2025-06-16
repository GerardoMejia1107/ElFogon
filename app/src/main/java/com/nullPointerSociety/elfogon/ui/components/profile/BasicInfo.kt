package com.nullPointerSociety.elfogon.ui.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun BasicInfo() {
    Box(
        modifier = Modifier
            .size(250.dp)
            .background(Color(0xFF7B9A85), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = "https://storage.googleapis.com/workspace-0f70711f-8b4e-4d94-86f1-2a93ccde5887/image/07f06bab-9f62-47b5-8b7c-d573844b62e1.png",
            contentDescription = "Foto de perfil circular con fondo beige claro y figura verde oscuro",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(250.dp)
                .clip(CircleShape)
                .background(Color(0xFFE6E0D8))
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = "José Armando Cardoza Sandoval",
        fontSize = 40.sp,
        textAlign = TextAlign.Center,
        lineHeight = 45.sp
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = "0000@gmail.com",
        fontSize = 24.sp,
        color = Color.Gray
    )
}