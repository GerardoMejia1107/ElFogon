package com.nullPointerSociety.elfogon.ui.screens

import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Timestamp
import com.nullPointerSociety.elfogon.data.model.user.UserReceptor
import com.nullPointerSociety.elfogon.ui.components.UserCard

data class User(
    val name: String,
    val email: String
)

@Composable
fun UserListScreen(users: List<UserReceptor>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFBF9F5))
            .padding(vertical = 16.dp)
    ) {

        users.forEachIndexed { index, user ->
            UserCard(user = user)
            if (index < users.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    thickness = 1.dp,
                    color = Color(0xFFAAAAAA)
                )
            }
        }
    }
}


@Preview
@Composable
fun UserListPreview() {
    UserListScreen(
        users = listOf(
            UserReceptor(
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
            ),
            UserReceptor(
                id = "DEF789101",
                name = "Lucía",
                lastName = "Mendoza",
                email = "lucia.mendoza@example.com",
                profilePictureUrl = "https://cdn-icons-png.flaticon.com/512/194/194938.png",
                role = "client",
                customSavedRecipes = listOf("CUSTOM_0003"),
                savedRecipes = listOf("404", "505"),
                madeRecipes = listOf("404"),
                registerDate = Timestamp.now()
            ),
            UserReceptor(
                id = "GHI112233",
                name = "Carlos",
                lastName = "Vargas",
                email = "carlos.vargas@example.com",
                profilePictureUrl = "https://cdn-icons-png.flaticon.com/512/2922/2922522.png",
                role = "client",
                customSavedRecipes = emptyList(),
                savedRecipes = listOf("606", "707", "808"),
                madeRecipes = listOf("606"),
                registerDate = Timestamp.now()
            ),
            UserReceptor(
                id = "JKL445566",
                name = "Ana",
                lastName = "Lopez",
                email = "ana.lopez@example.com",
                profilePictureUrl = "https://cdn-icons-png.flaticon.com/512/2922/2922561.png",
                role = "admin",
                customSavedRecipes = listOf("CUSTOM_0004", "CUSTOM_0005"),
                savedRecipes = listOf("909"),
                madeRecipes = listOf("909"),
                registerDate = Timestamp.now()
            )))
}