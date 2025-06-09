package com.nullPointerSociety.elfogon.ui.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nullPointerSociety.elfogon.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    title: String,
    onAction: (() -> Unit)? = null,
    showLogo: Boolean = true
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),
        title = {
            Row(
                verticalAlignment = Alignment.Bottom,
            ) {
                if (showLogo) {
                    Image(
                        painter = painterResource(R.drawable.elfogon_logo),
                        contentDescription = "Del Fogon Logo",
                        modifier = Modifier.size(60.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,

                    )
            }
        },
        navigationIcon = {
            onAction?.let {
                IconButton(onClick = { onAction() }) {
                    Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = "Go back")

                }
            }
        }
    )

}

