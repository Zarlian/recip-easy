package com.example.recipeasy.ui


import FilterDestination
import FilterPage
import HomePage
import RecipeDetailDestination
import RecipePage
import com.example.recipeasy.ui.pages.ShopPage
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipeasy.data.DataSource.defaultRecipeList
import com.example.recipeasy.data.DataSource.shop
import com.example.recipeasy.ui.pages.ShopDestination


@Composable
fun RecipEasyApp() {
    val navController = rememberNavController()
    RecipEasyNavHost(
        navController = navController
    )
}

@Composable
fun RecipEasyNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route
    ) {
            composable(route = HomeDestination.route) {
                HomePage(
                    navigateToShop = { navController.navigate(ShopDestination.route) },
                    navigateToFilter = { navController.navigate(FilterDestination.route) },
                    selectedPage = Page.HOME,
                    onItemSelected = { },
                    navigateToRecipeDetail = { navController.navigate("${RecipeDetailDestination.route}/${it}") }

                )
            }
            composable(route = ShopDestination.route) {
                ShopPage(
                    navigateBack = { navController.popBackStack() },
                    shop = shop
                )
            }

            composable(route = FilterDestination.route) {
                FilterPage(
                    navigateBack = { navController.popBackStack() },
                    recipeArticles = defaultRecipeList
                )
            }

            composable( route = RecipeDetailDestination.routeWithArgs,
                        arguments = listOf(navArgument(RecipeDetailDestination.recipeIdArg) {
                        type = NavType.StringType
                })
                )
            {
                val recipeId = it.arguments?.getString(RecipeDetailDestination.recipeIdArg)
                if (recipeId != null) {
                    RecipePage(
                        navigateBack = { navController.popBackStack() },
                        recipeId = recipeId
                    )
                }
            }

    }
}