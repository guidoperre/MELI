package com.guidoperre.meli.ui.search.adapter

import androidx.recyclerview.widget.RecyclerView
import com.guidoperre.meli.databinding.ItemSearchBinding
import com.guidoperre.meli.utils.MyItemClickListener

class SuggestViewHolder(
        private val binding: ItemSearchBinding,
        private val listener: MyItemClickListener
): RecyclerView.ViewHolder(binding.root) {

    fun bind(suggest: String) {
        binding.tvTitle.text = suggest
        binding.clItem.setOnClickListener {
            listener.onItemClick(suggest, adapterPosition)
        }
    }

}

