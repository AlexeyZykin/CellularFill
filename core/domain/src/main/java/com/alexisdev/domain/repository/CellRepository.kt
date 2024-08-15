package com.alexisdev.domain.repository

import com.alexisdev.domain.model.Cell
import kotlinx.coroutines.flow.Flow

interface CellRepository {
    fun getAllCells(): Flow<List<Cell>>
    fun createCell(newCell: Cell)
    fun updateCells(updatedCells: List<Cell>)
}