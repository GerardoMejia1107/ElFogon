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
import com.nullPointerSociety.elfogon.ui.navigation.HomeScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.MadeRecipesScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.ProfileScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.SavedRecipesScreenNav

data class BottomNavItem(val label: String, val icon: ImageVector, val destination: Any)

val bottomNavItems = listOf(
    BottomNavItem("Home", Icons.Default.Home, HomeScreenNav),
    BottomNavItem("Guardados", Icons.Default.Bookmark, SavedRecipesScreenNav),
    BottomNavItem("Preparados", Icons.Default.CheckCircle, MadeRecipesScreenNav),
    BottomNavItem("Perfil", Icons.Default.Person, ProfileScreenNav),
)

@Composable
fun BottomNavigationBar(
    selectedRoute: String?,
    onItemSelected: (Any) -> Unit
) {
    NavigationBar(
        modifier = Modifier.height(120.dp),
        containerColor = MaterialTheme.colorScheme.secondary
    ) {
        bottomNavItems.forEach { item ->
            val itemRoute = item.destination::class.qualifiedName
            NavigationBarItem(
                selected = itemRoute == selectedRoute,
                onClick = { onItemSelected(item.destination) },
                icon = { Icon(item.icon, item.label) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.background
                )
            )
        }
    }
}