package com.nullPointerSociety.elfogon.ui.layout

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    customTitle: String,
    onAction: (() -> Unit)? = null,
    showLogo: Boolean = true,
    selectedItem: MutableState<String>
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.heightIn(150.dp, 200.dp),
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),
        title = {
            val titleText = when (selectedItem.value) {
                "home" -> "Del Fogon"
                "saved_ones" -> "Saved Recipes"
                "made_ones" -> "Cooked Recipes"
                "profile" -> "Profile"
                "details_recipe" -> customTitle
                else -> "Del Fogon"
            }
            Heading(
                customTitle = titleText,
                showLogo = showLogo && selectedItem.value != "details_recipe"
            )
        },
        navigationIcon = {
            if (selectedItem.value == "details_recipe") {
                IconButton(onClick = { onAction?.invoke() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = "Go back"
                    )
                }
            }
        },
        actions = {
            if (selectedItem.value == "details_recipe") {
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


