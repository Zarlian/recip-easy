import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeasy.Page
import com.example.recipeasy.data.dataclasses.PantryItem
import com.example.recipeasy.data.pantryItems
import com.example.recipeasy.ui.theme.RecipeasyTheme

@Composable
fun PantryPage(
    modifier: Modifier = Modifier,
    selectedPage: Page, onItemSelected: (Page) -> Unit,
    pantry: List<PantryItem>) {
    Column(modifier = modifier.fillMaxSize()) {
        Header(
            selectedPage = selectedPage,
            onItemSelected = onItemSelected,
            modifier = modifier
        )
        PantryList(modifier = modifier.align(Alignment.CenterHorizontally), pantry = pantry)
    }
}

@Composable
fun PantryList(modifier: Modifier = Modifier, pantry: List<PantryItem>) {

    Box(modifier.fillMaxSize(),
        contentAlignment = Alignment.Center ){
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(pantry.size) {index ->
                val pantryItem = pantry[index]
                PantryArticle(
                    text = pantryItem.name,
                    drawable = pantryItem.image,
                    color = pantryItem.color,
                    quantity = pantryItem.quantity
                )
            }

        }
        FloatingActionButton(
            shape = MaterialTheme.shapes.extraSmall,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = { /* add new pantry item */ },
            containerColor = Color.White,
            contentColor = MaterialTheme.colorScheme.secondary
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }
    }
}


@Composable
fun PantryArticle(text: String, drawable: Int, color: Color, modifier: Modifier = Modifier, quantity: Int) {
    Row(
        modifier = modifier
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
        PantryButton(text = quantity)
    }
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
private fun PantryImage(drawable: Int, color: Color) {
    Surface(
        shape = MaterialTheme.shapes.small,
        shadowElevation = 4.dp,
        color = color,
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
fun PantryButton(text: Int, modifier: Modifier = Modifier) {
    Surface(
        shape = MaterialTheme.shapes.extraSmall,
        shadowElevation = 4.dp,
        modifier = modifier
            .fillMaxHeight(0.7f)

    ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            ) {
                PantryButtonText(text = "+")
                Text(
                    text = text.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                PantryButtonText(text = "-")
            }

    }
}

@Composable
private fun PantryButtonText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.secondary
    )
}

@Preview(showBackground = true)
@Composable
fun PantryListPreview() {
    RecipeasyTheme(dynamicColor = false) {
        PantryList(pantry = pantryItems)
    }
}