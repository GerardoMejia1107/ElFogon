package com.nullPointerSociety.elfogon.ui.layout

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.nullPointerSociety.elfogon.ui.navigation.AppNavGraph
import com.nullPointerSociety.elfogon.ui.navigation.HomeScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.MadeRecipesScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.SavedRecipesScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.UserProfileScreenNav
import com.nullPointerSociety.elfogon.ui.theme.PastelGreenLight

@Composable
fun CustomScaffold(
) {
    val navController = rememberNavController()
    var titleScreen = rememberSaveable { mutableStateOf("Home") }
    var selectedItem = rememberSaveable { mutableStateOf("home") }

    fun onItemSelected(currentItem: String) {
        selectedItem.value = currentItem
        when (currentItem) {
            "home" -> navController.navigate(HomeScreenNav)
            "saved_ones" -> navController.navigate(SavedRecipesScreenNav)
            "made_ones" -> navController.navigate(MadeRecipesScreenNav)
            "profile" -> navController.navigate(UserProfileScreenNav)
            else -> navController.navigate(HomeScreenNav)
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedItem = selectedItem.value,
                onItemSelected = { onItemSelected(it) }
            )
        }
    ) { innerPadding ->
        AppNavGraph(navController = navController)
    }
}