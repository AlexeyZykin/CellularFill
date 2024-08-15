package com.alexisdev.domain.usecase.impl

import com.alexisdev.domain.model.Cell
import com.alexisdev.domain.model.CellStatus
import com.alexisdev.domain.repository.CellRepository
import com.alexisdev.domain.usecase.CreateCellUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlin.random.Random



internal class CreateCellUseCaseImpl(private val cellRepository: CellRepository) :
    CreateCellUseCase {
    override fun execute() {
        cellRepository.createCell(
            Cell(
                id = Random.nextLong(),
                status = if (Random.nextBoolean()) { CellStatus.ALIVE } else { CellStatus.DEAD }
            )
        )
    }
}