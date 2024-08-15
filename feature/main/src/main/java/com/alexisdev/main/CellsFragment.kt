package com.alexisdev.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexisdev.main.databinding.FragmentCellsBinding
import com.alexisdev.main.utils.launchUntilPaused
import org.koin.androidx.viewmodel.ext.android.viewModel

class CellsFragment : Fragment() {
    private lateinit var binding: FragmentCellsBinding
    private val viewModel by viewModel<CellsViewModel>()
    private val adapter: CellAdapter by lazy {
        CellAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCellsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCells.layoutManager = LinearLayoutManager(context)
        binding.rvCells.adapter = adapter
        binding.btCreateCell.setOnClickListener {
            viewModel.onEvent(CellsUiEvent.OnCreateNewCell)
        }
        lifecycleScope.launchUntilPaused(this) {
            viewModel.getCellsUiState().collect {
                when (it) {
                    is CellsUiState.Loading -> {}
                    is CellsUiState.Error -> {}
                    is CellsUiState.Content -> {
                        adapter.map(it.cells)
                    }
                }
            }
        }
    }
}


