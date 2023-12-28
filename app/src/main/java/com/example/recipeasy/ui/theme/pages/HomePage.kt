import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.recipeasy.Page
import com.example.recipeasy.data.dataclasses.RecipeArticle

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    selectedPage: Page,
    onItemSelected: (Page) -> Unit,
    recipeArticles: List<RecipeArticle>,
    onSearchClicked: () -> Unit,
    onShopClicked: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        Header(
            selectedPage = selectedPage,
            onItemSelected = onItemSelected,
            onSearchClicked = onSearchClicked,
            onShopClicked = onShopClicked
        )
        RecipeCardList(modifier = modifier.align(Alignment.CenterHorizontally ),recipeArticles = recipeArticles)
    }
}