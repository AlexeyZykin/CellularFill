package com.alexisdev.data.model

import com.alexisdev.domain.model.Cell
import com.alexisdev.domain.model.CellStatus

data class CellDto(
    val id: Long,
    val status: CellStatusDto
)

enum class CellStatusDto {
    ALIVE, DEAD, NEW_LIFE
}


fun CellDto.toCell() = Cell(id = id, status = status.toCellStatus())


fun CellStatusDto.toCellStatus(): CellStatus {
    return when (this) {
        CellStatusDto.NEW_LIFE -> CellStatus.NEW_LIFE
        CellStatusDto.ALIVE -> CellStatus.ALIVE
        CellStatusDto.DEAD -> CellStatus.DEAD
    }
}

fun Cell.toCellDto() = CellDto(id = id, status = status.toCellStatusDto())

fun CellStatus.toCellStatusDto(): CellStatusDto {
    return when (this) {
        CellStatus.NEW_LIFE -> CellStatusDto.NEW_LIFE
        CellStatus.ALIVE -> CellStatusDto.ALIVE
        CellStatus.DEAD -> CellStatusDto.DEAD
    }
}