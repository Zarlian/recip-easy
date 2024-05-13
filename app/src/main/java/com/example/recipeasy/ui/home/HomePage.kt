import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.recipeasy.ui.NavigationDestination
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeasy.ui.api.APIUiState
import com.example.recipeasy.ui.api.APIViewModel
import com.example.recipeasy.ui.AppViewModelProvider
import com.example.recipeasy.ui.home.HomeViewModel

sealed class Page {
    object HOME : Page()
    object SHOP : Page()
    object PANTRY : Page()
    object PROFILE : Page()
    object FILTER : Page()
    object RECIPES : Page()
}

object HomeDestination : NavigationDestination {
    override val route = "home"
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun HomePage(
    navigateToShop: () -> Unit,
    navigateToFilter: () -> Unit,
    modifier: Modifier = Modifier,
    selectedPage: Page,
    onItemSelected: (Page) -> Unit,
    navigateToRecipeDetail: (String) -> Unit,

    viewModel : HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val apiViewModel: APIViewModel = viewModel()
    val apiUiState : APIUiState = apiViewModel.apiUiState
    val homeUiState by viewModel.homeUiState.collectAsState()

    LaunchedEffect(key1 = true) {
        apiViewModel.getRandomRecipes()
    }

    Column(modifier = modifier.fillMaxSize()) {
        Header(
            selectedPage = selectedPage,
            onItemSelected = onItemSelected,
            onSearchClicked = navigateToFilter,
            onShopClicked = navigateToShop
        )
        when (apiUiState) {
            is APIUiState.Success -> {
                val recipes = apiUiState.recipes
                RecipeCardList(
                    modifier = modifier.align(Alignment.CenterHorizontally ),
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