package com.alexisdev.data.di

import com.alexisdev.data.repository.CellRepositoryImpl
import com.alexisdev.domain.repository.CellRepository
import org.koin.dsl.module

val dataModule = module {
    single<CellRepository> { CellRepositoryImpl() }
}