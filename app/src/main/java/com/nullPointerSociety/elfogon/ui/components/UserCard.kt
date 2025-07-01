package com.nullPointerSociety.elfogon.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.firebase.Timestamp
import com.nullPointerSociety.elfogon.R
import com.nullPointerSociety.elfogon.data.model.user.UserReceptor
import com.nullPointerSociety.elfogon.ui.theme.AppColors

val testUser = UserReceptor(
    id = "ABC123456",
    name = "Gerardo",
    lastName = "Ramírez",
    email = "gerardo@example.com",
    profilePictureUrl = "https://d2gwgwt9a7yxle.cloudfront.net/what_is_user_id_in_net_banking_mobile_871b681e52.jpg",
    role = "admin",
    customSavedRecipes = listOf("CUSTOM_0001", "CUSTOM_0002"),
    savedRecipes = listOf("101", "202", "303"),
    madeRecipes = listOf("101", "202"),
    registerDate = Timestamp.now()
)

@Composable
fun UserCard(user: UserReceptor) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Imagen de perfil usando recurso local
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .border(4.dp, MaterialTheme.colorScheme.primary, CircleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user_default_pic),
                    contentDescription = "Profile image of ${user.name}",
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Información del usuario
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "${user.name} ${user.lastName}",
                    style = MaterialTheme.typography.titleMedium,
                    color = AppColors.bodyText
                )
                Text(
                    text = user.email,
                    style = MaterialTheme.typography.bodySmall,
                    color = AppColors.bodyText
                )
                Text(
                    text = "ID: ${user.id}",
                    style = MaterialTheme.typography.labelSmall,
                    color = AppColors.bodyText
                )
                Text(
                    text = "Joined: ${user.registerDate.toDate().toString().dropLast(15)}",
                    style = MaterialTheme.typography.labelSmall,
                    color = AppColors.bodyText
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Rol a la derecha
            Text(
                text = user.role.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = AppColors.titleText
            )
        }
    }
}


@Preview (showBackground = true)
@Composable
fun UserCardPreview() {
    UserCard(user = testUser)
}