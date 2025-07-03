package com.nullPointerSociety.elfogon.ui.components.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nullPointerSociety.elfogon.data.model.user.UserReceptor
import com.nullPointerSociety.elfogon.ui.components.UserCard

@Composable
fun UsersList(users: List<UserReceptor>) {
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

