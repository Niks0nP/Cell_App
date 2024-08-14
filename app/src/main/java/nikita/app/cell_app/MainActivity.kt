package nikita.app.cell_app

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import nikita.app.cell_app.logic.UiLayer
import nikita.app.cell_app.logic.application.App
import nikita.app.cell_app.ui.theme.Cell_AppTheme

class MainActivity : ComponentActivity() {

    private val uiLayer = UiLayer()
    private val viewModel = App.getInstance().viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT))
        setContent {
            Cell_AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    uiLayer.Greeting(
                        modifier = Modifier.padding(innerPadding),
                        viewModel
                    )
                }
            }
        }
    }
}
