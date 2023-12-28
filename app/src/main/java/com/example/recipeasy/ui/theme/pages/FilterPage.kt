import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape

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