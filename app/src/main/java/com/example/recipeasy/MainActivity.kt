package com.example.recipeasy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeasy.ui.theme.RecipeasyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeasyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppTitle("RecipEasy")
                }
            }
        }
    }
}

@Composable
fun AppTitle(title: String, modifier: Modifier = Modifier){
    Text(
        text = title,
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center, // Aligns text horizontally
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun NavBarItems(@StringRes text: Int, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(text),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.secondary,
        modifier = modifier
            .padding(horizontal = 8.dp)
            .padding(vertical = 5.dp)
    )
}

@Composable
fun NavBar(modifier: Modifier = Modifier){
    Row(modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        content = {
            NavBarItems(text = R.string.nav_home)
            NavBarItems(text = R.string.nav_recipes)
            NavBarItems(text = R.string.nav_pantry)
            NavBarItems(text = R.string.nav_profile)
        }
    )
}

@Composable
fun HeaderIcons(modifier: Modifier = Modifier){
    Row(){
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search",
            modifier = modifier.padding( 6.dp)
        )
        Icon(
            imageVector = Icons.Filled.ShoppingCart,
            contentDescription = "Shopping Cart",
            modifier = modifier.padding( 6.dp)
        )
    }
}

@Composable
fun Header(modifier: Modifier = Modifier){
    Column(
    ) {
        Box(
            modifier = Modifier.fillMaxWidth() // Make the row take the full width
        ) {
            AppTitle(title = "RecipEasy")
            HeaderIcons()
        }
        NavBar()
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun RecipeArticle(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier){
    Surface(
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp)
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Right,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
fun recipeCardList(modifier: Modifier = Modifier){

}

@Composable
fun homePage(modifier: Modifier = Modifier){

}



@Preview(showBackground = true)
@Composable
fun AppTitlePreview() {
    RecipeasyTheme {
        AppTitle("RecipEasy")
    }
}

@Preview(showBackground = true)
@Composable
fun NavBarPreview() {
    RecipeasyTheme {
        NavBar()
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderIconsPreview() {
    RecipeasyTheme {
        HeaderIcons()
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    RecipeasyTheme {
        Header()
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeArticlePreview() {
    RecipeasyTheme {
        RecipeArticle(
            text=R.string.article_recipe_1,
            drawable = R.drawable.plate_1)
    }
}

@Preview(showBackground = true)
@Composable
fun recipeCardListPreview() {
    RecipeasyTheme {
        recipeCardList()
    }
}

@Preview(showBackground = true)
@Composable
fun homePagePreview() {
    RecipeasyTheme {
        homePage()
    }
}