package com.guidoperre.meli.ui.product_page.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guidoperre.meli.databinding.ItemProductBinding
import com.guidoperre.meli.databinding.ItemProductInfoBinding
import com.guidoperre.meli.entities.product.result.Attribute
import com.guidoperre.meli.entities.product.result.ProductResult
import com.guidoperre.meli.utils.MyItemClickListener

class ProductInfoAdapter: RecyclerView.Adapter<ProductInfoViewHolder>() {

    private var list = ArrayList<Attribute>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductInfoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductInfoBinding.inflate(inflater,parent,false)
        return ProductInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductInfoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setInfo(products: List<Attribute>) {
        list = products as ArrayList<Attribute>
        notifyDataSetChanged()
    }


}