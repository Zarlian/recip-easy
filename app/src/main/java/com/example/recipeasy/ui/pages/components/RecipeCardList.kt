import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import coil.compose.rememberAsyncImagePainter
import com.example.recipeasy.data.dataclasses.MealDetails
import com.example.recipeasy.ui.theme.Colors

@Composable
fun RecipeCardList(
    modifier: Modifier = Modifier,
    recipes: List<MealDetails>,
    onItemClick: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val colors = Colors.surfaceColors

        items(items = recipes,  ) { recipe ->
            val index = recipes.indexOf(recipe)
            val color = colors[index % colors.size]

            if (index % 2 == 0) {
                RecipeArticle(
                    text = recipe.strMeal,
                    imageUrl = recipe.strMealThumb,
                    color = color,
                    onClick = { onItemClick(recipe.idMeal)}
                )
            } else {
                RecipeArticleMirror(
                    text = recipe.strMeal,
                    color = color,
                    onClick = { onItemClick(recipe.idMeal)},
                    imageUrl = recipe.strMealThumb
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeArticle(
    text: String,
    modifier: Modifier = Modifier,
    imageUrl: String,
    onClick: () -> Unit,
    color: Color
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        shadowElevation = 4.dp,
        color = color,
        modifier = modifier
            .padding(vertical = 8.dp)
            .width(320.dp)
            .height(130.dp),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(115.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .weight(1f) // Expand to take remaining space
                    .padding(end = 16.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeArticleMirror(
    text: String,
    modifier: Modifier = Modifier,
    imageUrl: String,
    onClick: () -> Unit,
    color: Color
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        color = color,
        shadowElevation = 4.dp,
        modifier = modifier
            .padding(vertical = 8.dp)
            .width(320.dp)
            .height(130.dp),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .weight(1f) // Expand to take remaining space
                    .padding(start = 16.dp),
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(115.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(10.dp))


        }
    }
}

