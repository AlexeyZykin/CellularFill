package com.alexisdev.data.model

import com.alexisdev.domain.model.Cell

data class CellDto(
    val id: Long,
    val status: CellStatusDto
)

fun CellDto.toCell() = Cell(id = id, status = status.toCellStatus())

fun Cell.toCellDto() = CellDto(id = id, status = status.toCellStatusDto())

