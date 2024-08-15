package com.alexisdev.domain.usecase

import com.alexisdev.domain.model.Cell

interface UpdateCellsUseCase {
    fun execute(cellsToUpdate: List<Cell>)
}