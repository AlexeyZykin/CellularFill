package com.alexisdev.main.di

import com.alexisdev.main.CellsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val mainFeatureModule = module {
    viewModelOf(::CellsViewModel)
}