
import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeasy.R
import com.example.recipeasy.data.dataclasses.MealDetails
import com.example.recipeasy.ui.NavigationDestination
import com.example.recipeasy.ui.pages.recipedetail.RecipeDetailViewModel
import com.example.recipeasy.ui.theme.oldtheme.Colors

object RecipeDetailDestination : NavigationDestination {
    override val route = "recipe_detail"
    const val recipeIdArg = "recipeId"
    val routeWithArgs = "$route/{$recipeIdArg}"
}

@Composable
fun RecipeScreen(
    navigateBack: () -> Unit,
    recipeId: String
) {
    val viewModel: RecipeDetailViewModel = viewModel()
    val recipeDetailState by viewModel.recipeDetailState.collectAsState()

    LaunchedEffect(key1 = recipeId) {
        viewModel.fetchRecipeDetail(recipeId)
    }

    val recipe = recipeDetailState

    Column {
        if (recipe != null) {
            SecondHeader(recipe.strMeal, onBackClicked = navigateBack)
        }
        if (recipe != null) {
            Recipe(
                recipe = recipe
            )
        }
    }


}

@Composable
fun RecipeIcons(
    recipe: MealDetails
) {
    val icons = listOf(
        R.drawable.outline_timer,
        R.drawable.cooking,
        R.drawable.plate,
        R.drawable.outline_add_shopping_cart,

    )

    val iconTitle = listOf(
        stringResource(R.string.time),
        stringResource(R.string.difficulty),
        stringResource(R.string.servings),
        stringResource(R.string.add_to_shop)
    )

    val iconText = listOf(
        recipe.time + " min",
        recipe.difficulty,
        recipe.servings,
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

        val context = LocalContext.current

        Row{
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = "share",
                modifier = Modifier
                    .size(40.dp)
                    .padding(6.dp)
                    .clickable {
                        shareRecipe(context, recipe)
                    },
                tint = MaterialTheme.colorScheme.primary

            )

            Icon(
                painter = painterResource(R.drawable.baseline_calendar_month_24),
                contentDescription = stringResource(R.string.add_to_calender),
                modifier = Modifier
                    .size(40.dp)
                    .padding(6.dp)
                    .clickable {
                        addRecipeToCalendar(context, recipe)
                    },
                tint = MaterialTheme.colorScheme.primary
            )
        }

    }
}

fun addRecipeToCalendar(context: Context, recipe: MealDetails) {
    val intent = Intent(Intent.ACTION_INSERT)
        .setData(CalendarContract.Events.CONTENT_URI)
        .putExtra(CalendarContract.Events.TITLE, recipe.strMeal)
        .putExtra(CalendarContract.Events.DESCRIPTION, recipe.strInstructions)
        .putExtra(CalendarContract.Events.EVENT_LOCATION, "Home")
    context.startActivity(intent)
}

fun shareRecipe(context: Context, recipe: MealDetails) {
    val recipeText = "I will be eating: ${recipe.strMeal}}"
    val intent: Intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, recipeText)
    }

    context.startActivity(Intent.createChooser(intent, "Share recipe using:"))
}

@Composable
fun RecipeIcon(icon: Int, title: String, text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = stringResource(R.string.shopping_cart),
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
fun RecipeText(recipe: MealDetails) {




    val preparation = recipe.strInstructions


    Column(modifier = Modifier.width(320.dp)) {
            Text(
                text = stringResource(R.string.ingredients),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )


        recipe.ingredients.forEach { ingredient ->
            Text(
                text = "- " + ingredient.name + " " + ingredient.measure,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )

        }

            Divider(modifier = Modifier.padding(vertical = 6.dp))



            Text(
                text = stringResource(R.string.preparation),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )


            Text(
                text = preparation,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(bottom = 6.dp)
            )
        }

}


@Composable
fun Recipe(
    recipe: MealDetails
)
{
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {

            val color = Colors.surfaceColors.random()

            RecipeArticleMirror(
                imageUrl = recipe.strMealThumb,
                text = stringResource(R.string.empty),
                onClick = {},
                color = color
            )
        }

        item {
            RecipeIcons(recipe = recipe)
        }

        item {
            RecipeText(recipe = recipe)
        }
    }
}