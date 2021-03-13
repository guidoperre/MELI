package com.guidoperre.meli.ui.search_preview.adapter

import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.guidoperre.meli.databinding.ItemProductBinding
import com.guidoperre.meli.entities.product.result.ProductResult
import com.guidoperre.meli.utils.MyItemClickListener
import kotlinx.coroutines.*
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.net.URL


class ProductViewHolder(
    private val binding: ItemProductBinding,
    private val listener: MyItemClickListener
): RecyclerView.ViewHolder(binding.root) {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun bind(product: ProductResult) {
        setImage(product)
        setTitle(product)
        setPrice(product)
        setDelivery(product)
        binding.clItem.setOnClickListener {
            listener.onItemClick(product, adapterPosition)
        }
    }

    private fun setImage(product: ProductResult){
        binding.ivPortada.setImageResource(0)
        uiScope.launch {
            val response = withContext(Dispatchers.IO){
                try {
                    val input = URL(product.thumbnail).content as InputStream
                    Drawable.createFromStream(input, "src name")
                } catch (e: Exception) {
                    null
                }
            }
            if (response != null)
                binding.ivPortada.setImageDrawable(response)
        }
    }

    private fun setTitle(product: ProductResult){
        binding.tvTitle.text = product.title
    }

    private fun setPrice(product: ProductResult){
        if (product.price != null){
            val decimalesTotal = product.price % 1
            val totalText = if (decimalesTotal != 0.0)
                "$${String.format("%.2f", product.price)}"
            else
                "$${product.price.toInt()}"
            binding.tvPrice.text = totalText
        }
    }

    private fun setDelivery(product: ProductResult){
        val freeShipping = product.shipping?.freeShipping
        if (freeShipping != null && freeShipping)
            binding.tvEnvio.visibility = View.VISIBLE
        else
            binding.tvEnvio.visibility = View.INVISIBLE
    }


}

