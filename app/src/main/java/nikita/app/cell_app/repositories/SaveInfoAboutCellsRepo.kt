package nikita.app.cell_app.repositories

import androidx.compose.runtime.mutableStateListOf

class SaveInfoAboutCellsRepo {

    private val cells = mutableStateListOf<Int>()
    private val valueAlive = mutableListOf<Int>()

    fun getCells() : MutableList<Int> {
        return cells
    }

    fun setCells() {
        cells.add(1)
    }

}