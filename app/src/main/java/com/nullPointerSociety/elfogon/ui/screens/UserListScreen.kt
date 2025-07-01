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

data class User(
    val name: String,
    val email: String
)

@Composable
fun UserListScreen(users: List<User>, onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFBF9F5))
            .padding(vertical = 16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .padding(start = 16.dp, bottom = 16.dp)
                .size(28.dp)
                .clickable { onBackClick() },
            tint = Color.Black
        )

        users.forEachIndexed { index, user ->
            UserRow(user = user, onClick = {  })
            if (index < users.lastIndex) {
                Divider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = Color(0xFFAAAAAA),
                    thickness = 1.dp
                )
            }
        }
    }
}

@Composable
fun UserRow(user: User, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF4A5E4D)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Person"
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = user.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = user.email,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
        }
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Go to user details",
            tint = Color.Black,
            modifier = Modifier.size(28.dp)
        )
    }
}

@Preview
@Composable
fun UserListPreview() {
    UserListScreen(
        users = listOf(
            User("José Armando Cardoza Sandoval", "00000@gmail.com"),
            User("José Armando Cardoza Sandoval", "00000@gmail.com"),
            User("José Armando Cardoza Sandoval", "00000@gmail.com"),
            User("José Armando Cardoza Sandoval", "00000@gmail.com")
        ),
        onBackClick = {})
}