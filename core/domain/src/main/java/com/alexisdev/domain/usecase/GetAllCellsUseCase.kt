package com.alexisdev.domain.usecase

import com.alexisdev.domain.model.Cell
import kotlinx.coroutines.flow.Flow

interface GetAllCellsUseCase {
    fun execute(): Flow<List<Cell>>
}