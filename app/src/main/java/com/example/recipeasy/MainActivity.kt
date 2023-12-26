package com.example.recipeasy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            RecipeasyTheme(dynamicColor = false) {
                var selectedPage by remember { mutableStateOf(Page.HOME) }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (selectedPage) {
                        Page.HOME -> HomePage(
                            selectedPage = selectedPage,
                            onItemSelected = { page ->
                                selectedPage = page
                            }
                        )
                        Page.RECIPES -> RecipePage(
                            selectedPage = selectedPage,
                            onItemSelected = { page ->
                                selectedPage = page
                            }
                        )
                        Page.PANTRY -> PantryPage(
                            selectedPage = selectedPage,
                            onItemSelected = { page ->
                                selectedPage = page
                            }
                        )
                        Page.PROFILE -> TODO() // Implement ProfilePage similarly
                    }
                }

            }
        }
    }
}

enum class Page {
    HOME, RECIPES, PANTRY, PROFILE
}

@Composable
fun AppTitle(@StringRes title: Int, modifier: Modifier = Modifier){
    Row(horizontalArrangement = Arrangement.Center){
        Text(
            text = stringResource(title),
            modifier = modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
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
        content = {
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
    )
}

@Composable
fun HeaderIcons(modifier: Modifier = Modifier){
    Row(horizontalArrangement = Arrangement.End,
        modifier = modifier.offset(x = (-25).dp)){
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search",
            modifier = modifier.padding( 6.dp),
            tint = MaterialTheme.colorScheme.secondary
        )
        Icon(
            imageVector = Icons.Filled.ShoppingCart,
            contentDescription = "Shopping Cart",
            modifier = modifier.padding( 6.dp),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}


@Composable
fun Header(
    modifier: Modifier = Modifier,
    selectedPage: Page,
    onItemSelected: (Page) -> Unit
) {
    Column(modifier.padding(bottom = 20.dp)) {
        Surface(
            modifier = modifier.fillMaxWidth().padding(top = 50.dp)
        ) {
            AppTitle(title = R.string.app_name)
            HeaderIcons()
        }
        NavBar(
            selectedPage = selectedPage,
            onItemSelected = onItemSelected,
            modifier = modifier.padding(start = 25.dp, end = 25.dp, bottom = 3.dp)
        )
        Divider(color = Color.Gray, thickness = 0.7.dp)
    }
}

@Composable
fun RecipeArticle(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier){
    Surface(
        shape = MaterialTheme.shapes.small,
        color = Color(0xFFEECED3),
        modifier = modifier
            .padding(vertical = 8.dp)
            .width(320.dp)
            .height(130.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(115.dp)
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Right,
                modifier = Modifier.padding(horizontal = 8.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun RecipeArticleMirror(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier){
    Surface(
        shape = MaterialTheme.shapes.small,
        color = Color(0xFFBEDEDF),
        modifier = modifier
            .padding(vertical = 8.dp)
            .width(320.dp)
            .height(130.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Right,
                modifier = Modifier.padding(horizontal = 8.dp),
                color = MaterialTheme.colorScheme.primary
            )
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(115.dp)
            )
        }
    }
}

@Composable
fun RecipeCardList(modifier: Modifier = Modifier) {

    LazyColumn(modifier = modifier,
               verticalArrangement = Arrangement.spacedBy(8.dp)){
        items(10) {
            RecipeArticle(
                text = R.string.article_recipe_1,
                drawable = R.drawable.plate_1
            )
            RecipeArticleMirror(
                drawable = R.drawable.plate_2,
                text = R.string.article_recipe_2)
        }
    }
}

@Composable
fun PantryButton(@StringRes text: Int, modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .border(1.dp, MaterialTheme.colorScheme.secondary)
            .fillMaxHeight(0.7f)
            .padding(horizontal = 10.dp)
    ){
        Column (
                horizontalAlignment = Alignment.CenterHorizontally

        ){
            PantryButtonText(text = "+", modifier = Modifier)
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
            PantryButtonText(text = "-", modifier = Modifier)
        }
    }
}

@Composable
private fun PantryButtonText(text: String, modifier: Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.secondary
    )
}


@Composable
fun PantryArticle(text: Int, drawable: Int, modifier: Modifier = Modifier) {
    Row (
        modifier = Modifier.width(320.dp)
                            .height(100.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            PantryImage(drawable)
            PantryText(text)
        }
        PantryButton(text = R.string.number_1)
    }
}

@Composable
private fun PantryText(text: Int) {
    Text(
        text = stringResource(text),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
private fun PantryImage(drawable: Int) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = Color(0xFFEECED3),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .width(130.dp)
            .height(100.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(115.dp)
            )
        }
    }
}

@Composable
fun PantryList(modifier: Modifier = Modifier) {

    LazyColumn(modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)){
        items(10) {
            PantryArticle(
                text = R.string.article_recipe_1,
                drawable = R.drawable.plate_1
            )
        }
    }
}


@Composable
fun HomePage(modifier: Modifier = Modifier, selectedPage: Page, onItemSelected: (Page) -> Unit) {
    Column(modifier = modifier.fillMaxSize()) {
        Header(
            selectedPage = selectedPage,
            onItemSelected = onItemSelected,
            modifier = modifier
        )
        RecipeCardList(modifier = modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
fun RecipePageText(modifier: Modifier = Modifier){
    Column( modifier = modifier.width(320.dp)
        ){
        Text(
            text = "Recipes with your pantry ingredients",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary,
            modifier = modifier.padding(horizontal = 8.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun RecipePage(modifier: Modifier = Modifier, selectedPage: Page, onItemSelected: (Page) -> Unit){
    Column(modifier = modifier.fillMaxSize()) {
        Header(
            selectedPage = selectedPage,
            onItemSelected = onItemSelected,
            modifier = modifier
        )
        RecipePageText(modifier = modifier.align(Alignment.CenterHorizontally))
        RecipeCardList(modifier = modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
fun PantryPage(modifier: Modifier = Modifier, selectedPage: Page, onItemSelected: (Page) -> Unit){
    Column(modifier = modifier.fillMaxSize()) {
        Header(
            selectedPage = selectedPage,
            onItemSelected = onItemSelected,
            modifier = modifier
        )
        PantryList(modifier = modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
fun SearchPage() {
    TODO("Not yet implemented")
}

@Composable
fun ShopPage() {
    TODO("Not yet implemented")
}


@Preview(showBackground = true)
@Composable
fun AppTitlePreview() {
    RecipeasyTheme {
        AppTitle(R.string.app_name)
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
fun RecipeCardListPreview() {
    RecipeasyTheme {
        RecipeCardList()
    }
}

@Preview(showBackground = true)
@Composable
fun SearchPagePreview() {
    RecipeasyTheme (dynamicColor = false){
        SearchPage()
    }
}

@Preview(showBackground = true)
@Composable
fun ShopPagePreview() {
    RecipeasyTheme (dynamicColor = false){
        ShopPage()
    }
}


