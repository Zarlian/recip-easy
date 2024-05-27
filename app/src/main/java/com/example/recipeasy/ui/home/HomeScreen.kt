import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.recipeasy.ui.NavigationDestination
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.recipeasy.ui.api.APIUiState
import com.example.recipeasy.ui.api.APIViewModel
import com.example.recipeasy.ui.AppViewModelProvider
import com.example.recipeasy.ui.home.HomeViewModel

sealed class Page {
    object HOME : Page()
    object PANTRY : Page()
    object PROFILE : Page()
    object RECIPES : Page()
}

object HomeDestination : NavigationDestination {
    override val route = "home"
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun HomeScreen(
    navigateToShop: () -> Unit,
    navigateToFilter: () -> Unit,
    modifier: Modifier = Modifier,
    selectedPage: Page,
    onItemSelected: (Page) -> Unit,
    navigateToRecipeDetail: (String) -> Unit,
    navController: NavHostController,
    apiViewModel: APIViewModel = viewModel()

) {

    val apiUiState: APIUiState = apiViewModel.apiUiState

    LaunchedEffect(key1 = true) {
        apiViewModel.getRandomRecipes()
    }

    Scaffold(
        topBar = {
            MainTopBar(
                selectedPage = selectedPage,
                onItemSelected = onItemSelected,
                onSearchClicked = navigateToFilter,
                modifier = modifier,
                onShopClicked = navigateToShop,
                navController = navController,
            )
        }
    ) { innerPadding ->
        when (apiUiState) {
            is APIUiState.Success -> {
                val recipes = apiUiState.recipes
                RecipeCardList(
                    modifier = modifier
                        .padding(innerPadding)
                        .fillMaxWidth(),
                    recipes = recipes,
                    onItemClick = navigateToRecipeDetail,
                )
            }

            is APIUiState.Loading -> Text(
                text = "Loading recipes...",
                modifier = Modifier.padding(all = 10.dp),
                style = MaterialTheme.typography.bodyMedium
            )

            is APIUiState.Error -> Text(
                text = "Error!",
                modifier = Modifier.padding(all = 10.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}