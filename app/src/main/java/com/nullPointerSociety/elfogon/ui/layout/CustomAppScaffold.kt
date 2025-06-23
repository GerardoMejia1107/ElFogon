package com.nullPointerSociety.elfogon.ui.layout

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SoupKitchen
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nullPointerSociety.elfogon.ui.navigation.AppNavGraph
import com.nullPointerSociety.elfogon.ui.navigation.HomeScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.LogInScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.MadeRecipesScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.ProfileScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.SavedRecipesScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.SignUpScreenNav


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun CustomScaffold(
) {
    val navController = rememberNavController()

    var titleScreen = rememberSaveable { mutableStateOf("") }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var scrollState = rememberLazyListState()
    var showTitleTopBar = remember {
        derivedStateOf {
            scrollState.firstVisibleItemIndex > 0 ||
                    scrollState.firstVisibleItemScrollOffset > 100
        }
    }


    fun onItemSelected(route: Any) {
        navController.navigate(route)
    }

    val hideBarsIn =
        listOf(LogInScreenNav::class.qualifiedName, SignUpScreenNav::class.qualifiedName)
    val isDetailsScreen = currentRoute?.contains("RecipeDetailsScreenNav") == true

    LaunchedEffect(currentRoute) {
        println("🌐 Current Route: $currentRoute")
    }

    Scaffold(
        contentWindowInsets = WindowInsets.systemBars,
        topBar = {
            if (currentRoute !in hideBarsIn) {
                CustomTopBar(
                    customTitle = if (showTitleTopBar.value) titleScreen.value else "",
                    onAction = {
                        navController.popBackStack(HomeScreenNav, inclusive = false)
                    },
                    showLogo = true,
                    currentRoute
                )
            }
        },
        bottomBar = {
            if (currentRoute !in hideBarsIn) {
                BottomNavigationBar(
                    selectedRoute = currentRoute,
                    onItemSelected = { routeObject ->
                        onItemSelected(routeObject)
                    }
                )
            }
        },
        floatingActionButton = {
            if (isDetailsScreen) {
                FloatingActionButton(
                    shape = RoundedCornerShape(30.dp),
                    onClick = {},
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.secondary
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                    ) {
                        Text(
                            text = "Cook this!",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            imageVector = Icons.Default.SoupKitchen,
                            contentDescription = "Cook this",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        AppNavGraph(
            navController = navController,
            Modifier.padding(innerPadding),
            titleScreen,
            scrollState
        )
    }
}

