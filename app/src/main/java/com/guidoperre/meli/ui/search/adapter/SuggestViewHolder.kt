package com.guidoperre.meli.ui.search.adapter

import androidx.recyclerview.widget.RecyclerView
import com.guidoperre.meli.R
import com.guidoperre.meli.databinding.ItemSearchBinding
import com.guidoperre.meli.entities.search.Search
import com.guidoperre.meli.utils.MyItemClickListener

class SuggestViewHolder(
        private val binding: ItemSearchBinding,
        private val listener: MyItemClickListener
): RecyclerView.ViewHolder(binding.root) {

    fun bind(suggest: Search) {
        binding.tvTitle.text = suggest.name
        if (suggest.history)
            binding.ivLupa.setImageResource(R.mipmap.ic_reloj_foreground)
        else
            binding.ivLupa.setImageResource(R.mipmap.ic_lupita_foreground)
        binding.clItem.setOnClickListener {
            listener.onItemClick(suggest, adapterPosition)
        }
    }

}

