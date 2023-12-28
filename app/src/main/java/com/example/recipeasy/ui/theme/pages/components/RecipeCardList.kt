import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.recipeasy.data.dataclasses.RecipeArticle

@Composable
fun RecipeCardList(modifier: Modifier = Modifier, recipeArticles: List<RecipeArticle>) {

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(recipeArticles.size) { index ->
            val recipe = recipeArticles[index]
            if (index % 2 == 0) {
                RecipeArticle(
                    text = recipe.title,
                    drawable = recipe.image,
                    color = recipe.color
                )
            } else {
                RecipeArticleMirror(
                    drawable = recipe.image,
                    text = recipe.title,
                    color = recipe.color
                )
            }
        }
    }
}

@Composable
fun RecipeArticle(
    @DrawableRes drawable: Int,
    text: String,
    modifier: Modifier = Modifier,
    color: Color
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = color,
        shadowElevation = 4.dp,
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
                text = text,
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
    text: String,
    modifier: Modifier = Modifier,
    color: Color
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = color,
        shadowElevation = 4.dp,
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
                text = text,
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

