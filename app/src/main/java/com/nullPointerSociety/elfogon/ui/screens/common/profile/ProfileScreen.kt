package com.nullPointerSociety.elfogon.ui.screens.common.profile

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nullPointerSociety.elfogon.ui.components.CustomButton
import com.nullPointerSociety.elfogon.ui.components.profile.BasicInfo
import com.nullPointerSociety.elfogon.ui.components.profile.ProfileStatCard
import kotlinx.coroutines.delay

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    scrollState: LazyListState = LazyListState(0, 0),
) {
    val profileHomeViewModel: ProfileViewModel = viewModel(factory = ProfileViewModel.Factory)

    val savedOnes = profileHomeViewModel.savedRecipes.collectAsState().value
    val madeOnes = profileHomeViewModel.madeRecipes.collectAsState().value

    val userData = profileHomeViewModel.userData.collectAsState()

    // Mostrar pantalla de carga si aún no hay datos cargados (por ejemplo -1 como valor inicial)
    val isLoading = savedOnes < 0 && madeOnes < 0

    if (isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            CircularProgressIndicator()
        }
    } else {
        // Mostrar contenido completo si ya se cargaron los datos
        LazyColumn(
            state = scrollState,
            modifier = modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            contentPadding = PaddingValues(bottom = 125.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                BasicInfo(userData)
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ProfileStatCard("Saved recipes", count = savedOnes)
                    ProfileStatCard("Made recipes", count = madeOnes)
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
                CustomButton(
                    text = "Sign Out",
                    onClick = { profileHomeViewModel.logout() },
                )
            }
        }
    }
}
