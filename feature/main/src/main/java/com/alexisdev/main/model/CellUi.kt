package com.alexisdev.main.model

import com.alexisdev.domain.model.Cell
import com.alexisdev.domain.model.CellStatus

data class CellUi(
    val id: Long,
    val status: CellStatusUi
)

fun CellUi.toCell() = Cell(id = id, status = status.toCellStatus())

fun Cell.toCellUi() = CellUi(id = id, status = status.toCellStatusUi())



