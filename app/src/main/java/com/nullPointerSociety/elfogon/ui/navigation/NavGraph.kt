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
import com.nullPointerSociety.elfogon.ui.screens.auth.register.RegisterViewModel
import com.nullPointerSociety.elfogon.ui.screens.home.HomeScreen
import com.nullPointerSociety.elfogon.ui.screens.home.HomeViewModel
import com.nullPointerSociety.elfogon.ui.screens.profile.ProfileScreen
import com.nullPointerSociety.elfogon.ui.screens.profile.ProfileViewModel
import com.nullPointerSociety.elfogon.ui.screens.recipes.details.RecipeDetailsScreen
import com.nullPointerSociety.elfogon.ui.screens.recipes.details.RecipeDetailsViewModel
import com.nullPointerSociety.elfogon.ui.screens.recipes.made.MadeRecipesScreen
import com.nullPointerSociety.elfogon.ui.screens.recipes.saved.SavedRecipesScreen


@SuppressLint("ContextCastToActivity")
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    titleScreen: MutableState<String>,
    scrollState: LazyListState
) {
    //ViewModels
    //val spooncularViewModel: SpooncularViewModel = viewModel(factory = SpooncularViewModel.Factory)
    val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
    val registerViewModel: RegisterViewModel = viewModel(factory = RegisterViewModel.Factory)
    val homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
    val profileHomeViewModel: ProfileViewModel = viewModel(factory = ProfileViewModel.Factory)
    val detailsScreenViewModel: RecipeDetailsViewModel =
        viewModel(factory = RecipeDetailsViewModel.Factory)


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
            LoginScreen(navController, loginViewModel)
        }
        composable<SignUpScreenNav> {
            RegisterScreen(navController, registerViewModel)
        }
        composable<HomeScreenNav> {
            HomeScreen(
                onNavigateToFilters = { navController.navigate("filters") },
                homeViewModel = homeViewModel,
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
                viewModel = profileHomeViewModel,
                modifier = modifier,
                navController = navController,
                scrollState = scrollState
            )
        }

        composable<RecipeDetailsScreenNav> { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("id") ?: 0
            RecipeDetailsScreen(
                recipeId,
                detailsScreenViewModel,
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
