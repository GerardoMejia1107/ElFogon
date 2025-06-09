package com.nullPointerSociety.elfogon.ui.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nullPointerSociety.elfogon.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    customTitle: String,
    onAction: (() -> Unit)? = null,
    showLogo: Boolean = true,
    selectedItem: MutableState<String>
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),
        title = {
            Row(
                verticalAlignment = Alignment.Bottom,
            ) {
                if (showLogo && selectedItem.value != "details_recipe") {
                    Image(
                        painter = painterResource(R.drawable.elfogon_logo),
                        contentDescription = "Del Fogon Logo",
                        modifier = Modifier.size(60.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = when (selectedItem.value) {
                        "home" -> "Del Fogon"
                        "saved_ones" -> "Saved Recipes"
                        "made_ones" -> "Cooked Recipes"
                        "profile" -> "Profile"
                        "details_recipe" -> customTitle
                        else -> "Del Fogon"
                    },
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        navigationIcon = {
            if (selectedItem.value == "details_recipe") {
                IconButton(onClick = { onAction?.invoke() }) {
                    Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = "Go back")
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


