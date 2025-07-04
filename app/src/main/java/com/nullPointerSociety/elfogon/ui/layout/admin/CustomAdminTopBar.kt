package com.nullPointerSociety.elfogon.ui.layout.admin

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nullPointerSociety.elfogon.ui.components.Heading
import com.nullPointerSociety.elfogon.ui.navigation.DashboardScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.HomeScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.MadeRecipesScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.ProfileScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.RegistrationsScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.SavedRecipesScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.UsersScreenNav

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAdminTopBar(
    selectedRoute: String?,
) {
    CenterAlignedTopAppBar(

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        title = {
            val titleText = when (selectedRoute) {
                DashboardScreenNav::class.qualifiedName -> "Your Dashboard"
                UsersScreenNav::class.qualifiedName -> "Users on Your App"
                RegistrationsScreenNav::class.qualifiedName -> "New Registrations"
                ProfileScreenNav::class.qualifiedName -> "Your Profile"
                else -> "Del Fogon"
            }
            Heading(
                customTitle = titleText,
                showLogo = false
            )
        },
        navigationIcon = {
            {}
        },
        actions = {

        }
    )
}
