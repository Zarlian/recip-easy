import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipeasy.data.dataclasses.RecipeArticle

@Composable
fun PantryRecipePage(
    modifier: Modifier = Modifier,
    selectedPage: Page,
    onItemSelected: (Page) -> Unit,
    recipeArticles: List<RecipeArticle>,
    onSearchClicked: () -> Unit,
    onShopClicked: () -> Unit,
    navigateToRecipeDetail: (String) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        Header(
            selectedPage = selectedPage,
            onItemSelected = onItemSelected,
            modifier = modifier,
            onSearchClicked = onSearchClicked,
            onShopClicked = onShopClicked
        )
        PantryRecipePageText(modifier = modifier.align(Alignment.CenterHorizontally))
        RecipeCardList(
            modifier = modifier.align(Alignment.CenterHorizontally),
            recipes = emptyList(),
            onItemClick = navigateToRecipeDetail
            )
    }
}

@Composable
fun PantryRecipePageText(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.width(320.dp)
    ) {
        Text(
            text = "Recipes with your pantry ingredients",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary,
            modifier = modifier.padding(horizontal = 8.dp, vertical = 8.dp)
        )
    }
}

