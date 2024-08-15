package com.alexisdev.data.repository

import com.alexisdev.data.model.CellDto
import com.alexisdev.data.model.toCell
import com.alexisdev.data.model.toCellDto
import com.alexisdev.domain.model.Cell
import com.alexisdev.domain.repository.CellRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class CellRepositoryImpl : CellRepository {
    private val cells = mutableListOf<CellDto>()

    override fun getAllCells(): Flow<List<Cell>> {
        return flowOf(cells.map { it.toCell() })
    }

    override fun createCell(newCell: Cell) {
        cells.add(newCell.toCellDto())
    }

    override fun updateCells(updatedCells: List<Cell>) {
        cells.clear()
        cells.addAll(updatedCells.map { it.toCellDto() })
    }
}