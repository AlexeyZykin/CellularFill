package com.alexisdev.data.repository

import com.alexisdev.data.model.CellDto
import com.alexisdev.data.model.toCell
import com.alexisdev.data.model.toCellDto
import com.alexisdev.domain.model.Cell
import com.alexisdev.domain.repository.CellRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map

internal class CellRepositoryImpl : CellRepository {
    private val cells = mutableListOf<CellDto>()
    private val updatedCellsFlow = MutableSharedFlow<List<CellDto>>()

    override fun getAllCells(): Flow<List<Cell>> {
        return updatedCellsFlow.map { it.map { it.toCell() } }
    }

    override suspend fun createCell(newCell: Cell) {
        cells.add(newCell.toCellDto())
        updatedCellsFlow.emit(cells)
    }

    override suspend fun updateCells(updatedCells: List<Cell>) {
        cells.clear()
        cells.addAll(updatedCells.map { it.toCellDto() })
        updatedCellsFlow.emit(cells)
    }
}