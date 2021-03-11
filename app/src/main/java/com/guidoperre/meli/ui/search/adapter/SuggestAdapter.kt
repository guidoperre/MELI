package com.guidoperre.meli.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guidoperre.meli.databinding.ItemSearchBinding
import com.guidoperre.meli.utils.MyItemClickListener

class SuggestAdapter(
        private val listener: MyItemClickListener
): RecyclerView.Adapter<SuggestViewHolder>() {

    private var list = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchBinding.inflate(inflater,parent,false)
        return SuggestViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: SuggestViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setSuggests(suggests: List<String>) {
        list = suggests as ArrayList<String>
        notifyDataSetChanged()
    }


}