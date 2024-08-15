package com.alexisdev.domain.repository

import com.alexisdev.domain.model.Cell
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CellRepositoryImpl : CellRepository {
    private val cells = mutableListOf<Cell>()

    override fun getAllCells(): Flow<List<Cell>> {
        return flowOf(cells)
    }

    override fun createCell(newCell: Cell) {

    }

    override fun updateCells(updatedCells: List<Cell>) {
        cells.clear()
        cells.addAll(updatedCells)
    }
}