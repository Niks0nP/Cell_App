package nikita.app.cell_app.logic

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import kotlinx.coroutines.launch
import nikita.app.cell_app.R
import nikita.app.cell_app.ui.theme.Cell_AppTheme
import nikita.app.cell_app.ui.theme.gradient
import nikita.app.cell_app.viewmodel.MyViewModel

class UiLayer {

    @Composable
    fun Greeting(modifier: Modifier = Modifier, viewModel: MyViewModel = MyViewModel()) {

        val cells = viewModel.cells
        val valueAlive = viewModel.valueAlive

        val listState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()

        ConstraintLayout {
            Column(
                modifier
                    .fillMaxSize()
                    .background(gradient)
                    .padding(bottom = 80.dp)) {
                Column(
                    modifier
                        .height(60.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = stringResource(id = R.string.title),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp)
                }
                LazyColumn(state = listState) {
                    items ( cells.size ) {
                        AnimatedVisibility(visible = true) {
                            CellItem(valueAlive[it], viewModel)
                        }
                    }
                }
            }
            Button(onClick = {
                viewModel.addSell()

                coroutineScope.launch {
                    listState.animateScrollToItem(cells.size - 1)
                }
            },
                modifier
                    .fillMaxWidth()
                    .constrainAs(createRef()) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
                Text(text = stringResource(id = R.string.create),
                color = Color.White,
                fontWeight = FontWeight.Bold)
            }
        }
    }

    @Composable
    fun CellItem(value: Int, viewModel: MyViewModel) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(86.dp)
                .padding(16.dp, 0.dp, 16.dp, 4.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically) {

            Box(contentAlignment = Alignment.Center,
                modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 0.dp)) {

                Image(painter =
                painterResource(id = viewModel.getCellUiState(value).backgroundResource)
                    , contentDescription = "",
                    modifier = Modifier.size(40.dp))

                Image(painter =
                painterResource(id = viewModel.getCellUiState(value).iconResource)
                    , contentDescription = "",
                    modifier = Modifier.size(22.dp))
            }
            Column {
                Text(text = stringResource(id = viewModel.getCellUiState(value).title),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black)

                Text(text = stringResource(id = viewModel.getCellUiState(value).description),
                    color = Color.Black)
            }

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        Cell_AppTheme {
            Greeting()
        }
    }
}