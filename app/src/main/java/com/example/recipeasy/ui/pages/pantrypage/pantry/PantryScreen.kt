package com.example.recipeasy.ui.pages.pantrypage.pantry

import MainTopBar
import Page
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.recipeasy.R
import com.example.recipeasy.data.dataclasses.PantryItem
import com.example.recipeasy.ui.AppViewModelProvider
import com.example.recipeasy.ui.NavigationDestination
import com.example.recipeasy.ui.theme.AppTheme
import com.example.recipeasy.ui.theme.oldtheme.Colors
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton


object PantryDestination : NavigationDestination {
    override val route = "pantry"
}

@Composable
fun PantryScreen(
    modifier: Modifier = Modifier,
    selectedPage: Page,
    onItemSelected: (Page) -> Unit,
    navigateToFilter: () -> Unit,
    navigateToShop: () -> Unit,
    navigateToPantryEntry: () -> Unit,
    navController: NavHostController,
    viewModel: PantryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val pantryUiState by viewModel.pantryUiState.collectAsState()

    Scaffold(
        topBar = {
            MainTopBar(
                selectedPage = selectedPage,
                onItemSelected = onItemSelected,
                onSearchClicked = navigateToFilter,
                modifier = modifier,
                onShopClicked = navigateToShop,
                navController = navController
            )
        },
        floatingActionButton = {
            val context = LocalContext.current
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                FloatingActionButton(
                    shape = MaterialTheme.shapes.extraSmall,
                    onClick = {
                        savePantryItemsToFile(
                            context,
                            pantryUiState.pantryItems.map { it.name to it.quantity })
                    },
                    containerColor = Color.White,
                    contentColor = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(start = 25.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_save_24),
                        contentDescription = "Save Pantry Items"
                    )
                }

                FloatingActionButton(
                    shape = MaterialTheme.shapes.extraSmall,
                    onClick = navigateToPantryEntry,
                    containerColor = Color.White,
                    contentColor = MaterialTheme.colorScheme.secondary
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add pantry item")
                }
            }
        }
    ) { innerPadding ->
        PantryList(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            pantry = pantryUiState.pantryItems,
            onIncreaseClick = { viewModel.increaseQuantity(it.id) },
            onDecreaseClick = { viewModel.decreaseQuantity(it.id) }
        )
    }
}

@Composable
fun PantryList(
    modifier: Modifier = Modifier,
    pantry: List<PantryItem>,
    onIncreaseClick: (PantryItem) -> Unit,
    onDecreaseClick: (PantryItem) -> Unit
) {

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val colors = Colors.surfaceColors

        items(items = pantry) { pantryItem ->
            val index = pantry.indexOf(pantryItem)
            val color = colors[index % colors.size]

            PantryArticle(
                text = pantryItem.name,
                imageUrl = pantryItem.image,
                color = color,
                quantity = pantryItem.quantity,
                onIncreaseClick = { onIncreaseClick(pantryItem) },
                onDecreaseClick = { onDecreaseClick(pantryItem) }
            )
        }
    }
}

@Composable
fun PantryArticle(
    text: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
    quantity: Int,
    color: Color,
    onIncreaseClick: () -> Unit,
    onDecreaseClick: () -> Unit
) {
    Row(
        modifier = modifier
            .width(320.dp)
            .height(140.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            PantryImage(imageUrl, color)
            PantryText(text)
        }
        PantryButton(
            text = quantity,
            onIncreaseClick = onIncreaseClick,
            onDecreaseClick = onDecreaseClick
        )
    }
}

@Composable
fun PantryButton(
    text: Int,
    modifier: Modifier = Modifier,
    onIncreaseClick: () -> Unit,
    onDecreaseClick: () -> Unit
) {
    Surface(
        shape = MaterialTheme.shapes.extraSmall,
        shadowElevation = 4.dp,
        modifier = modifier.fillMaxHeight(0.8f)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            IconButton(onClick = onIncreaseClick) {
                PantryButtonText(text = "+")
            }
            Text(
                text = text.toString(),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
            IconButton(onClick = onDecreaseClick) {
                PantryButtonText(text = "-")
            }
        }
    }
}

@Preview
@Composable
fun PantryButtonPreview() {
AppTheme {
    PantryButton(
        text = 5,
        onIncreaseClick = {},
        onDecreaseClick = {}
    )
}
}

@Composable
private fun PantryText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
private fun PantryImage(
    imageUrl: String,
    color: Color
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        shadowElevation = 4.dp,
        color = color,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .width(130.dp)
            .height(130.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
            )
        }
    }
}



@Composable
private fun PantryButtonText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.secondary
    )
}

fun savePantryItemsToFile(context: Context, pantryItems: List<Pair<String, Int>>) {
    val pantryText =
        pantryItems.joinToString(separator = "\n") { (name, quantity) -> "$name: $quantity" }
    val fileName = "pantry_items.txt"
    val fileContents = "Pantry Items:\n$pantryText"

    try {
        // Create or open the file in the internal storage
        val file = File(context.filesDir, fileName)
        val fos = FileOutputStream(file)
        fos.write(fileContents.toByteArray())
        fos.close()
        // Optionally, notify the user that the file has been saved
        Toast.makeText(context, "Pantry items saved to $fileName", Toast.LENGTH_SHORT).show()
    } catch (e: IOException) {
        e.printStackTrace()
        // Optionally, notify the user that there was an error
        Toast.makeText(context, "Failed to save pantry items", Toast.LENGTH_SHORT).show()
    }
}


//@Preview
//@Composable
//fun PantryScreenPreview() {
//    val navController = rememberNavController()
//
//    AppTheme {
//        PantryScreen(
//            selectedPage = Page.PANTRY,
//            onItemSelected = {},
//            navigateToFilter = {},
//            navigateToShop = {},
//            navigateToPantryEntry = {},
//            navController = navController
//        )
//    }
//}
