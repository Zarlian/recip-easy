package com.example.recipeasy.ui.pages

import Header
import Page
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recipeasy.ui.NavigationDestination


object ProfileDestination : NavigationDestination {
    override val route = "profile"
}

@Composable
fun ProfilePage(
    modifier: Modifier = Modifier,
    selectedPage: Page,
    onItemSelected: (Page) -> Unit,
    onSearchClicked: () -> Unit,
    onShopClicked: () -> Unit
) {
    Header(
        selectedPage = selectedPage,
        onItemSelected = onItemSelected,
        onSearchClicked = onSearchClicked,
        onShopClicked = onShopClicked
    )
}