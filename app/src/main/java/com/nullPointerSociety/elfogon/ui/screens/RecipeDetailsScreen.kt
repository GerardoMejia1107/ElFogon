package com.nullPointerSociety.elfogon.ui.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nullPointerSociety.elfogon.data.mapper.htmlToAnnotatedString
import com.nullPointerSociety.elfogon.ui.layout.CustomTopBar
import com.nullPointerSociety.elfogon.viewmodel.SpooncularViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable

fun RecipeDetailsScreen(id: Int, viewModel: SpooncularViewModel = viewModel()) {
    val scrollState = rememberScrollState()
    val detailRecipe = viewModel.getRecipeById(id)

    Scaffold(
        topBar = {
            CustomTopBar(
                title = detailRecipe?.title.toString()
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Surface(
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(

                    modifier = Modifier.background(MaterialTheme.colorScheme.background),
                    text = htmlToAnnotatedString(detailRecipe?.summary.toString())
                )
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun RecipeDetailsScreenPreview() {
    RecipeDetailsScreen(1, viewModel = SpooncularViewModel())
}