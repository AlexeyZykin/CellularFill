package com.alexisdev.domain.repository

import com.alexisdev.domain.model.Cell
import kotlinx.coroutines.flow.Flow

interface CellRepository {
    fun getAllCells(): Flow<List<Cell>>
    suspend fun createCell(newCell: Cell)
    suspend fun updateCells(updatedCells: List<Cell>)
}