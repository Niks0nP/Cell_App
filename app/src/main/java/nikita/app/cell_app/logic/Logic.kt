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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
import nikita.app.cell_app.ui.theme.backColor1

class Logic {

    private val cells1 = mutableStateListOf<Int>()
    private val valueAlive = mutableListOf<Int>()

    private val gradient = Brush.verticalGradient(
        0.0f to backColor1,
        1.0f to Color.Black,
        startY = 0.0f,
        endY = 1500.0f
    )

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {

        val cells = remember {
            cells1
        }

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
                            CellItem(valueAlive[it])
                        }
                    }

                    if (cells.size >= 3) {
                        if (valueAlive[cells.size - 1] == 1 &&
                            valueAlive[cells.size - 2] == 1 &&
                            valueAlive[cells.size - 3] == 1) {
                            cells.add(1)
                            valueAlive.add(2)
                            item { CellItem(valueAlive[cells.size - 1]) }
                        }
                    }

                    if (cells.size >= 3) {
                        if (valueAlive[cells.size - 1] == 0 &&
                            valueAlive[cells.size - 2] == 0 &&
                            valueAlive[cells.size - 3] == 0) {
                            cells.add(1)
                            valueAlive.add(3)
                            item { CellItem(valueAlive[cells.size - 1]) }
                        }
                    }
                }

            }
            Button(onClick = {
                cells.add(1)
                valueAlive.add((0..1).random())

                coroutineScope.launch {
                    listState.animateScrollToItem(cells1.size - 1)
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
    fun CellItem(value: Int) {
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
                when (value) {
                    0 -> painterResource(id = R.drawable.ellipse_death)
                    1 -> painterResource(id = R.drawable.ellipse_life)
                    else -> painterResource(id = R.drawable.ellipce_life_create)
                }, contentDescription = "",
                    modifier = Modifier.size(40.dp))

                Image(painter =
                when (value) {
                    0, 3 -> painterResource(id = R.drawable.death_cell)
                    1 -> painterResource(id = R.drawable.life_cell)
                    else -> painterResource(id = R.drawable.create_life)
                }, contentDescription = "",
                    modifier = Modifier.size(22.dp))
            }
            Column {
                Text(text = when (value) {
                    0 -> stringResource(id = R.string.death1)
                    1 -> stringResource(id = R.string.life1)
                    else -> stringResource(id = R.string.life_create1)
                },
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                    )
                Text(text = when (value) {
                    0 -> stringResource(id = R.string.death2)
                    1 -> stringResource(id = R.string.life2)
                    3 -> stringResource(id = R.string.life_death)
                    else -> stringResource(id = R.string.life_create2)
                },
                    color = Color.Black)
            }

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        Cell_AppTheme {
            Greeting("Android")
        }
    }
}