package com.nullPointerSociety.elfogon.ui.layout.admin

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nullPointerSociety.elfogon.ui.navigation.AdminNavPath

@Composable
fun CustomAdminScaffold(
) {
    val navController = rememberNavController()
    var titleScreen = rememberSaveable { mutableStateOf("") }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val previousRoute = navController.previousBackStackEntry?.destination?.route

    fun onItemSelected(route: Any) {
        navController.navigate(route)
    }

    val context = LocalContext.current


    Scaffold(
        topBar = { CustomAdminTopBar() },
        bottomBar = {
            CustomAdminButtonBar(selectedRoute = currentRoute, onItemSelected = { routeObject ->
                onItemSelected(routeObject)
            })
        },
    ) { innerPadding ->
        AdminNavPath(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            titleScreen = titleScreen,
            )
    }


}