package com.nullPointerSociety.elfogon.ui.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nullPointerSociety.elfogon.ui.screens.HomeScreen
import com.nullPointerSociety.elfogon.ui.screens.LoginScreen
import com.nullPointerSociety.elfogon.ui.screens.MadeRecipesScreen
import com.nullPointerSociety.elfogon.ui.screens.ProfileScreen
import com.nullPointerSociety.elfogon.ui.screens.RecipeDetailsScreen
import com.nullPointerSociety.elfogon.ui.screens.RegisterScreen
import com.nullPointerSociety.elfogon.ui.screens.SavedRecipesScreen
import com.nullPointerSociety.elfogon.viewmodel.AuthViewModel
import com.nullPointerSociety.elfogon.viewmodel.SpooncularViewModel


@SuppressLint("ContextCastToActivity")
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun AppNavGraph(
    navController: NavHostController,
    selectedItem: MutableState<String>,
    modifier: Modifier = Modifier,
    titleScreen: MutableState<String>,
    scrollState: LazyListState
) {
    val viewModel: SpooncularViewModel = viewModel()
    val authViewModel: AuthViewModel = viewModel()
    val authState = authViewModel.authState.collectAsState()


    val onClickRecipe = { recipeId: Int ->
        navController.navigate(RecipeDetailsScreenNav(recipeId))
    }





    NavHost(navController = navController, startDestination = LogInScreenNav) {

        composable<LogInScreenNav> {
            LoginScreen(navController, authViewModel, selectedItem)
        }
        composable<SignUpScreenNav> {
            RegisterScreen(navController, authViewModel, selectedItem)
        }
        composable<HomeScreenNav> {
            HomeScreen(
                {},
                viewModel = viewModel,
                onRecipeClick = onClickRecipe,
                modifier,
                authViewModel,
                navController = navController
            )
        }
        composable<SavedRecipesScreenNav> {
            SavedRecipesScreen()
        }
        composable<MadeRecipesScreenNav> {
            MadeRecipesScreen()
        }
        composable<ProfileScreenNav> {
            ProfileScreen(
                modifier = modifier,
                authViewModel = authViewModel,
                navController = navController,
                scrollState = scrollState
            )
        }
        composable<RecipeDetailsScreenNav> { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("id") ?: 0
            RecipeDetailsScreen(
                recipeId,
                viewModel,
                onBack = { navController.navigate(HomeScreenNav) },
                modifier = modifier,
                selectedItem,
                titleScreen,
                scrollState

            )
        }
    }

}
