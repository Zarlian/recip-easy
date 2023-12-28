import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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