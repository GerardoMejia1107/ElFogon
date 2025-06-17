package com.nullPointerSociety.elfogon.ui.layout

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
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nullPointerSociety.elfogon.ui.components.Heading
import com.nullPointerSociety.elfogon.ui.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    customTitle: String,
    onAction: (() -> Unit)? = null,
    showLogo: Boolean = true,
    selectedItem: MutableState<String>
) {
    val topBarHeight = if (selectedItem.value != Routes.DETAILS_RECIPE) {
        200.dp
    } else {
        115.dp
    }
    CenterAlignedTopAppBar(

        modifier = Modifier.heightIn(min = 115.dp, max = topBarHeight),
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),
        title = {
            val titleText = when (selectedItem.value) {
                Routes.HOME -> "Del Fogon"
                Routes.SAVED_RECIPES -> "Saved Recipes"
                Routes.MADE_RECIPES -> "Cooked Recipes"
                Routes.PROFILE -> "Profile"
                Routes.DETAILS_RECIPE -> customTitle
                else -> "Del Fogon"
            }
            Heading(
                customTitle = titleText,
                showLogo = showLogo && selectedItem.value != Routes.DETAILS_RECIPE,
            )
        },
        navigationIcon = {
            if (selectedItem.value == Routes.DETAILS_RECIPE) {
                IconButton(onClick = { onAction?.invoke() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = "Go back"
                    )
                }
            }
        },
        actions = {
            if (selectedItem.value == Routes.DETAILS_RECIPE) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Outlined.BookmarkAdd,
                        contentDescription = "Add to favorites or saved",
                    )
                }
            }
        }


    )
}


