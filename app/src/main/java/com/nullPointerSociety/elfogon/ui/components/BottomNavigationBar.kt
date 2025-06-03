package com.nullPointerSociety.elfogon.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.unit.dp

data class BottomNavItem(val label: String, val icon: ImageVector)

val bottomNavItems = listOf(
    BottomNavItem("Inicio", Icons.Default.Home),
    BottomNavItem("Guardados", Icons.Default.Bookmark),
    BottomNavItem("Preparados", Icons.Default.CheckCircle),
    BottomNavItem("Perfil", Icons.Default.Person),
)

@Composable
fun BottomNavigationBar() {
    NavigationBar() {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = false,
                onClick = { /* no-op de momento */ },
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }
}