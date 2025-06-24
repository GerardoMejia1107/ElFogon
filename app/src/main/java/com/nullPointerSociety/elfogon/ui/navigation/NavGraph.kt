package com.nullPointerSociety.elfogon.ui.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nullPointerSociety.elfogon.data.repository.impl.AuthState
import com.nullPointerSociety.elfogon.ui.screens.auth.login.LoginScreen
import com.nullPointerSociety.elfogon.ui.screens.auth.login.LoginViewModel
import com.nullPointerSociety.elfogon.ui.screens.auth.register.RegisterScreen
import com.nullPointerSociety.elfogon.ui.screens.home.HomeScreen
import com.nullPointerSociety.elfogon.ui.screens.profile.ProfileScreen
import com.nullPointerSociety.elfogon.ui.screens.recipes.details.RecipeDetailsScreen
import com.nullPointerSociety.elfogon.ui.screens.recipes.made.MadeRecipesScreen
import com.nullPointerSociety.elfogon.ui.screens.recipes.saved.SavedRecipesScreen
import com.nullPointerSociety.elfogon.viewmodel.UserViewModel


@SuppressLint("ContextCastToActivity")
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    titleScreen: MutableState<String>,
    scrollState: LazyListState,
) {

    val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
    val userViewModel: UserViewModel = viewModel(factory = UserViewModel.Factory)


    //Solamente usado en el LaunchedEffect para redirigir al usuario si no está autenticado
    val authState = loginViewModel.authState.collectAsState()
    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> navController.navigate(LogInScreenNav)
            else -> Unit
        }
    }


    val onClickRecipe = { recipeId: Int ->
        navController.navigate(RecipeDetailsScreenNav(recipeId))
    }


    NavHost(navController = navController, startDestination = LogInScreenNav) {

        composable<LogInScreenNav> {
            LoginScreen(navController)
        }
        composable<SignUpScreenNav> {
            RegisterScreen(navController)
        }
        composable<HomeScreenNav> {
            HomeScreen(
                onNavigateToFilters = { navController.navigate("filters") },
                onRecipeClick = onClickRecipe,
                modifier,
                navController = navController,
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
                navController = navController,
                scrollState = scrollState
            )
        }

        composable<RecipeDetailsScreenNav> { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("id") ?: 0
            userViewModel.setRecipeIdSelected(recipeId.toString())

            RecipeDetailsScreen(
                recipeId,
                onBack = { navController.popBackStack() },
                modifier = modifier,
                titleScreen,
                scrollState

            )
        }

        /*composable("filters") {
            FilterScreen(viewModel = viewModel())
        }*/

    }

}
