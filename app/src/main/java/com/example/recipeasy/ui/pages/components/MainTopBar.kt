import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.recipeasy.R
import com.example.recipeasy.ui.pages.ProfileDestination
import com.example.recipeasy.ui.pages.pantrypage.pantry.PantryDestination

@Composable
fun AppTitle(@StringRes title: Int, modifier: Modifier = Modifier) {
    Row(horizontalArrangement = Arrangement.Center) {
        Text(
            text = stringResource(title),
            modifier = modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 32.sp),
            color = MaterialTheme.colorScheme.primary
        )
    }

}

@Composable
fun NavBarItems(
    @StringRes text: Int,
    selectedPage: Page,
    onClick: (Page) -> Unit,
    modifier: Modifier = Modifier
) {
    val page = when (text) {
        R.string.nav_home -> Page.HOME
        R.string.nav_recipes -> Page.RECIPES
        R.string.nav_pantry -> Page.PANTRY
        R.string.nav_profile -> Page.PROFILE
        else -> Page.HOME // Default to HOME
    }

    Text(
        text = stringResource(text),
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = if (selectedPage == page) FontWeight.ExtraBold else FontWeight.Normal,
        color = if (selectedPage == page) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
        modifier = modifier
            .padding(horizontal = 8.dp)
            .padding(vertical = 5.dp)
            .clickable {
                onClick(page)
            }
    )
}

@Composable
fun NavBar(
    selectedPage: Page,
    onItemSelected: (Page) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        NavBarItems(
            text = R.string.nav_home,
            selectedPage = selectedPage,
            onClick = { onItemSelected(Page.HOME) }
        )
        NavBarItems(
            text = R.string.nav_recipes,
            selectedPage = selectedPage,
            onClick = { onItemSelected(Page.RECIPES) }
        )
        NavBarItems(
            text = R.string.nav_pantry,
            selectedPage = selectedPage,
            onClick = { onItemSelected(Page.PANTRY) }
        )
        NavBarItems(
            text = R.string.nav_profile,
            selectedPage = selectedPage,
            onClick = { onItemSelected(Page.PROFILE) }
        )
    }
}

@Composable
fun HeaderIcons(
    modifier: Modifier = Modifier,
    onSearchClicked: () -> Unit,
    onShopClicked: () -> Unit) {

    Row(
        horizontalArrangement = Arrangement.End,
        modifier = modifier.offset(x = (-25).dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search",
            modifier = modifier.padding(6.dp)
                                .clickable(onClick = onSearchClicked),
            tint = MaterialTheme.colorScheme.secondary
        )
        Icon(
            imageVector = Icons.Filled.ShoppingCart,
            contentDescription = "Shopping Cart",
            modifier = modifier.padding(6.dp)
                                .clickable(onClick = onShopClicked),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}


@Composable
fun MainTopBar(
    modifier: Modifier = Modifier,
    selectedPage: Page,
    onItemSelected: (Page) -> Unit,
    onSearchClicked: () -> Unit,
    onShopClicked: () -> Unit,
    navController: NavHostController
) {
    Column(modifier.padding(bottom = 20.dp)) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            AppTitle(title = R.string.app_name)
            HeaderIcons(
                onSearchClicked = onSearchClicked,
                onShopClicked = onShopClicked,
            )
        }
        NavBar(
            selectedPage = selectedPage,
            onItemSelected = {
                onItemSelected(it)
                when (it) {
                    Page.HOME -> navController.navigate(HomeDestination.route)
                    Page.RECIPES -> navController.navigate(PantryRecipeDestination.route)
                    Page.PANTRY -> navController.navigate(PantryDestination.route)
                    Page.PROFILE -> navController.navigate(ProfileDestination.route)
                    else -> {}
                }
            },
            modifier = modifier.padding(start = 25.dp, end = 25.dp, bottom = 3.dp)
        )
        Divider(color = Color.Gray, thickness = 0.7.dp)
    }
}