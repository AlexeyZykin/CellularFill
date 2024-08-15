package com.alexisdev.data.model

import com.alexisdev.domain.model.CellStatus

enum class CellStatusDto {
    ALIVE, DEAD, NEW_LIFE
}

fun CellStatusDto.toCellStatus(): CellStatus {
    return when (this) {
        CellStatusDto.NEW_LIFE -> CellStatus.NEW_LIFE
        CellStatusDto.ALIVE -> CellStatus.ALIVE
        CellStatusDto.DEAD -> CellStatus.DEAD
    }
}

fun CellStatus.toCellStatusDto(): CellStatusDto {
    return when (this) {
        CellStatus.NEW_LIFE -> CellStatusDto.NEW_LIFE
        CellStatus.ALIVE -> CellStatusDto.ALIVE
        CellStatus.DEAD -> CellStatusDto.DEAD
    }
}