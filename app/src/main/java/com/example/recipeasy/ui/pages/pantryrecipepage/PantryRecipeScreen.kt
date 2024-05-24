import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.recipeasy.ui.NavigationDestination


object PantryRecipeDestination : NavigationDestination {
    override val route = "recipes"
}

@Composable
fun PantryRecipeScreen(
    modifier: Modifier = Modifier,
    selectedPage: Page,
    onItemSelected: (Page) -> Unit,
    navigateToFilter: () -> Unit,
    navigateToShop: () -> Unit,
    navigateToRecipeDetail: (String) -> Unit,
    navController: NavHostController,
) {
    Scaffold(
        topBar = {
            MainTopBar(
                selectedPage = selectedPage,
                onItemSelected = onItemSelected,
                onSearchClicked = navigateToFilter,
                modifier = modifier,
                onShopClicked = navigateToShop,
                navController = navController
            )
        }
    ) { innerPadding ->
        PantryRecipePageText(modifier  = modifier.padding(innerPadding))
        RecipeCardList(
            modifier = modifier.padding(innerPadding),
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

