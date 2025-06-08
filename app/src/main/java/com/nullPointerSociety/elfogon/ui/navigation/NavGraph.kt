package com.nullPointerSociety.elfogon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nullPointerSociety.elfogon.ui.screens.HomeScreen
import com.nullPointerSociety.elfogon.ui.screens.MadeRecipesScreen
import com.nullPointerSociety.elfogon.ui.screens.SavedRecipesScreen
import com.nullPointerSociety.elfogon.ui.screens.UserProfileScreen
import com.nullPointerSociety.elfogon.viewmodel.SpooncularViewModel


@Composable
fun AppNavGraph(navController: NavHostController) {
    val viewModel: SpooncularViewModel = viewModel()

    NavHost(navController = navController, startDestination = HomeScreenNav) {
        composable<HomeScreenNav> {
            HomeScreen({}, viewModel = viewModel)
        }
        composable<SavedRecipesScreenNav> {
            SavedRecipesScreen()
        }
        composable<MadeRecipesScreenNav> {
            MadeRecipesScreen()
        }
        composable<UserProfileScreenNav> {
            UserProfileScreen()
        }

    }
}