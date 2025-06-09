package com.nullPointerSociety.elfogon.ui.layout

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.derivedStateOf
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

    var scrollState = rememberLazyListState()
    var showTitleTopBar = remember {
        derivedStateOf {
            scrollState.firstVisibleItemIndex > 0 || scrollState.firstVisibleItemScrollOffset > 100
        }
    }


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
                customTitle = if (showTitleTopBar.value) titleScreen.value else "",
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
                    Row(
                        modifier = Modifier.padding(10.dp),
                    ) {
                        Text(text = "Cook this!", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
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
            selectedItem,
            Modifier.padding(innerPadding),
            titleScreen,
            scrollState

        )
    }
}