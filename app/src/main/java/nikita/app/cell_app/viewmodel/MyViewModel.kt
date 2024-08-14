package nikita.app.cell_app.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import nikita.app.cell_app.R
import nikita.app.cell_app.logic.data.CellUi

class MyViewModel : ViewModel() {

    private val _cells = mutableStateListOf<Int>().apply { addAll(emptyList()) }
    val cells : List<Int> get() = _cells

    private val _valueAlive = mutableListOf<Int>().apply { addAll(emptyList()) }
    val valueAlive : List<Int> get() = _valueAlive
//    private val cells = SaveInfoAboutCellsRepo()._cells
//    private val valueAlive = SaveInfoAboutCellsRepo()._valueAlive

    fun addSell() {
        _cells.add(1)
        _valueAlive.add((0..1).random())
        updateCells()
    }

    private fun updateCells() {
        if (_cells.size >= 3) {
            when {
                _valueAlive.takeLast(3).all { it == 1 } -> {
                    _cells.add(1)
                    _valueAlive.add(2)
                }
                _valueAlive.takeLast(3).all { it == 0 } -> {
                    _cells.add(1)
                    _valueAlive.add(3)
                }
            }
        }
    }

    fun getCellUiState(value: Int) : CellUi {
        return when (value) {
            0 -> CellUi(
                backgroundResource = R.drawable.ellipse_death,
                iconResource = R.drawable.death_cell,
                title = R.string.death1,
                description = R.string.death2
            )
            1 -> CellUi(
                backgroundResource = R.drawable.ellipse_life,
                iconResource = R.drawable.life_cell,
                title = R.string.life1,
                description = R.string.life2
            )
            3 -> CellUi(
                backgroundResource = R.drawable.ellipce_life_create,
                iconResource = R.drawable.death_cell,
                title = R.string.life_create1,
                description = R.string.life_death
            )
            else -> CellUi(
                backgroundResource = R.drawable.ellipce_life_create,
                iconResource = R.drawable.create_life,
                title = R.string.life_create1,
                description = R.string.life_create2
            )
        }
    }
}