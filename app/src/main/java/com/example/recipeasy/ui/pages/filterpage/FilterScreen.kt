import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeasy.R
import com.example.recipeasy.data.DataSource
import com.example.recipeasy.data.dataclasses.RecipeArticle
import com.example.recipeasy.ui.NavigationDestination
import com.example.recipeasy.ui.theme.RecipeasyTheme


object FilterDestination : NavigationDestination {
    override val route = "filter"
}

@Composable
fun FilterScreen(
    modifier: Modifier = Modifier,
    recipeArticles: List<RecipeArticle>,
    navigateBack: () -> Unit
) {
    var choice by remember { mutableStateOf("") }


    Scaffold (
        topBar = {
            SecondHeader(
                title = "Result",
                subtitle = "You have 3 items",
                onBackClicked = navigateBack
            )
        },
        floatingActionButton = {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                FloatingActionButton(
                    onClick = { /*TODO*/ },
                    modifier = modifier.padding(16.dp)
                ) {
                    FilterButton()
                }
            }
        }
    )
    { innerPadding ->

        Row(
            modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Filter() { updatedChoice ->
                choice = updatedChoice // Update the choice in FilterPage
            }
            FilterBy(choice = choice)
            Sort()
        }
        FilterResults(recipeArticles = recipeArticles)
    }
}

@Composable
fun FilterBy(choice: String) {
    var expandedFilter by remember { mutableStateOf(false) }
    val foodItems = remember {
        mapOf(
            "Chicken" to mutableStateOf(false),
            "Eggs" to mutableStateOf(false),
            "Pasta" to mutableStateOf(false),
            "Beef" to mutableStateOf(false),
            "Milk" to mutableStateOf(false),
            "Cheese" to mutableStateOf(false)
        )
    }

    if (choice == "ONLY" || choice == "NO") {
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
                    foodItems.forEach { (food, checked) ->
                        DropdownMenuItem(
                            onClick = { checked.value = !checked.value },
                            text = { Text(food) },
                            leadingIcon = { Checkbox(checked = checked.value, onCheckedChange = { isChecked ->
                                checked.value = isChecked
                            }) }
                        )
                    }

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
fun FilterResults(recipeArticles: List<RecipeArticle>) {
    Box(
        modifier = Modifier
            .width(320.dp),

    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(320.dp)
        ) {
            items(recipeArticles.size) { index ->
                val recipeArticle = recipeArticles[index]
                FilterResult(recipeArticle = recipeArticle)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilterResultsPreview() {
    RecipeasyTheme {
        FilterResults(recipeArticles = DataSource.filterResultList)
    }
}

@Composable
fun FilterResult(modifier: Modifier = Modifier, recipeArticle: RecipeArticle) {
    Row(
        modifier = modifier
            .width(320.dp)
            .height(100.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.width(320.dp)
        ) {
            FilterImage(recipeArticle.image, recipeArticle.color)
            FilterText(recipeArticle.title)
        }
    }
}

@Composable
private fun FilterText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
private fun FilterImage(drawable: Int, color: Color) {
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
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(115.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilterResultPreview() {
    RecipeasyTheme {
        FilterResult(modifier = Modifier , RecipeArticle(1,"Chicken pie", R.drawable.plate_1, Color(0xFFEECED3)))
    }
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

@Preview
@Composable
fun FilterScreen(){
    RecipeasyTheme {
        FilterScreen(recipeArticles = listOf(),
                    navigateBack = {})
    }
}