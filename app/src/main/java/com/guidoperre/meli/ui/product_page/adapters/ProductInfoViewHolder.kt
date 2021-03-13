package com.guidoperre.meli.ui.product_page.adapters

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.guidoperre.meli.R
import com.guidoperre.meli.databinding.ItemProductInfoBinding
import com.guidoperre.meli.entities.product.result.Attribute

class ProductInfoViewHolder(
    private val binding: ItemProductInfoBinding
): RecyclerView.ViewHolder(binding.root) {


    fun bind(attribute: Attribute) {
        binding.tvAttributeTitle.text = attribute.name
        binding.tvAttributeValue.text = attribute.valueName
        if (adapterPosition % 2 != 0){
            binding.tvAttributeTitle.backgroundTintList = ContextCompat
                .getColorStateList(binding.root.context, R.color.gris_300)
            binding.tvAttributeValue.backgroundTintList = ContextCompat
                .getColorStateList(binding.root.context, R.color.gris_100)
        } else {
            binding.tvAttributeTitle.backgroundTintList = ContextCompat
                .getColorStateList(binding.root.context, R.color.gris_400)
            binding.tvAttributeValue.backgroundTintList = ContextCompat
                .getColorStateList(binding.root.context, R.color.gris_200)
        }
    }


}

