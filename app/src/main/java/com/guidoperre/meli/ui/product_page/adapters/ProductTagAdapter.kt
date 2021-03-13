package com.guidoperre.meli.ui.product_page.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guidoperre.meli.databinding.ItemProductTagBinding
import com.guidoperre.meli.entities.product.result.ProductResult

class ProductTagAdapter: RecyclerView.Adapter<ProductTagViewHolder>() {

    private var list = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductTagViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductTagBinding.inflate(inflater,parent,false)
        return ProductTagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductTagViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setTags(tags: List<String>) {
        list = tags as ArrayList<String>
        notifyDataSetChanged()
    }


}