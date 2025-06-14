package com.nullPointerSociety.elfogon.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nullPointerSociety.elfogon.ui.components.RecipeCard
import com.nullPointerSociety.elfogon.ui.components.SearchBar
import com.nullPointerSociety.elfogon.viewmodel.AuthViewModel
import com.nullPointerSociety.elfogon.viewmodel.SpooncularViewModel

/*val recipeApis = listOf(
    RecipeApi(
        id = 664932,
        title = "Walnut Pesto",
        image = "https://img.spoonacular.com/recipes/664932-556x370.jpg",
        readyInMinutes = 45,
        servings = 12,
        summary = "This Walnut Pesto is a delicious twist on the classic. It's rich in healthy fats and perfect for pasta, sandwiches or as a dip. Preparation time is only 45 minutes. This recipe has been highly rated by home cooks for its simplicity and taste. Learn more about walnut-based recipes."
    ),
    RecipeApi(
        id = 649403,
        title = "Lebanese Kibbeh",
        image = "https://img.spoonacular.com/recipes/649403-556x370.jpg",
        readyInMinutes = 45,
        servings = 6,
        summary = "Lebanese Kibbeh is a traditional Middle Eastern dish that combines ground meat, bulgur wheat and spices into a savory treat. It can be fried, baked or served raw depending on your preference. This dish is gluten-free and perfect for festive occasions. Check more kibbeh recipes."
    ),
    RecipeApi(
        id = 637297,
        title = "Cauliflower Chickpea Stew",
        image = "https://img.spoonacular.com/recipes/637297-556x370.jpg",
        readyInMinutes = 45,
        servings = 4,
        summary = "Our Cauliflower Chickpea Stew offers a perfect combination of plant-based protein and fiber. Ideal for vegan and vegetarian diets. The stew is low in calories and packed with nutrients. Preparation takes less than an hour and it's great for meal prep. Find more plant-based stews."
    ),
    RecipeApi(
        id = 636732,
        title = "Cajun Lobster Pasta",
        image = "https://img.spoonacular.com/recipes/636732-556x370.jpg",
        readyInMinutes = 45,
        servings = 1,
        summary = "Cajun Lobster Pasta combines succulent lobster meat with a creamy Cajun-spiced sauce. This elegant dish is perfect for romantic dinners or special occasions. Its unique flavor profile comes from paprika, cayenne pepper and fresh herbs. Explore more seafood pasta dishes."
    ),
    RecipeApi(
        id = 647395,
        title = "Hot Artichoke Crab Dip",
        image = "https://img.spoonacular.com/recipes/647395-556x370.jpg",
        readyInMinutes = 45,
        servings = 4,
        summary = "Hot Artichoke Crab Dip is the ultimate party appetizer, combining creamy cheese, tender crab meat and flavorful artichokes. Served warm, it pairs wonderfully with crackers or bread. This recipe is quick to prepare and guaranteed to impress your guests. For more dip recipes."
    )
)*/

@Composable
fun HomeScreen(
    onNavigateToFilters: () -> Unit,
    viewModel: SpooncularViewModel = viewModel(),
    onRecipeClick: (Int) -> Unit = {},
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    authViewModel: AuthViewModel
) {
    val sampleRecipes = viewModel.recipes.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        SearchBar(
            onFilterClick = onNavigateToFilters,
            onBackClick = { /* sin acción por ahora */ }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Surface(
            modifier = Modifier.fillMaxWidth()

        ) {
            Text(
                text = "Descubre recetas deliciosas y fáciles de preparar",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
            )
        }


        Spacer(modifier = Modifier.height(16.dp))

        //sampleRecipes.value.isEmpty()
        if (sampleRecipes.value.isEmpty()) {

            CircularProgressIndicator()

        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(bottom = 140.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(sampleRecipes.value) { RecipeCard(it, onRecipeClick) }
            }

        }


    }
}

