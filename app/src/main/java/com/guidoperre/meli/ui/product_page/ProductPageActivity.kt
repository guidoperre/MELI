package com.guidoperre.meli.ui.product_page

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.guidoperre.meli.R
import com.guidoperre.meli.databinding.ActivityProductPageBinding
import com.guidoperre.meli.entities.product.result.ProductResult
import com.guidoperre.meli.ui.search_preview.SearchPreviewActivity
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class ProductPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductPageBinding
    private val model: ProductPageViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_page)
        binding.lifecycleOwner = this

        setActionBar()
        getItem()
    }

    private fun getItem(){
        val productJson = intent.extras?.getString("product")
        try {
            val product = Gson().fromJson(productJson, ProductResult::class.java)
            if (product != null){
                setItemBasic(product)
                setPrice(product)
                setItemTags(product)
                setStock(product)
                setProductInformation(product)
                setSellerInformation(product)
            } else
                Toast.makeText(
                        this,
                        "Ocurrio un error, intente de nuevo mas tarde",
                        Toast.LENGTH_SHORT
                ).show()
        } catch (e: JsonSyntaxException) {
            Toast.makeText(
                    this,
                    "Ocurrio un error, intente de nuevo mas tarde",
                    Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setItemBasic(product: ProductResult){
        val vendidos = "${product.soldQuantity} vendidos"
        val condition = if (product.condition != null && product.condition == "new")
            "Nuevo"
        else if (product.condition != null)
            "Usado"
        else
            "null"
        binding.tvCondicion.text = condition
        binding.tvVendidos.text = vendidos
        binding.tvTitulo.text = product.title
    }

    private fun setPictures(){

    }

    private fun setReviews(){

    }

    private fun setPrice(product: ProductResult){
        if(product.price != null){
            val decimalesTotal = product.price % 1
            val totalText = if (decimalesTotal != 0.0)
                "$ ${String.format("%.2f", product.price)}"
            else
                "$ ${product.price.toInt()}"
            binding.tvPrecio.text = totalText
        }
    }

    private fun setItemTags(product: ProductResult){

    }

    private fun setStock(product: ProductResult){
        val stock = "${product.availableQuantity} disponibles"
        binding.tvStockValue.text = stock
    }

    private fun setDescription(){

    }

    private fun setProductInformation(product: ProductResult){

    }

    private fun setSellerInformation(product: ProductResult){
        val address = "${product.address?.cityName}, ${product.address?.stateName}"
        val status =
            "Mercadolibre ${product.seller?.sellerReputation?.powerSellerStatus?.
            capitalize(Locale.ROOT)}"
        binding.tvVendedorUbicacion.text = address
        binding.tvVendedorVentas.text = product.seller?.sellerReputation?.metric?.sales?.completed
                .toString()
        binding.tvVendedorGrado.text = status
    }

    private fun setQuestions(){

    }

    private fun setActionBar(){
        val myToolbar: Toolbar = binding.tbProductPage
        setSupportActionBar(myToolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayUseLogoEnabled(false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return true
    }

    override fun onBackPressed() {
        val query = intent.getStringExtra("query")
        val intent = Intent(this, SearchPreviewActivity::class.java)
        intent.putExtra("query", query)
        startActivity(intent)
        overridePendingTransition(R.transition.fade_in, R.transition.fade_out)
        finish()
    }

}