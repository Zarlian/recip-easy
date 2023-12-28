import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.recipeasy.R
import com.example.recipeasy.data.dataclasses.Recipe
import com.example.recipeasy.data.dataclasses.RecipeArticle

@Composable
fun RecipePage(
    onBackClicked: () -> Unit,
    recipeArticle: RecipeArticle
) {
    Column {
        SecondHeader(recipeArticle.recipe.recipeTitle, onBackClicked = onBackClicked)
        Recipe( recipeArticle = recipeArticle)
    }
}

@Composable
fun RecipeIcons( recipe: Recipe) {
    val icons = listOf(
        R.drawable.outline_timer,
        R.drawable.cooking,
        R.drawable.plate,
        R.drawable.outline_add_shopping_cart
    )

    val iconTitle = listOf(
        stringResource(R.string.time),
        stringResource(R.string.difficulty),
        stringResource(R.string.servings),
        stringResource(R.string.add_to_shop)
    )

    val iconText = listOf(
        recipe.time.toString() + " min",
        recipe.difficulty,
        recipe.servings.toString(),
        ""
    )
    Row(
        modifier = Modifier.width(320.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (i in 0..3) {
            Column {
                RecipeIcon(icon = icons[i], title = iconTitle[i], text = iconText[i])
            }
        }
    }
}

@Composable
fun RecipeIcon(icon: Int, title: String, text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = "Shopping Cart",
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
fun RecipeText(recipe: Recipe) {
    val ingredients = recipe.ingredients.map { ingredient ->
        "- " + ingredient.name + " " + ingredient.quantity.toString() + " " + ingredient.unit
    }

    val steps = recipe.steps.map { step ->
        "- " + step.prepStep
    }

    LazyColumn(modifier = Modifier.width(320.dp)) {
        item {
            Text(
                text = "Ingredients",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }

        items(ingredients) { ingredient ->
            Text(
                text = ingredient,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )

        }
        item {
            Divider(modifier = Modifier.padding(vertical = 6.dp))
        }

        item {
            Text(
                text = "Preparation",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }

        items(steps) { step ->
            Text(
                text = step,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(bottom = 6.dp)
            )
        }
    }
}


@Composable
fun Recipe(recipeArticle: RecipeArticle) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RecipeArticleMirror(drawable = recipeArticle.image, text = "", color = recipeArticle.color)
        RecipeIcons(recipe = recipeArticle.recipe)
        RecipeText(recipe = recipeArticle.recipe)
    }
}