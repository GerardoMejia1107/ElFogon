package com.nullPointerSociety.elfogon.ui.layout

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.nullPointerSociety.elfogon.ui.navigation.Routes

data class BottomNavItem(val label: String, val icon: ImageVector, val route: String)

val bottomNavItems = listOf(
    BottomNavItem("Home", Icons.Default.Home, Routes.HOME),
    BottomNavItem("Guardados", Icons.Default.Bookmark, Routes.SAVED_RECIPES),
    BottomNavItem("Preparados", Icons.Default.CheckCircle, Routes.MADE_RECIPES),
    BottomNavItem("Perfil", Icons.Default.Person, Routes.PROFILE),
)

@Composable
fun BottomNavigationBar(
    selectedItem: String = "now_playing",
    onItemSelected: (String) -> Unit
) {
    NavigationBar(
        modifier = Modifier.height(120.dp),
        containerColor = MaterialTheme.colorScheme.secondary,
    ) {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(indicatorColor = MaterialTheme.colorScheme.background),
                selected = selectedItem == item.route,
                onClick = { onItemSelected(item.route) },
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
            )
        }
    }
}