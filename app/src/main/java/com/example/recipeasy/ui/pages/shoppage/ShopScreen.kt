package com.example.recipeasy.ui.pages.shoppage

import SecondHeader
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeasy.R
import com.example.recipeasy.data.dataclasses.ShopArticle
import com.example.recipeasy.data.dataclasses.ShopItem
import com.example.recipeasy.ui.NavigationDestination


object ShopDestination : NavigationDestination {
    override val route = "shop"
}

@Composable
fun ShopScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    shop: List<ShopArticle>) {

    Scaffold (
        topBar = {
            SecondHeader(title = stringResource(R.string.shopping_list), onBackClicked = navigateBack)
        }
    ){innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShopIcons()

            AnimatedVisibility(
                visible = shop.isNotEmpty(),
                enter = slideInVertically(initialOffsetY = { fullHeight -> fullHeight })
            ) {

                ShopArticles( shop = shop)
            }
        }
    }
}

@Composable
fun ShopIcons(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .height(48.dp),
           horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        ShopIcon(R.drawable.baseline_print)
        ShopIcon(R.drawable.ah_logo)
        ShopIcon(R.drawable.jumbo_logo)
        ShopIcon(R.drawable.collectengo_logo)
    }
}

@Composable
fun ShopIcon(@DrawableRes drawable: Int) {
    Image(

        painter = painterResource(drawable),
        contentDescription = stringResource(R.string.shopping_cart),
        modifier = Modifier.padding(6.dp)
    )
}



@Composable
fun ShopArticles(
    shop: List<ShopArticle>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(shop.size) { index ->
            val shopArticle = shop[index]
            ShopArticle(shopArticle = shopArticle)
        }
    }
}

@Composable
fun ShopItem(shopItem: ShopItem) {
    var checked by remember { mutableStateOf(true) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.width(320.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = checked,
                onCheckedChange = { isChecked ->
                    checked = isChecked
                },
                modifier = Modifier.padding(6.dp)
            )
            Column {
                Text(
                    text = shopItem.quantity.toString() + " " + shopItem.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = shopItem.recipeTitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }

        Icon(
            imageVector = Icons.Outlined.Edit,
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = stringResource(R.string.shopping_cart),
            modifier = Modifier
                .padding(6.dp)
                .size(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShopItemPreview() {
    ShopItem(shopItem = ShopItem(4, "Spare ribs", "Spare ribs with fried potatoes"))
}

@Composable
fun ShopArticle(modifier: Modifier = Modifier, shopArticle: ShopArticle) {
    Column {
        ShopTitle(title = shopArticle.title)
        Surface(
            shape = MaterialTheme.shapes.small,
            color = shopArticle.color,
            modifier = modifier
                .padding(vertical = 8.dp)
                .width(320.dp)
        ) {
            Column {
                shopArticle.shopItems.forEach { shopItem ->
                    ShopItem(shopItem = shopItem)
                }
            }
        }
    }
}

@Composable
fun ShopTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.secondary
    )
}