package com.nullPointerSociety.elfogon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nullPointerSociety.elfogon.ui.screens.HomeScreen
import com.nullPointerSociety.elfogon.ui.screens.MadeRecipesScreen
import com.nullPointerSociety.elfogon.ui.screens.SavedRecipesScreen
import com.nullPointerSociety.elfogon.ui.screens.UserProfileScreen


@Composable
fun AppNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = HomeScreenNav) {
        composable<HomeScreenNav> {
            HomeScreen {}
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