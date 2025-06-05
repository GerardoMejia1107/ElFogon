package com.nullPointerSociety.elfogon.ui.layout

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(val label: String, val icon: ImageVector, val route: String)

val bottomNavItems = listOf(
    BottomNavItem("Home", Icons.Default.Home, "home"),
    BottomNavItem("Guardados", Icons.Default.Bookmark, "saved_ones"),
    BottomNavItem("Preparados", Icons.Default.CheckCircle, "made_ones"),
    BottomNavItem("Perfil", Icons.Default.Person, "profile"),
)

@Composable
fun BottomNavigationBar(
    selectedItem: String = "now_playing",
    onItemSelected: (String) -> Unit
) {
    NavigationBar() {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = selectedItem == item.route,
                onClick = { onItemSelected(item.route) },
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },

                )
        }
    }
}