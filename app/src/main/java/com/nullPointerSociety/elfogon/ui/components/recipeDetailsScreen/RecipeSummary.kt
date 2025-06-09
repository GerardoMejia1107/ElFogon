package com.nullPointerSociety.elfogon.ui.components.recipeDetailsScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nullPointerSociety.elfogon.data.mapper.htmlToAnnotatedString
import com.nullPointerSociety.elfogon.ui.components.ExpandableText

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun RecipeSummary(summaryHtml: String?) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        ExpandableText(
            text = htmlToAnnotatedString(summaryHtml.orEmpty()).toString(),
            maxLines = 3
        )
    }
}