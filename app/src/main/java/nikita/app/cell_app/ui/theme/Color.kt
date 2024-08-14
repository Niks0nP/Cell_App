package nikita.app.cell_app.ui.theme

import android.graphics.Color.rgb
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val backColor1 = Color(rgb(49, 0, 80))

val gradient = Brush.verticalGradient(
    0.0f to backColor1,
    1.0f to Color.Black,
    startY = 0.0f,
    endY = 1500.0f
)