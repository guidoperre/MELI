package com.guidoperre.meli.ui.select_country.adapter

import androidx.recyclerview.widget.RecyclerView
import com.guidoperre.meli.R
import com.guidoperre.meli.databinding.ItemCountryBinding
import com.guidoperre.meli.entities.sites.Site
import com.guidoperre.meli.utils.MyItemClickListener

class SiteViewHolder(
    private val binding: ItemCountryBinding,
    private val listener: MyItemClickListener
): RecyclerView.ViewHolder(binding.root) {

    companion object {
        var selectedSiteId = ""
    }

    fun bind(site: Site) {
        setSite(site)
        setCheckBox(site)
        binding.clItem.setOnClickListener {
            listener.onItemClick(site, adapterPosition)
        }
    }

    private fun setSite(site: Site) {
        binding.tvCountryName.text = site.name
        when(site.name) {
            "Perú" -> binding.ivCountryIcon.setImageResource(R.mipmap.ic_peru_foreground)
            "Mexico" -> binding.ivCountryIcon.setImageResource(R.mipmap.ic_mexico_foreground)
            "Dominicana" -> binding.ivCountryIcon.setImageResource(R.mipmap.ic_dominicana_foreground)
            "Brasil" -> binding.ivCountryIcon.setImageResource(R.mipmap.ic_brasil_foreground)
            "Costa Rica" -> binding.ivCountryIcon.setImageResource(R.mipmap.ic_costa_rica_foreground)
            "Colombia" -> binding.ivCountryIcon.setImageResource(R.mipmap.ic_colombia_foreground)
            "Chile" -> binding.ivCountryIcon.setImageResource(R.mipmap.ic_chile_foreground)
            "Cuba" -> binding.ivCountryIcon.setImageResource(R.mipmap.ic_cuba_foreground)
            "Honduras" -> binding.ivCountryIcon.setImageResource(R.mipmap.ic_honduras_foreground)
            "Panamá" -> binding.ivCountryIcon.setImageResource(R.mipmap.ic_panama_foreground)
            "Guatemala" -> binding.ivCountryIcon.setImageResource(R.mipmap.ic_guatemala_foreground)
            "Argentina" -> binding.ivCountryIcon.setImageResource(R.mipmap.ic_argentina_foreground)
            "Paraguay" -> binding.ivCountryIcon.setImageResource(R.mipmap.ic_paraguay_foreground)
            "Bolivia" -> binding.ivCountryIcon.setImageResource(R.mipmap.ic_bolivia_foreground)
            "El Salvador" -> binding.ivCountryIcon.setImageResource(R.mipmap.ic_salvador_foreground)
            "Ecuador" -> binding.ivCountryIcon.setImageResource(R.mipmap.ic_ecuador_foreground)
            "Nicaragua" -> binding.ivCountryIcon.setImageResource(R.mipmap.ic_nicaragua_foreground)
            "Venezuela" -> binding.ivCountryIcon.setImageResource(R.mipmap.ic_venezuela_foreground)
            "Uruguay" -> binding.ivCountryIcon.setImageResource(R.mipmap.ic_uruguay_foreground)
            else -> binding.ivCountryIcon.setImageResource(0)
        }
    }

    private fun setCheckBox(site: Site) {
        binding.cbCountry.isChecked = site.id == selectedSiteId
    }

}

