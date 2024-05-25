package com.example.recipeasy.ui.pages

import MainTopBar
import Page
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.recipeasy.ui.NavigationDestination


object ProfileDestination : NavigationDestination {
    override val route = "profile"
}

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    selectedPage: Page,
    onItemSelected: (Page) -> Unit,
    navigateToFilter: () -> Unit,
    navigateToShop: () -> Unit,
    navController: NavHostController
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
    )
        { innerPadding ->
        Text(
            text = "nothing to see here",
            modifier = Modifier.padding(innerPadding)
        )

    }

}