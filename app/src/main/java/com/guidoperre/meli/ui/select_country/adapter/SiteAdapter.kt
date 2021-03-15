package com.guidoperre.meli.ui.select_country.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guidoperre.meli.databinding.ItemCountryBinding
import com.guidoperre.meli.entities.sites.Site
import com.guidoperre.meli.utils.MyItemClickListener
import kotlin.collections.ArrayList

class SiteAdapter(
        private val listener: MyItemClickListener
): RecyclerView.Adapter<SiteViewHolder>() {

    private var list = ArrayList<Site>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCountryBinding.inflate(inflater,parent,false)
        return SiteViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: SiteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setSites(sites: List<Site>) {
        val sortedList = sortSites(sites)
        list = sortedList
        notifyDataSetChanged()
    }

    private fun sortSites(sites: List<Site>): ArrayList<Site>{
        val sortedList = ArrayList<Site>()
        val rawSorted = sites.sortedBy { it.name }
        for (site in rawSorted)
            sortedList.add(site)
        return sortedList
    }

}