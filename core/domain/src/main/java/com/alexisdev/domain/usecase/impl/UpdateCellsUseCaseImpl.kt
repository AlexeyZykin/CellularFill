package com.alexisdev.domain.usecase.impl

import com.alexisdev.domain.model.Cell
import com.alexisdev.domain.model.CellStatus
import com.alexisdev.domain.repository.CellRepository
import com.alexisdev.domain.usecase.UpdateCellsUseCase
import kotlin.random.Random

private const val REQUIRED_ALIVE_CELLS = 3
private const val REQUIRED_DEAD_CELLS = 3

internal class UpdateCellsUseCaseImpl(private val cellRepository: CellRepository) : UpdateCellsUseCase {
    override suspend fun execute(cellsToUpdate: List<Cell>) {
        val nextIsLife = cellsToUpdate.takeLast(3)
            .count { it.status == CellStatus.ALIVE } == REQUIRED_ALIVE_CELLS
        val nextIsDead =
            cellsToUpdate.takeLast(3).count { it.status == CellStatus.DEAD } == REQUIRED_DEAD_CELLS

        val newList = cellsToUpdate.toMutableList()
        when {
            nextIsLife -> {
                newList.add(
                    Cell(id = Random.nextLong(), status = CellStatus.NEW_LIFE)
                )
                cellRepository.updateCells(newList.toList())
            }
            nextIsDead -> {
                cellsToUpdate
                    .findLast { it.status == CellStatus.NEW_LIFE }
                    ?.let { newList.remove(it) }
                cellRepository.updateCells(newList.toList())
            }
        }
    }
}