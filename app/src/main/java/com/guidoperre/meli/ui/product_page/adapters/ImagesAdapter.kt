package com.guidoperre.meli.ui.product_page.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.guidoperre.meli.R

class ImagesAdapter(
    private val context:Context
): PagerAdapter() {

    private var list = ArrayList<Drawable>()

    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_product_picture, container,false)
        val positionText = "${position}/${count}"
        val ivPicture = view.findViewById<ImageView>(R.id.iv_picture)
        val tvCantidadFotos = view.findViewById<TextView>(R.id.tv_cantidad_fotos)
        ivPicture.setImageDrawable(list[position])
        tvCantidadFotos.text = positionText
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any){
        container.removeView(`object` as View)
    }

    fun setImages(images: List<Drawable>){
        list = images as ArrayList<Drawable>
        notifyDataSetChanged()
    }

}