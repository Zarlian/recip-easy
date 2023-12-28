import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.recipeasy.Page
import com.example.recipeasy.R

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