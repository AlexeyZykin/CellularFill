package com.alexisdev.domain.model

data class Cell(
    val id: Long,
    val status: CellStatus
)

enum class CellStatus {
    ALIVE, DEAD, NEW_LIFE
}
