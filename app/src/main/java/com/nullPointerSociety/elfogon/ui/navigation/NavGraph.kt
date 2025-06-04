package com.nullPointerSociety.elfogon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nullPointerSociety.elfogon.ui.screen.search.FilterScreen
import com.nullPointerSociety.elfogon.ui.screen.search.SearchScreen

object Routes {
    const val Search = "search"
    const val Filters = "filters"
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Search) {
        composable(Routes.Search) {
            SearchScreen(onNavigateToFilters = {
                navController.navigate(Routes.Filters)
            })
        }
        composable(Routes.Filters) {
            FilterScreen()
        }
    }
}