package com.nullPointerSociety.elfogon.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExpandableText(
    text: String,
    maxLines: Int = 3
) {
    var label by remember { mutableStateOf("See more") }
    var expanded by remember { mutableStateOf(false) } //saber si esta expandido
    var isTextOverflowing by remember { mutableStateOf(false) } //saber si el texto se desborda

    Box() {
        Text(
            lineHeight = 24.sp,
            text = text,
            maxLines = if (expanded) Int.MAX_VALUE else maxLines,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { textLayoutResult ->
                isTextOverflowing = textLayoutResult.hasVisualOverflow
            },
            modifier = Modifier
                .clickable(
                    onClick = {
                        label = if (expanded) "See more" else "See less"
                        expanded = !expanded
                    }
                )
                .padding(top = 10.dp)
        )

    }
}