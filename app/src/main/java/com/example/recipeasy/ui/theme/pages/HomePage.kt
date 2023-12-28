import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.recipeasy.Page

@Composable
fun HomePage(modifier: Modifier = Modifier, selectedPage: Page, onItemSelected: (Page) -> Unit) {
    Column(modifier = modifier.fillMaxSize()) {
        Header(
            selectedPage = selectedPage,
            onItemSelected = onItemSelected,
            modifier = modifier
        )
        RecipeCardList(modifier = modifier.align(Alignment.CenterHorizontally))
    }
}