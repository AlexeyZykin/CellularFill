package com.alexisdev.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexisdev.domain.model.CellStatus
import com.alexisdev.main.databinding.CellItemBinding
import com.alexisdev.main.model.CellStatusUi
import com.alexisdev.main.model.CellUi
import com.alexisdev.main.utils.Mapper
import com.bumptech.glide.Glide

class CellAdapter : RecyclerView.Adapter<CellAdapter.CellViewHolder>(), Mapper<List<CellUi>> {
    private val list = mutableListOf<CellUi>()

    class CellViewHolder(private val binding: CellItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cellUi: CellUi) {
            val context = binding.root.context
            binding.tvCellTitle.text = context.getString(cellUi.status.title)
            binding.tvCellDesc.text = context.getString(cellUi.status.description)
            Glide
                .with(context)
                .load(cellUi.status.imageRes)
                .centerCrop()
                .into(binding.imgCellItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
        val binding = CellItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CellViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun map(source: List<CellUi>) {
        val diff = DiffUtil(list, source)
        val result = androidx.recyclerview.widget.DiffUtil.calculateDiff(diff)
        list.clear()
        list.addAll(source)
        result.dispatchUpdatesTo(this)
    }
    class DiffUtil(
        private val oldList: List<CellUi>,
        private val newList: List<CellUi>
    ) : androidx.recyclerview.widget.DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size


        override fun getNewListSize() = newList.size


        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}