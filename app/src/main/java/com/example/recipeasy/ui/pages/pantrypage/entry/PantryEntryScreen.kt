package com.example.recipeasy.ui.pages.pantrypage.entry

import SecondHeader
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.recipeasy.R
import com.example.recipeasy.ui.AppViewModelProvider
import com.example.recipeasy.ui.NavigationDestination
import com.example.recipeasy.ui.pages.pantrypage.PantryItemDetails
import com.example.recipeasy.ui.pages.pantrypage.PantryUiState
import kotlinx.coroutines.launch

object PantryEntryDestination : NavigationDestination {
    override val route = "pantry_entry"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantryEntryScreen(
    navigateBack: () -> Unit,
    canNavigateBack: Boolean = true,
    viewModel: PantryEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val activityResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.updateUiState(viewModel.pantryUiState.pantryItem.copy(imageUri = it))
        }
    }

    Scaffold(
        topBar = {
            SecondHeader(
                title = "Add pantry item",
                onBackClicked = navigateBack
            )
        }
    ) { innerPadding ->
        PantryEntryBody(
            itemUiState = viewModel.pantryUiState,
            onItemValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveItem()
                    navigateBack()
                }
            },
            onSelectImageClick = {
                activityResultLauncher.launch("image/*")
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}



@Composable
fun PantryEntryBody(
    itemUiState: PantryUiState,
    onItemValueChange: (PantryItemDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    onSelectImageClick: () -> Unit
) {
    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large))
    ) {
        ItemInputForm(
            itemDetails = itemUiState.pantryItem,
            onValueChange = onItemValueChange,
            modifier = Modifier.fillMaxWidth(),
            onSelectImageClick = onSelectImageClick
        )
        Button(
            onClick = onSaveClick,
            enabled = itemUiState.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Save item")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemInputForm(
    itemDetails: PantryItemDetails,
    modifier: Modifier = Modifier,
    onValueChange: (PantryItemDetails) -> Unit = {},
    enabled: Boolean = true,
    onSelectImageClick: () -> Unit // Add this parameter
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        OutlinedTextField(
            value = itemDetails.name,
            onValueChange = { onValueChange(itemDetails.copy(name = it)) },
            label = { Text("Name") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = itemDetails.quantity,
            onValueChange = { onValueChange(itemDetails.copy(quantity = it)) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
            label = { Text(stringResource(R.string.amount)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        Button(onClick = onSelectImageClick, enabled = enabled) {
            Text(text = stringResource(R.string.select_image))
        }

        itemDetails.imageUri?.let { uri ->
            // Display selected image (optional)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = rememberAsyncImagePainter(uri),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(115.dp)
                        .clip(CircleShape)
                )
            }
        }

        Row (
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = stringResource(R.string.main_ingredient),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 16.dp)
            )

            Checkbox(
                checked = itemDetails.isMainIngredient,
                onCheckedChange = { isChecked ->
                    onValueChange(itemDetails.copy(isMainIngredient = isChecked))
                },
                colors = CheckboxDefaults.colors(
                    checkmarkColor = MaterialTheme.colorScheme.secondary
                )
            )
        }
    }
}

