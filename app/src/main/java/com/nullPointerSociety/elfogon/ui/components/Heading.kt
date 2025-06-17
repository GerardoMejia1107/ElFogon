package com.nullPointerSociety.elfogon.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nullPointerSociety.elfogon.R
import com.nullPointerSociety.elfogon.ui.theme.AppColors

@Composable
fun Heading(customTitle: String?, showLogo: Boolean = true) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center,
    ) {
        if (showLogo) {
            Image(
                painter = painterResource(R.drawable.del_fogon),
                contentDescription = "Del Fogon Logo",
                modifier = Modifier
                    .sizeIn(
                        maxWidth = 80.dp,
                        maxHeight = 80.dp,
                        minWidth = 50.dp,
                        minHeight = 50.dp
                    )
                    .padding(bottom = 10.dp)

            )
        }
        Spacer(Modifier.width(10.dp))
        Text(
            text = customTitle.toString(),
            color = AppColors.titleText,
            style = MaterialTheme.typography.displayMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}