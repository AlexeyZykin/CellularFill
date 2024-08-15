package com.alexisdev.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexisdev.domain.usecase.CreateCellUseCase
import com.alexisdev.domain.usecase.GetAllCellsUseCase
import com.alexisdev.domain.usecase.UpdateCellsUseCase
import com.alexisdev.main.model.CellUi
import com.alexisdev.main.model.toCell
import com.alexisdev.main.model.toCellUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CellsViewModel(
    private val createCellUseCase: CreateCellUseCase,
    private val getAllCellsUseCase: GetAllCellsUseCase,
    private val updateCellsUseCase: UpdateCellsUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<CellsUiState> = MutableStateFlow(CellsUiState.Loading)
    private val uiState: StateFlow<CellsUiState> = _uiState

    init {
        getAllCellsUseCase.execute().onEach { cells ->
            _uiState.update {
                CellsUiState.Content(cells = cells.map { it.toCellUi() })
            }
        }.launchIn(viewModelScope)
        _uiState.onEach {
            (it as? CellsUiState.Content)?.let { content ->
                updateCellsUseCase.execute(content.cells.map { cell -> cell.toCell() })
            }
        }.launchIn(viewModelScope)
    }

    fun getCellsUiState(): StateFlow<CellsUiState> = uiState

    fun onEvent(event: CellsUiEvent) {
        when (event) {
            is CellsUiEvent.OnCreateNewCell -> {
                handleOnCreateNewCell()
            }
        }
    }

    private fun handleOnCreateNewCell() = viewModelScope.launch {
        createCellUseCase.execute()
    }

}

sealed interface CellsUiState {
    data object Loading : CellsUiState
    data class Content(val cells: List<CellUi>) : CellsUiState
    data class Error(val msg: String) : CellsUiState
}

sealed interface CellsUiEvent {
    data object OnCreateNewCell : CellsUiEvent
}