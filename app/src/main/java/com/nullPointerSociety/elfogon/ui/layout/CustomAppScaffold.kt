package com.nullPointerSociety.elfogon.ui.layout

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nullPointerSociety.elfogon.ui.navigation.AppNavGraph
import com.nullPointerSociety.elfogon.ui.navigation.HomeScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.MadeRecipesScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.SavedRecipesScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.UserProfileScreenNav

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun CustomScaffold(
) {
    val navController = rememberNavController()
    var titleScreen = rememberSaveable { mutableStateOf("") }
    var selectedItem = rememberSaveable { mutableStateOf("Del Fogon") }
    val currentDestination = navController
        .currentBackStackEntryAsState().value?.destination?.route

    fun onItemSelected(currentItem: String) {
        selectedItem.value = currentItem
        when (currentItem) {
            "home" -> navController.navigate(HomeScreenNav)
            "saved_ones" -> navController.navigate(SavedRecipesScreenNav)
            "made_ones" -> navController.navigate(MadeRecipesScreenNav)
            "profile" -> navController.navigate(UserProfileScreenNav)
            else -> ""
        }
    }

    Scaffold(
        topBar = {
            CustomTopBar(
                customTitle = titleScreen.value,
                onAction = {
                    navController.navigate(HomeScreenNav)
                    selectedItem.value = ""
                },
                showLogo = true,
                selectedItem
            )
        },
        bottomBar = {
            BottomNavigationBar(
                selectedItem = selectedItem.value,
                onItemSelected = { onItemSelected(it) }
            )
        },
        floatingActionButton = {
            if (selectedItem.value == "details_recipe") {
                FloatingActionButton(
                    shape = RoundedCornerShape(30.dp),
                    onClick = {},
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.secondary
                ) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        imageVector = Icons.Filled.PlayCircle,
                        contentDescription = "Cook"
                    )
                }
            }
        }
    ) { innerPadding ->
        AppNavGraph(
            navController = navController,
            selectedItem,
            Modifier.padding(innerPadding),
            titleScreen

        )
    }
}