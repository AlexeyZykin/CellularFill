package com.alexisdev.domain.usecase.impl

import com.alexisdev.domain.model.Cell
import com.alexisdev.domain.repository.CellRepository
import com.alexisdev.domain.usecase.GetAllCellsUseCase
import kotlinx.coroutines.flow.Flow

internal class GetAllCellsUseCaseImpl(private val cellRepository: CellRepository) :
    GetAllCellsUseCase {
    override fun execute(): Flow<List<Cell>> {
        return cellRepository.getAllCells()
    }
}