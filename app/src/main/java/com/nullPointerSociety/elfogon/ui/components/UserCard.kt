package com.nullPointerSociety.elfogon.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.firebase.Timestamp
import com.nullPointerSociety.elfogon.R
import com.nullPointerSociety.elfogon.data.model.user.UserReceptor
import com.nullPointerSociety.elfogon.ui.theme.AppColors


@Composable
fun UserCard(user: UserReceptor) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .heightIn(min = 130.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            // Contenido principal
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Avatar
                AsyncImage(
                    model = user.profilePictureUrl ?: "",
                    contentDescription = "Profile image",
                    placeholder = painterResource(id = R.drawable.user_default_pic),
                    error = painterResource(id = R.drawable.user_default_pic),
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                )



                Spacer(modifier = Modifier.width(12.dp))

                // Info principal
                Column(modifier = Modifier.weight(1f).padding(10.dp)) {
                    Text(
                        text = "${user.name} ${user.lastName}".toSentenceCase(),
                        style = MaterialTheme.typography.titleSmall,
                        color = AppColors.titleText
                    )
                    Spacer(modifier = Modifier.height(1.dp))

                    Text(
                        text = user.email,
                        style = MaterialTheme.typography.bodySmall,
                        color = AppColors.bodyText
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "ID: ${user.id}",
                        style = MaterialTheme.typography.labelSmall,
                        color = AppColors.bodyText
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Joined: ${user.registerDate.toDate().toString().dropLast(15)}",
                        style = MaterialTheme.typography.labelSmall,
                        color = AppColors.bodyText
                    )
                }
            }

            // Rol como chip flotante en la esquina superior derecha
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .background(
                        color = if (user.role.lowercase() == "admin") Color(0xFFDCF4F2)
                        else Color(0xFFE8ECF4),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = user.role.uppercase(),
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                    color = if (user.role.lowercase() == "admin") Color(0xFF00796B)
                    else Color(0xFF304FFE)
                )
            }
        }
    }
}



fun String.toSentenceCase(): String {
    return this
        .lowercase()
        .split(" ")
        .joinToString(" ") { word ->
            word.replaceFirstChar { it.uppercase() }
        }
}


