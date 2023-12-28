package com.example.recipeasy

import FilterPage
import HomePage
import PantryPage
import PantryRecipePage
import ProfilePage
import RecipePage
import ShopPage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipeasy.data.filterResultList
import com.example.recipeasy.data.pantryItems
import com.example.recipeasy.data.pantryRecipeList
import com.example.recipeasy.ui.theme.RecipeasyTheme
import com.example.recipeasy.data.recipeArticlesList
import com.example.recipeasy.data.shop


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeasyTheme(dynamicColor = false) {
                var selectedPage by remember { mutableStateOf(Page.HOME) }
                var showFilterPage by remember { mutableStateOf(false) }
                var showShopPage by remember { mutableStateOf(false) }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (showFilterPage) {
                        FilterPage(
                            recipeArticles = filterResultList,
                            onBackClicked = {
                                showFilterPage = false
                            })
                    }
                    else if(showShopPage) {
                        ShopPage(shop = shop,
                            onBackClicked = {
                                showShopPage = false
                            })
                    } else{
                        when (selectedPage) {
                            Page.HOME -> HomePage(
                                selectedPage = selectedPage,
                                onItemSelected = { page ->
                                    selectedPage = page
                                },
                                recipeArticles = recipeArticlesList,
                                onSearchClicked = {
                                    showFilterPage = true
                                },
                                onShopClicked = {
                                    showShopPage = true
                                }
                            )

                            Page.RECIPES -> PantryRecipePage(
                                selectedPage = selectedPage,
                                onItemSelected = { page ->
                                    selectedPage = page
                                },
                                recipeArticles = pantryRecipeList,
                                onSearchClicked = {
                                    showFilterPage = true
                                },
                                onShopClicked = {
                                    showShopPage = true
                                }
                            )

                            Page.PANTRY -> PantryPage(
                                selectedPage = selectedPage,
                                onItemSelected = { page ->
                                    selectedPage = page
                                },
                                pantry = pantryItems,
                                onSearchClicked = {
                                    showFilterPage = true
                                },
                                onShopClicked = {
                                    showShopPage = true
                                }
                            )

                            Page.PROFILE -> ProfilePage(
                                selectedPage = selectedPage,
                                onItemSelected = { page ->
                                    selectedPage = page
                                },
                                onSearchClicked = {
                                    showFilterPage = true
                                },
                                onShopClicked = {
                                    showShopPage = true
                                }
                            )
                        }
                    }
                }

            }
        }
    }
}

enum class Page {
    HOME, RECIPES, PANTRY, PROFILE
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    var selectedPage by remember { mutableStateOf(Page.HOME) }
    RecipeasyTheme(dynamicColor = false) {
        HomePage(
            selectedPage = selectedPage,
            onItemSelected = { page ->
                selectedPage = page
            },
            recipeArticles = recipeArticlesList,
            onSearchClicked = {},
            onShopClicked = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PantryRecipePagePreview() {
    var selectedPage by remember { mutableStateOf(Page.HOME) }
    RecipeasyTheme(dynamicColor = false) {
        PantryRecipePage(
            selectedPage = selectedPage,
            onItemSelected = { page ->
                selectedPage = page
            },
            recipeArticles = pantryRecipeList,
            onSearchClicked = {},
            onShopClicked = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PantryPagePreview() {
    var selectedPage by remember { mutableStateOf(Page.HOME) }
    RecipeasyTheme(dynamicColor = false) {
        PantryPage(
            selectedPage = selectedPage,
            onItemSelected = { page ->
                selectedPage = page
            },
            pantry = pantryItems,
            onSearchClicked = {},
            onShopClicked = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchPagePreview() {
    RecipeasyTheme(dynamicColor = false) {
        FilterPage(
            recipeArticles = filterResultList,
            onBackClicked = {})
    }
}

@Preview(showBackground = true)
@Composable
fun ShopPagePreview() {
    RecipeasyTheme(dynamicColor = false) {
        ShopPage(shop = shop,
            onBackClicked = {})
    }
}

@Preview(showBackground = true)
@Composable
fun RecipePagePreview() {
    RecipeasyTheme(dynamicColor = false) {
        RecipePage(
            onBackClicked = {},
            recipeArticle = recipeArticlesList[0]
        )
    }
}




