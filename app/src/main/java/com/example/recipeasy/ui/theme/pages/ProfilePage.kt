import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recipeasy.Page

@Composable
fun ProfilePage(
    modifier: Modifier = Modifier,
    selectedPage: Page,
    onItemSelected: (Page) -> Unit,
    onSearchClicked: () -> Unit,
    onShopClicked: () -> Unit
) {
    Header(selectedPage = selectedPage, onItemSelected = onItemSelected , onSearchClicked = onSearchClicked, onShopClicked = onShopClicked)
}