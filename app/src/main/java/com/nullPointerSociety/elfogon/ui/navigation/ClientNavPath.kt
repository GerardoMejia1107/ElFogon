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
import com.nullPointerSociety.elfogon.ui.screens.admin.DashboardScreen
import com.nullPointerSociety.elfogon.ui.screens.admin.DashboardViewModel
import com.nullPointerSociety.elfogon.ui.screens.auth.login.LoginScreen
import com.nullPointerSociety.elfogon.ui.screens.auth.login.LoginViewModel
import com.nullPointerSociety.elfogon.ui.screens.auth.register.RegisterScreen
import com.nullPointerSociety.elfogon.ui.screens.home.HomeScreen
import com.nullPointerSociety.elfogon.ui.screens.home.HomeViewModel
import com.nullPointerSociety.elfogon.ui.screens.profile.ProfileScreen
import com.nullPointerSociety.elfogon.ui.screens.recipes.details.RecipeDetailsScreen
import com.nullPointerSociety.elfogon.ui.screens.recipes.made.MadeRecipesScreen
import com.nullPointerSociety.elfogon.ui.screens.recipes.saved.SavedRecipesScreen
import com.nullPointerSociety.elfogon.viewmodel.UserViewModel


@SuppressLint("ContextCastToActivity")
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ClientNavPath(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    titleScreen: MutableState<String>,
    scrollState: LazyListState,
) {

    val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
    val userViewModel: UserViewModel = viewModel(factory = UserViewModel.Factory)
    val homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
    val dashboardViewModel: DashboardViewModel = viewModel(factory = DashboardViewModel.Factory)


    //Solamente usado en el LaunchedEffect para redirigir al usuario si no está autenticado
    val authState = loginViewModel.authState.collectAsState()
    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> navController.navigate(LogInScreenNav)
            else -> Unit
        }
    }


    val onClickRecipe = { recipeId: String, requester: String ->
        navController.navigate(RecipeDetailsScreenNav(recipeId, requester))
    }


    NavHost(navController = navController, startDestination = LogInScreenNav) {

        composable<LogInScreenNav> {
            LoginScreen(navController)
        }
        composable<SignUpScreenNav> {
            RegisterScreen(navController)
        }
        composable<HomeScreenNav> {
            val requester = HomeScreenNav::class.qualifiedName
            HomeScreen(
                homeViewModel = homeViewModel,
                onNavigateToFilters = { navController.navigate(SearchByFilterScreenNav) },
                onRecipeClick = { recipeID: String ->
                    onClickRecipe(recipeID, requester ?: "default")
                },
                modifier,
                navController = navController,
            )
        }
        composable<SavedRecipesScreenNav> {
            val requester = SavedRecipesScreenNav::class.qualifiedName
            SavedRecipesScreen(modifier = modifier, onRecipeClick = { recipeID: String ->
                onClickRecipe(recipeID, requester ?: "default")
            })
        }
        composable<MadeRecipesScreenNav> {
            MadeRecipesScreen()
        }
        composable<ProfileScreenNav> {
            ProfileScreen(
                modifier = modifier,
                scrollState = scrollState
            )
        }

        composable<RecipeDetailsScreenNav> { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("id") ?: ""
            val requester: String =
                backStackEntry.arguments?.getString("requestOrigin") ?: "default"
            userViewModel.setRecipeIdSelected(recipeId.toString())

            RecipeDetailsScreen(
                recipeId,
                requester,
                onBack = { navController.popBackStack() },
                modifier = modifier,
                titleScreen,
                scrollState

            )
        }


        //filerscreen
        /*composable<SearchByFilterScreenNav> {
            FilterScreen(viewModel = spooncularViewModel)
        }*/

        /*composable("filters") {
            FilterScreen(viewModel = viewModel())
        }*/

    }

}
