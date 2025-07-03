package com.nullPointerSociety.elfogon.ui.layout.client

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
import androidx.compose.ui.unit.dp
import com.nullPointerSociety.elfogon.ui.components.Heading
import com.nullPointerSociety.elfogon.ui.navigation.HomeScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.MadeRecipesScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.ProfileScreenNav
import com.nullPointerSociety.elfogon.ui.navigation.SavedRecipesScreenNav


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    customTitle: String,
    onAction: (() -> Unit)? = null,
    showLogo: Boolean = true,
    selectedRoute: String?,
    previousRoute: String?,
    detailsAction: () -> Unit
) {
    val isDetails = selectedRoute?.contains("RecipeDetailsScreenNav") == true
    val maxHeight = if (isDetails) 115.dp else 200.dp

    val isFromSaved = previousRoute?.contains("SavedRecipesScreenNav") == true

    CenterAlignedTopAppBar(
        modifier = Modifier.heightIn(min = 115.dp, max = maxHeight),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        title = {
            val titleText = when {
                selectedRoute == HomeScreenNav::class.qualifiedName -> "Del Fogon"
                selectedRoute == SavedRecipesScreenNav::class.qualifiedName -> "Saved Recipes"
                selectedRoute == MadeRecipesScreenNav::class.qualifiedName -> "Cooked Recipes"
                selectedRoute == ProfileScreenNav::class.qualifiedName -> "Profile"
                isDetails -> customTitle
                else -> "Del Fogon"
            }

            Heading(
                customTitle = titleText,
                showLogo = showLogo && !isDetails
            )
        },
        navigationIcon = {
            if (isDetails) {
                IconButton(onClick = {
                    onAction?.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = "Go back"
                    )
                }
            }
        },
        actions = {
            if (isDetails && !isFromSaved) {
                IconButton(onClick = { detailsAction() }) {
                    Icon(
                        imageVector = Icons.Outlined.BookmarkAdd,
                        contentDescription = "Add to favorites"
                    )
                }
            }
        }
    )
}


