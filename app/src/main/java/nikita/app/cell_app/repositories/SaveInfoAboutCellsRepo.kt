package nikita.app.cell_app.repositories

import androidx.compose.runtime.mutableStateListOf

class SaveInfoAboutCellsRepo {

     val _cells = mutableStateListOf<Int>().apply { addAll(emptyList()) }
    val cells : List<Int> get() = _cells

     val _valueAlive = mutableListOf<Int>().apply { addAll(emptyList()) }
    val valueAlive : List<Int> get() = _valueAlive
}