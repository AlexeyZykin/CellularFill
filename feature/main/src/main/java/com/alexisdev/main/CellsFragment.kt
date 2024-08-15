package com.alexisdev.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.alexisdev.main.databinding.FragmentCellsBinding
import kotlinx.coroutines.launch
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

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCells.layoutManager = LinearLayoutManager(context)
        binding.rvCells.adapter = adapter
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
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
        binding.btCreateCell.setOnClickListener {
            viewModel.onEvent(CellsUiEvent.OnCreateNewCell)
        }
    }
}