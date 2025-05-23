import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeasy.R
import com.example.recipeasy.ui.theme.RecipeasyTheme

@Composable
fun SecondHeader(title: String, subtitle: String = "", onBackClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp, bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back),
                modifier = Modifier
                    .clickable(onClick = onBackClicked)
                    .size(36.dp),
                tint = MaterialTheme.colorScheme.secondary
            )
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = (-18).dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge.copy(fontSize = 32.sp),
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                }
            }

        }
        Divider()
    }
}


@Preview(showBackground = true)
@Composable
fun SecondHeaderPreview() {
    RecipeasyTheme (dynamicColor = false){
        SecondHeader(title = "Chicken with vegetables", subtitle = "testsubtitle", onBackClicked = {})
    }
}

@Preview(showBackground = true)
@Composable
fun SecondHeaderPreview2() {
    RecipeasyTheme (dynamicColor = false){
        SecondHeader(title = stringResource(R.string.shopping_list), subtitle = stringResource(R.string.empty), onBackClicked = {})
    }
}