package com.nullPointerSociety.elfogon.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.nullPointerSociety.elfogon.ui.navigation.LogInScreenNav
import com.nullPointerSociety.elfogon.viewmodel.AuthViewModel

@Composable
fun UserProfileScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel,
    navController: NavController
) {

    Column(modifier = modifier) {
        Button(onClick = {
            authViewModel.logout()
            navController.navigate(LogInScreenNav)
        }) {
            Text(text = "Sing Out")
        }
    }

}
