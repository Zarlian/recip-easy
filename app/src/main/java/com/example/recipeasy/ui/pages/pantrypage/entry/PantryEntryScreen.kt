package com.example.recipeasy.ui.pages.pantrypage.entry

import SecondHeader
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeasy.R
import com.example.recipeasy.ui.AppViewModelProvider
import com.example.recipeasy.ui.NavigationDestination
import com.example.recipeasy.ui.pages.pantrypage.PantryItemDetails
import com.example.recipeasy.ui.pages.pantrypage.PantryUiState
import kotlinx.coroutines.launch
import java.util.Currency
import java.util.Locale

object PantryEntryDestination : NavigationDestination {
    override val route = "pantry_entry"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantryEntryScreen(
    navigateBack: () -> Unit,
//    onNavigateUp: () -> Unit,
    canNavigateBack: Boolean = true,
    viewModel: PantryEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
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
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large))
    ) {
        ItemInputForm(
            itemDetails = itemUiState.pantryItem,
            onValueChange = onItemValueChange,
            modifier = Modifier.fillMaxWidth()
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
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        OutlinedTextField(
            value = itemDetails.name,
            onValueChange = { onValueChange(itemDetails.copy(name = it)) },
            label = { Text("Name") },
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//            ),
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
            label = { Text("Amount") },
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = itemDetails.image,
            onValueChange = { onValueChange(itemDetails.copy(image = it)) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            label = { Text("Image url") },
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
//        OutlinedTextField(
//            value = itemDetails.quantity,
//            onValueChange = { onValueChange(itemDetails.copy(quantity = it)) },
//            keyboardOptions = KeyboardOptions.Default.copy(
//                keyboardType = KeyboardType.Number,
//                imeAction = ImeAction.Done
//            ),
//            label = { Text(stringResource(R.string.quantity_req)) },
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//            ),
//            modifier = Modifier.fillMaxWidth(),
//            enabled = enabled,
//            singleLine = true
//        )
        if (enabled) {
            Text(
                text = "*required fields",
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
            )
        }
    }
}