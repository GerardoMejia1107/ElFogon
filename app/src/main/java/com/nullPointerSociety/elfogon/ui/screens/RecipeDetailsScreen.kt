package com.nullPointerSociety.elfogon.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.nullPointerSociety.elfogon.data.mapper.htmlToAnnotatedString
import com.nullPointerSociety.elfogon.ui.components.ExpandableText
import com.nullPointerSociety.elfogon.ui.layout.CustomTopBar
import com.nullPointerSociety.elfogon.viewmodel.SpooncularViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable

fun RecipeDetailsScreen(id: Int, viewModel: SpooncularViewModel = viewModel()) {
    val detailRecipe = viewModel.getRecipeById(id)

    Scaffold(
        topBar = {
            CustomTopBar(
                title = detailRecipe?.title.toString()
            )
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(bottom = 140.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)

        ) {

            /*text = htmlToAnnotatedString(detailRecipe?.summary.toString())*/

            item {
                ExpandableText(text = htmlToAnnotatedString(detailRecipe?.summary.toString()).toString(), 3)

                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Ready in ${10} minutes", style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.height(10.dp))
                Box(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            detailRecipe?.image
                        ),
                        contentDescription = detailRecipe?.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(10.dp))

                    )


                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Ingredients You Need!", style = MaterialTheme.typography.titleLarge)
            }

            items(detailRecipe?.extendedIngredients ?: emptyList()) { item ->
                Column() {
                    Text(
                        item.original.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 7.dp)
                    )
                }
            }


        }
    }
}
