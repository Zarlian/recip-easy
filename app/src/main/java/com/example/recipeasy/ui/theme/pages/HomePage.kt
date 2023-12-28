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
    recipeArticles: List<RecipeArticle>
) {
    Column(modifier = modifier.fillMaxSize()) {
        Header(
            selectedPage = selectedPage,
            onItemSelected = onItemSelected,
            modifier = modifier
        )
        RecipeCardList(modifier = modifier.align(Alignment.CenterHorizontally ),recipeArticles = recipeArticles)
    }
}