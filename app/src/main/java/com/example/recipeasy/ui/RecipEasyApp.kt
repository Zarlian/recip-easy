package com.example.recipeasy.ui


import FilterDestination
import FilterScreen
import HomeScreen
import com.example.recipeasy.ui.pages.pantrypage.pantry.PantryDestination
import PantryRecipeDestination
import PantryRecipeScreen
import com.example.recipeasy.ui.pages.pantrypage.pantry.PantryScreen
import RecipeDetailDestination
import RecipeScreen
import com.example.recipeasy.ui.pages.shoppage.ShopScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipeasy.data.DataSource.defaultRecipeList
import com.example.recipeasy.data.DataSource.pantryItems
import com.example.recipeasy.data.DataSource.shop
import com.example.recipeasy.ui.pages.ProfileDestination
import com.example.recipeasy.ui.pages.ProfileScreen
import com.example.recipeasy.ui.pages.pantrypage.entry.PantryEntryDestination
import com.example.recipeasy.ui.pages.pantrypage.entry.PantryEntryScreen
import com.example.recipeasy.ui.pages.shoppage.ShopDestination


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
            HomeScreen(
                navigateToShop = { navController.navigate(ShopDestination.route) },
                navigateToFilter = { navController.navigate(FilterDestination.route) },
                selectedPage = Page.HOME,
                onItemSelected = { },
                navigateToRecipeDetail = { navController.navigate("${RecipeDetailDestination.route}/${it}") },
                navController = navController

            )
        }
        composable(route = ShopDestination.route) {
            ShopScreen(
                navigateBack = { navController.popBackStack() },
                shop = shop
            )
        }

        composable(route = PantryDestination.route) {
            PantryScreen(
                selectedPage = Page.PANTRY,
                onItemSelected = { },
                pantry = pantryItems,
                navigateToFilter = { navController.navigate(FilterDestination.route) },
                navigateToShop = { navController.navigate(ShopDestination.route) },
                navController = navController,
                navigateToPantryEntry = { navController.navigate(PantryEntryDestination.route) }
            )
        }

        composable(route = PantryEntryDestination.route) {
            PantryEntryScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        composable(route = ProfileDestination.route) {
            ProfileScreen(
                navigateToShop = { navController.navigate(ShopDestination.route) },
                navigateToFilter = { navController.navigate(FilterDestination.route) },
                selectedPage = Page.HOME,
                onItemSelected = { },
                navController = navController
            )
        }

        composable(route = PantryRecipeDestination.route) {
            PantryRecipeScreen(
                selectedPage = Page.RECIPES,
                onItemSelected = { },
                navigateToFilter = { navController.navigate(FilterDestination.route) },
                navigateToShop = { navController.navigate(ShopDestination.route) },
                navigateToRecipeDetail = { navController.navigate("${RecipeDetailDestination.route}/${it}") },
                navController = navController
            )
        }

        composable(route = FilterDestination.route) {
            FilterScreen(
                navigateBack = { navController.popBackStack() },
                recipeArticles = defaultRecipeList
            )
        }

        composable(route = RecipeDetailDestination.routeWithArgs,
            arguments = listOf(navArgument(RecipeDetailDestination.recipeIdArg) {
                type = NavType.StringType
            })
        ) {
            val recipeId = it.arguments?.getString(RecipeDetailDestination.recipeIdArg)
            if (recipeId != null) {
                RecipeScreen(
                    navigateBack = { navController.popBackStack() },
                    recipeId = recipeId
                )
            }
        }
    }
}