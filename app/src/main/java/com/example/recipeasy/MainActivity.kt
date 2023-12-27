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
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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

                        Page.RECIPES -> PantryRecipePage(
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
fun AppTitle(@StringRes title: Int, modifier: Modifier = Modifier) {
    Row(horizontalArrangement = Arrangement.Center) {
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
fun HeaderIcons(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = modifier.offset(x = (-25).dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search",
            modifier = modifier.padding(6.dp),
            tint = MaterialTheme.colorScheme.secondary
        )
        Icon(
            imageVector = Icons.Filled.ShoppingCart,
            contentDescription = "Shopping Cart",
            modifier = modifier.padding(6.dp),
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
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
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
    modifier: Modifier = Modifier
) {
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
    modifier: Modifier = Modifier
) {
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

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(10) {
            RecipeArticle(
                text = R.string.article_recipe_1,
                drawable = R.drawable.plate_1
            )
            RecipeArticleMirror(
                drawable = R.drawable.plate_2,
                text = R.string.article_recipe_2
            )
        }
    }
}

@Composable
fun PantryButton(@StringRes text: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .border(1.dp, MaterialTheme.colorScheme.secondary)
            .fillMaxHeight(0.7f)
            .padding(horizontal = 10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
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
private fun PantryText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
private fun PantryImage(drawable: Int, color: Long) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = Color(color),
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
    val ingredientsStrings = listOf(
        "Beans",
        "Chicken Breast",
        "Eggs",
        "Honey",
        "Onions",
        "Tagliatelle"
    )
    val ingredientDrawableMap = mapOf(
        "Beans" to R.drawable.beans,
        "Chicken Breast" to R.drawable.chicken_breast,
        "Eggs" to R.drawable.eggs,
        "Honey" to R.drawable.honey,
        "Onions" to R.drawable.onions,
        "Tagliatelle" to R.drawable.tagliatelle
    )

    val colorList = listOf(
        0xFFEECED3,
        0xFFBEDEDF,
        0xFFF7CE94,
        0xFFF7E7A9
    )
    var colorIndex = 0

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(25) { index ->
            PantryArticle(
                text = ingredientsStrings[index % ingredientsStrings.size],
                drawable = ingredientDrawableMap[ingredientsStrings[index % ingredientsStrings.size]]
                    ?: R.drawable.default_image,
                color = colorList[colorIndex % colorList.size]
            )
            colorIndex++
        }
    }
    //TODO make this button work
    FloatingActionButton(
        onClick = { /*TODO*/ },
    ) {
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}

@Composable
fun PantryArticle(text: String, drawable: Int, color: Long, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .width(320.dp)
            .height(100.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            PantryImage(drawable, color)
            PantryText(text)
        }
        PantryButton(text = R.string.number_1)
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
fun PantryRecipePageText(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.width(320.dp)
    ) {
        Text(
            text = "Recipes with your pantry ingredients",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary,
            modifier = modifier.padding(horizontal = 8.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun PantryRecipePage(
    modifier: Modifier = Modifier,
    selectedPage: Page,
    onItemSelected: (Page) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        Header(
            selectedPage = selectedPage,
            onItemSelected = onItemSelected,
            modifier = modifier
        )
        PantryRecipePageText(modifier = modifier.align(Alignment.CenterHorizontally))
        RecipeCardList(modifier = modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
fun PantryPage(modifier: Modifier = Modifier, selectedPage: Page, onItemSelected: (Page) -> Unit) {
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
fun FilterPage() {
    var choice by remember { mutableStateOf("") }

    Column {
        SecondHeader(title = "Result", subtitle = "You have 3 items")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Filter() { updatedChoice ->
                choice = updatedChoice // Update the choice in FilterPage
            }
            FilterBy(choice = choice)
            Sort()
        }
        FilterResults()

    }


}

@Composable
fun FilterBy(choice: String) {
    var expandedFilter by remember { mutableStateOf(false) }

    if(choice == "ONLY" || choice == "NO"){
        Box {
            Button(
                onClick = { expandedFilter = !expandedFilter },
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.secondary
                ),

                ) {
                Text("________________")
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "Dropdown")
            }

            DropdownMenu(
                expanded = expandedFilter,
                onDismissRequest = { expandedFilter = false },
                content = {

                    DropdownMenuItem(
                        onClick = { /* TODO */ },
                        text = { Text("Chicken") },
                        leadingIcon = { Checkbox(checked = false, onCheckedChange = {  }) }
                    )
                    DropdownMenuItem(
                        onClick = { /* TODO */ },
                        text = { Text("Eggs") },
                        leadingIcon = { Checkbox(checked = false, onCheckedChange = {  }) }
                    )
                    DropdownMenuItem(
                        onClick = { /* TODO */ },
                        text = { Text("Pasta") },
                        leadingIcon = { Checkbox(checked = false, onCheckedChange = {  }) }
                    )
                    DropdownMenuItem(
                        onClick = { /* TODO */ },
                        text = { Text("Beef") },
                        leadingIcon = { Checkbox(checked = false, onCheckedChange = {  }) }
                    )
                    DropdownMenuItem(
                        onClick = { /* TODO */ },
                        text = { Text("Milk") },
                        leadingIcon = { Checkbox(checked = false, onCheckedChange = {  }) }
                    )
                    DropdownMenuItem(
                        onClick = { /* TODO */ },
                        text = { Text("Cheese") },
                        leadingIcon = { Checkbox(checked = false, onCheckedChange = {  }) }
                    )

                    DropdownMenuItem(
                        onClick = { expandedFilter = false },
                        text = { Text("Done") },
                    )

                }
            )
        }
    }
}

@Composable
fun FilterResults() {
    LazyColumn() {
        items(3) {
            FilterResult()
        }
    }
}

@Composable
fun FilterResult() {
}

@Composable
fun Filter(onChoiceSelected: (String) -> Unit) {
    var expandedFilter by remember { mutableStateOf(false) }

    var choice by remember { mutableStateOf("FILTER") }

    Box {
        Button(
            onClick = { expandedFilter = !expandedFilter },
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text(choice)
        }

        // DropdownMenu tied to the 'expanded' state
        DropdownMenu(
            expanded = expandedFilter,
            onDismissRequest = { expandedFilter = false },
            content = {
                DropdownMenuItem(onClick = { onChoiceSelected("ONLY"); expandedFilter = false ; choice="ONLY" }, text = { Text("ONLY") })
                DropdownMenuItem(onClick = { onChoiceSelected("NO"); expandedFilter = false ; choice="NO"}, text = { Text("NO") })
                DropdownMenuItem(onClick = { onChoiceSelected(""); expandedFilter = false ; choice="FILTER"}, text = { Text("CANCEL") })
            }
        )
    }
}

@Composable
private fun Sort() {
    var expandedSort by remember { mutableStateOf(false) }

    Box {
        Button(
            onClick = { expandedSort = !expandedSort },
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.secondary
            ),

            ) {
            Text("SORT")
        }

        DropdownMenu(
            expanded = expandedSort,
            onDismissRequest = { expandedSort = false },
            content = {
                DropdownMenuItem(onClick = { /* TODO */ }, text = { Text("ALPHABETIC") })
                DropdownMenuItem(onClick = { /* TODO */ }, text = { Text("CALORIES") })
                DropdownMenuItem(onClick = { /* TODO */ }, text = { Text("TIME") })
            }
        )
    }
}


@Composable
fun ShopPage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SecondHeader(title = "Shopping List")
        ShopIcons()
        ShopArticles()
    }
}

@Composable
fun ShopIcons() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.width(290.dp)
    ) {
        ShopIcon()
        ShopIcon()
        ShopIcon()
        ShopIcon()
    }
}

@Composable
fun ShopIcon() {
    Icon(
        imageVector = Icons.Filled.ShoppingCart,
        contentDescription = "Shopping Cart",
        modifier = Modifier.padding(6.dp)
    )
}

@Composable
fun SecondHeader(title: String, subtitle: String = "") {
    //TODO text need to be in the middle, icon to the left
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp, bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row() {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Shopping Cart",
                modifier = Modifier.padding(6.dp)
            )
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        Divider()
    }
}

@Composable
fun ShopArticles() {
    LazyColumn() {
        items(2) {
            ShopArticle()
        }
    }
}

@Composable
fun ShopItem(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.width(320.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = true,
                onCheckedChange = { /*TODO*/ },
                modifier = Modifier.padding(6.dp)
            )
            Column {
                Text(
                    text = "6 Spare ribs",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Spare ribs with fried potatoes",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }

        Icon(
            imageVector = Icons.Outlined.Edit,
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = "Shopping Cart",
            modifier = Modifier
                .padding(6.dp)
                .size(24.dp)
        )
    }
}

@Composable
fun ShopArticle(modifier: Modifier = Modifier) {
    Column {
        ShopTitle()
        Surface(
            shape = MaterialTheme.shapes.small,
            color = Color(0xFFEECED3),
            modifier = modifier
                .padding(vertical = 8.dp)
                .width(320.dp)
        ) {
            Column {
                ShopItem()
                ShopItem()
                ShopItem()
                ShopItem()
            }
        }
    }
}

@Composable
fun ShopTitle(modifier: Modifier = Modifier) {
    Text(
        text = "Meat",
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.secondary
    )
}


@Composable
fun RecipePage() {
    Column {
        SecondHeader("Chicken with vegetables")
        Recipe()
    }
}

@Composable
fun RecipeIcons() {
    val icons = listOf(
        Icons.Filled.AccountBox,
        Icons.Filled.Delete,
        Icons.Filled.ShoppingCart,
        Icons.Filled.ShoppingCart
    )

    val iconTitle = listOf(
        "Time",
        "Difficulty",
        "Servings",
        "Add to\nshop"
    )

    val iconText = listOf(
        "30 min",
        "Easy",
        "4",
        ""
    )
    Row(
        modifier = Modifier.width(320.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (i in 0..3) {
            Column {
                RecipeIcon(imageVector = icons[i], title = iconTitle[i], text = iconText[i])
            }
        }
    }
}

@Composable
fun RecipeIcon(imageVector: ImageVector, title: String, text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "Shopping Cart",
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(40.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun RecipeText() {
    val ingredients = listOf(
        "- 1 tbsp olive oil",
        "- 1 onion, chopped",
        "- 2 garlic cloves, crushed",
        "- 1 tsp ground cumin",
        "- 1 tsp ground coriander",
        "- 1 tsp ground cinnamon",
        "- 1 tsp harissa paste",
        "- 400g can chopped tomatoes",
        "- 2 tbsp clear honey",
        "- 2 x 400g cans chickpeas, drained and rinsed",
        "- 1 lemon, juiced",
        "- 2 tbsp chopped coriander",
        "- 2 tbsp chopped parsley",
        "- 2 tbsp chopped mint",
        "- 100g feta cheese, crumbled",
    )

    val steps = listOf(
        "1. Heat the oil in a large saucepan and cook the onion for 5 mins until softened and starting to turn golden. Add the garlic and spices, and cook for 1 min more. Stir in the harissa, tomatoes and honey, and bubble together for 5 mins.",
        "2. Add the chickpeas to the pan with the lemon juice and seasoning, then simmer for 3 mins more. Stir through the herbs and scatter over the feta to serve."
    )

    Column(modifier = Modifier.width(320.dp)) {
        Text(
            text = "Ingredients",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        ingredients.forEach {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        Divider()

        Text(
            text = "Preparation",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        steps.forEach {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}


@Composable
fun Recipe() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RecipeArticleMirror(drawable = R.drawable.plate_1, text = R.string.empty_string)
        RecipeIcons()
        RecipeText()
    }
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
            })
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
            })
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
            })
    }
}

@Preview(showBackground = true)
@Composable
fun SearchPagePreview() {
    RecipeasyTheme(dynamicColor = false) {
        FilterPage()
    }
}

@Preview(showBackground = true)
@Composable
fun ShopPagePreview() {
    RecipeasyTheme(dynamicColor = false) {
        ShopPage()
    }
}

@Preview(showBackground = true)
@Composable
fun RecipePagePreview() {
    RecipeasyTheme(dynamicColor = false) {
        RecipePage()
    }
}



