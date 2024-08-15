package com.alexisdev.domain.usecase

import com.alexisdev.domain.model.Cell

interface CreateCellUseCase {
    suspend fun execute()
}