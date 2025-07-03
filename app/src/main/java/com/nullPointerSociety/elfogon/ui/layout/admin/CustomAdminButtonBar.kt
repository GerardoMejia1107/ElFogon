package com.nullPointerSociety.elfogon.ui.layout.admin

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nullPointerSociety.elfogon.ui.layout.client.BottomNavItem
import com.nullPointerSociety.elfogon.ui.navigation.DashboardScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.HomeScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.MadeRecipesScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.ProfileScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.RegistrationsScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.SavedRecipesScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.UsersScreenNav


val bottomNavItems = listOf(
    BottomNavItem("Dashboard", Icons.Default.Dashboard, DashboardScreenNav),
    BottomNavItem("Users", Icons.Default.SupervisedUserCircle, UsersScreenNav),
    BottomNavItem("Registers", Icons.Default.TrendingUp, RegistrationsScreenNav),
    BottomNavItem("Profile", Icons.Default.Person, ProfileScreenNav),
)


@Composable
fun CustomAdminButtonBar(
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

@Preview
@Composable
fun CustomAdminButtonBarPreview() {
    CustomAdminButtonBar(
        selectedRoute = HomeScreenNav::class.qualifiedName,
        onItemSelected = {}
    )
}