package com.alexisdev.main.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.alexisdev.domain.model.Cell
import com.alexisdev.domain.model.CellStatus
import com.alexisdev.main.R


data class CellUi(
    val id: Long,
    val status: CellStatusUi
)

enum class CellStatusUi(
    @StringRes val title: Int,
    @StringRes val description: Int,
    @DrawableRes val imageRes: Int
) {
    ALIVE(
        title = R.string.alive_cell_status_title,
        description = R.string.alive_cell_status_desc,
        imageRes = R.drawable.alive_cell_image
    ),
    DEAD(
        title = R.string.dead_cell_status_title,
        description = R.string.dead_cell_status_desc,
        imageRes = R.drawable.dead_cell_image
    ),
    NEW_LIFE(
        title = R.string.new_life_cell_status_title,
        description = R.string.new_life_cell_status_desc,
        imageRes = R.drawable.new_life_cell_image
    )
}


fun CellUi.toCell() = Cell(id = id, status = status.toCellStatus())

fun Cell.toCellUi() = CellUi(id = id, status = status.toCellStatusUi())

fun CellStatusUi.toCellStatus(): CellStatus {
    return when (this) {
        CellStatusUi.ALIVE -> CellStatus.ALIVE
        CellStatusUi.DEAD -> CellStatus.DEAD
        CellStatusUi.NEW_LIFE -> CellStatus.NEW_LIFE
    }
}

fun CellStatus.toCellStatusUi(): CellStatusUi {
    return when (this) {
        CellStatus.ALIVE -> CellStatusUi.ALIVE
        CellStatus.DEAD -> CellStatusUi.DEAD
        CellStatus.NEW_LIFE -> CellStatusUi.NEW_LIFE
    }
}


