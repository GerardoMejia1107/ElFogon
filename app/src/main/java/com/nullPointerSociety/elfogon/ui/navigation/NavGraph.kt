package com.nullPointerSociety.elfogon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nullPointerSociety.elfogon.ui.screens.HomeScreen
import com.nullPointerSociety.elfogon.ui.screens.MadeRecipesScreen
import com.nullPointerSociety.elfogon.ui.screens.RecipeDetailsScreen
import com.nullPointerSociety.elfogon.ui.screens.SavedRecipesScreen
import com.nullPointerSociety.elfogon.ui.screens.UserProfileScreen
import com.nullPointerSociety.elfogon.viewmodel.SpooncularViewModel


@Composable
fun AppNavGraph(navController: NavHostController) {
    val viewModel: SpooncularViewModel = viewModel()
    val onClickRecipe = { recipeId: Int ->
        navController.navigate(RecipeDetailsScreenNav(recipeId))
    }

    NavHost(navController = navController, startDestination = HomeScreenNav) {
        composable<HomeScreenNav> {
            HomeScreen({}, viewModel = viewModel, onRecipeClick = onClickRecipe)
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
        composable<RecipeDetailsScreenNav> { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("id") ?: 0
            RecipeDetailsScreen(recipeId, viewModel)
        }
    }

}
