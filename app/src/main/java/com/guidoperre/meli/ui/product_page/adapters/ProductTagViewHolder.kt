package com.guidoperre.meli.ui.product_page.adapters

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.guidoperre.meli.R
import com.guidoperre.meli.databinding.ItemProductTagBinding


class ProductTagViewHolder(
    private val binding: ItemProductTagBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(tag: String) {
        when (tag){
            "mp" -> {
                binding.tvProductTag.text = binding.root.resources.getString(R.string.mercadopago)
                binding.tvProductTag.backgroundTintList = ContextCompat
                    .getColorStateList(binding.root.context, R.color.azul_500)
            }
            "eg" -> {
                binding.tvProductTag.text = binding.root.resources.getString(R.string.envio_gratis)
                binding.tvProductTag.backgroundTintList = ContextCompat
                    .getColorStateList(binding.root.context, R.color.verde_500)
            }
            else -> {
                binding.tvProductTag.text = ""
                binding.tvProductTag.backgroundTintList = ContextCompat
                    .getColorStateList(binding.root.context, R.color.white)
            }
        }
    }


}

