package com.guidoperre.meli.ui.search_preview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guidoperre.meli.databinding.ItemProductBinding
import com.guidoperre.meli.entities.product.result.ProductResult
import com.guidoperre.meli.utils.MyItemClickListener

class ProductAdapter(
        private val listener: MyItemClickListener
): RecyclerView.Adapter<ProductViewHolder>() {

    private var list = ArrayList<ProductResult>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater,parent,false)
        return ProductViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setProducts(products: List<ProductResult>) {
        list = products as ArrayList<ProductResult>
        notifyDataSetChanged()
    }


}