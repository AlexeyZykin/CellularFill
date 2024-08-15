package com.alexisdev.domain.di

import com.alexisdev.domain.usecase.CreateCellUseCase
import com.alexisdev.domain.usecase.GetAllCellsUseCase
import com.alexisdev.domain.usecase.UpdateCellsUseCase
import com.alexisdev.domain.usecase.impl.CreateCellUseCaseImpl
import com.alexisdev.domain.usecase.impl.GetAllCellsUseCaseImpl
import com.alexisdev.domain.usecase.impl.UpdateCellsUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    single<CreateCellUseCase> { CreateCellUseCaseImpl(get()) }
    single<GetAllCellsUseCase> { GetAllCellsUseCaseImpl(get()) }
    single<UpdateCellsUseCase> { UpdateCellsUseCaseImpl(get()) }
}