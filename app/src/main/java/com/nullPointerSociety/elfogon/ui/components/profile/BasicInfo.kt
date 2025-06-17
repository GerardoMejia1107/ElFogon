package com.nullPointerSociety.elfogon.ui.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nullPointerSociety.elfogon.data.model.UserData

@Composable
fun BasicInfo(userData: State<UserData?>) {

    Box(
        modifier = Modifier
            .size(200.dp)
            .background(MaterialTheme.colorScheme.primary, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = userData.value?.profilePictureUrl.toString(),
            contentDescription = "Foto de perfil circular con fondo beige claro y figura verde oscuro",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(185.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.background)
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = userData.value?.name.toString(),
        style = MaterialTheme.typography.headlineMedium,
        textAlign = TextAlign.Center,
        lineHeight = 45.sp,
        modifier = Modifier.padding(horizontal = 20.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = userData.value?.email.toString(),
        fontSize = 24.sp,
        color = Color.Gray
    )
}