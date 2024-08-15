package com.alexisdev.domain.usecase

import com.alexisdev.domain.model.Cell

interface UpdateCellsUseCase {
    suspend fun execute(cellsToUpdate: List<Cell>)
}