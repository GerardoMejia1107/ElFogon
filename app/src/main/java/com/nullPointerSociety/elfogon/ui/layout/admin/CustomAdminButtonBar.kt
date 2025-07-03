package com.nullPointerSociety.elfogon.ui.layout.admin

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.nullPointerSociety.elfogon.ui.layout.client.BottomNavItem
import com.nullPointerSociety.elfogon.ui.navigation.DashboardScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.HomeScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.MadeRecipesScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.ProfileScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.SavedRecipesScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.UsersScreenNav


val bottomNavItems = listOf(
    BottomNavItem("Dashboard", Icons.Default.Dashboard, DashboardScreenNav),
    BottomNavItem("Users", Icons.Default.SupervisedUserCircle, UsersScreenNav),
    BottomNavItem("Registers", Icons.Default.AppRegistration, RegistrationsScreenNav),
    BottomNavItem("Profile", Icons.Default.Person, ProfileScreenNav),
)


@Composable
fun CustomAdminButtonBar(
) {


}