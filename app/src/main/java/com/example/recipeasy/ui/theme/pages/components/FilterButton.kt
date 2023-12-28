import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FilterButton() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(50.dp)
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val circleSize = size.width * 1f // Adjust size as needed
            val circleShadowSize = circleSize * 1.05f // Adjust shadow size

            // Draw shadow
            drawCircle(
                color = Color.Gray.copy(alpha = 0.4f), // Semi-transparent gray
                radius = circleShadowSize / 2,
                center = Offset(size.width / 2, size.height / 2)
            )

            // Draw main circle
            drawCircle(
                color = Color.White,
                radius = circleSize / 2,
                center = Offset(size.width / 2, size.height / 2)
            )
            drawLine(
                start = Offset(x = size.width * 0.2f, y = size.height * 0.4f),
                end = Offset(x = size.width * 0.8f, y = size.height * 0.4f),
                color = Color.Gray,
                strokeWidth = 2.dp.toPx()
            )
            drawLine(
                start = Offset(x = size.width * 0.3f, y = size.height * 0.5f),
                end = Offset(x= size.width * 0.7f, y= size.height*0.5f),
                color=Color.Gray,
                strokeWidth=2.dp.toPx()
            )
            drawLine(
                start= Offset(x=size.width*0.4f,y=size.height*0.6f),
                end= Offset(x=size.width*0.6f,y=size.height*0.6f),
                color=Color.Gray,
                strokeWidth=2.dp.toPx()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FilterButtonPreview() {
    FilterButton(

    )
}