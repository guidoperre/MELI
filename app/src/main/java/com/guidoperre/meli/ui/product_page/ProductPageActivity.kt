package com.guidoperre.meli.ui.product_page

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.guidoperre.meli.R
import com.guidoperre.meli.databinding.ActivityProductPageBinding
import com.guidoperre.meli.entities.product.result.*
import com.guidoperre.meli.ui.product_page.adapters.ImagesAdapter
import com.guidoperre.meli.ui.product_page.adapters.ProductInfoAdapter
import com.guidoperre.meli.ui.product_page.adapters.ProductTagAdapter
import com.guidoperre.meli.ui.product_page.adapters.QuestionAdapter
import com.guidoperre.meli.ui.search_preview.SearchPreviewActivity
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList

class ProductPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductPageBinding
    private val model: ProductPageViewModel by viewModel()

    private val tagsAdapter = ProductTagAdapter()
    private val infoAdapter = ProductInfoAdapter()
    private val questionAdapter = QuestionAdapter()
    private val imagesAdapter = ImagesAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_page)
        binding.lifecycleOwner = this

        initTagList()
        initInfoList()
        initQuestionList()
        initImagePager()
        setActionBar()
        setObservables()
        getItem()
    }

    private fun initTagList(){
        val list = binding.rvProductTag
        list.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
        list.adapter = tagsAdapter
        list.setHasFixedSize(true)
    }

    private fun initInfoList(){
        val list = binding.rvProductInfo
        list.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
        list.adapter = infoAdapter
        list.setHasFixedSize(true)
    }

    private fun initQuestionList(){
        val list = binding.rvPreguntas
        list.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
        list.adapter = questionAdapter
        list.setHasFixedSize(true)
    }

    private fun initImagePager(){
        val pager = binding.pgFoto
        pager.adapter = imagesAdapter
    }

    private fun setObservables(){
        model.picturesHandler.observe(this, {
            setPictures(it)
        })

        model.reviewsHandler.observe(this, {
            if (it != null)
                setReviews(it)
        })

        model.descriptionHandler.observe(this, {
            setDescription(it)
        })

        model.questionsHandler.observe(this, {
            setQuestions(it)
        })
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
                if (product.id != null) {
                    model.getPictures(product.id)
                    model.getReviews(product.id)
                    model.getDescription(product.id)
                    model.getQuestions(product.id)
                }
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

    private fun setPictures(pictures: List<Drawable>?){
        if (pictures != null)
            imagesAdapter.setImages(pictures)
    }

    private fun setReviews(review: Review){
        val reviewsAmount = "(${review.paging?.total})"
        if (review.ratingAverage != null) {
            when {
                review.ratingAverage == 5.0 -> {
                    binding.ivEstrella1.setImageResource(R.mipmap.ic_estrella_azul_foreground)
                    binding.ivEstrella2.setImageResource(R.mipmap.ic_estrella_azul_foreground)
                    binding.ivEstrella3.setImageResource(R.mipmap.ic_estrella_azul_foreground)
                    binding.ivEstrella4.setImageResource(R.mipmap.ic_estrella_azul_foreground)
                    binding.ivEstrella5.setImageResource(R.mipmap.ic_estrella_azul_foreground)
                }
                review.ratingAverage < 1 -> {
                    binding.ivEstrella1.setImageResource(R.mipmap.ic_estrella_gris_foreground)
                    binding.ivEstrella2.setImageResource(R.mipmap.ic_estrella_gris_foreground)
                    binding.ivEstrella3.setImageResource(R.mipmap.ic_estrella_gris_foreground)
                    binding.ivEstrella4.setImageResource(R.mipmap.ic_estrella_gris_foreground)
                    binding.ivEstrella5.setImageResource(R.mipmap.ic_estrella_gris_foreground)
                }
                review.ratingAverage < 2 -> {
                    binding.ivEstrella1.setImageResource(R.mipmap.ic_estrella_azul_foreground)
                    binding.ivEstrella2.setImageResource(R.mipmap.ic_estrella_gris_foreground)
                    binding.ivEstrella3.setImageResource(R.mipmap.ic_estrella_gris_foreground)
                    binding.ivEstrella4.setImageResource(R.mipmap.ic_estrella_gris_foreground)
                    binding.ivEstrella5.setImageResource(R.mipmap.ic_estrella_gris_foreground)
                }
                review.ratingAverage < 3 -> {
                    binding.ivEstrella1.setImageResource(R.mipmap.ic_estrella_azul_foreground)
                    binding.ivEstrella2.setImageResource(R.mipmap.ic_estrella_azul_foreground)
                    binding.ivEstrella3.setImageResource(R.mipmap.ic_estrella_gris_foreground)
                    binding.ivEstrella4.setImageResource(R.mipmap.ic_estrella_gris_foreground)
                    binding.ivEstrella5.setImageResource(R.mipmap.ic_estrella_gris_foreground)
                }
                review.ratingAverage < 4 -> {
                    binding.ivEstrella1.setImageResource(R.mipmap.ic_estrella_azul_foreground)
                    binding.ivEstrella2.setImageResource(R.mipmap.ic_estrella_azul_foreground)
                    binding.ivEstrella3.setImageResource(R.mipmap.ic_estrella_azul_foreground)
                    binding.ivEstrella4.setImageResource(R.mipmap.ic_estrella_gris_foreground)
                    binding.ivEstrella5.setImageResource(R.mipmap.ic_estrella_gris_foreground)
                }
                review.ratingAverage < 5 -> {
                    binding.ivEstrella1.setImageResource(R.mipmap.ic_estrella_azul_foreground)
                    binding.ivEstrella2.setImageResource(R.mipmap.ic_estrella_azul_foreground)
                    binding.ivEstrella3.setImageResource(R.mipmap.ic_estrella_azul_foreground)
                    binding.ivEstrella4.setImageResource(R.mipmap.ic_estrella_azul_foreground)
                    binding.ivEstrella5.setImageResource(R.mipmap.ic_estrella_gris_foreground)
                }
            }
        }
        binding.tvReviews.text = reviewsAmount
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
        val tags = ArrayList<String>()
        if (product.acceptsMercadopago != null && product.acceptsMercadopago)
            tags.add("mp")
        if (product.shipping?.freeShipping != null && product.shipping.freeShipping)
            tags.add("eg")
        tagsAdapter.setTags(tags)
    }

    private fun setStock(product: ProductResult){
        val stock = "${product.availableQuantity} disponibles"
        binding.tvStockValue.text = stock
    }

    private fun setDescription(description: Description?){
        if (description?.text != null && description.text != "")
            binding.tvDescriptionValue.text = description.text
        else
            binding.tvDescriptionValue.text = resources.getString(R.string.sin_descripcion)
    }

    private fun setProductInformation(product: ProductResult){
        if (product.attributes != null && product.attributes.isNotEmpty()){
            binding.tvSinInformacion.visibility = View.INVISIBLE
            binding.rvProductInfo.visibility = View.VISIBLE
            infoAdapter.setInfo(product.attributes)
        } else {
            binding.tvSinInformacion.visibility = View.VISIBLE
            binding.rvProductInfo.visibility = View.INVISIBLE
        }
    }

    private fun setSellerInformation(product: ProductResult){
        val address = "${product.address?.cityName}, ${product.address?.stateName}"
        val status = if (product.seller?.sellerReputation?.powerSellerStatus != null)
            "Mercadolibre ${product.seller.sellerReputation.powerSellerStatus.capitalize(
                Locale.ROOT
            )}"
        else
            "Todavia no tiene rango"
        binding.tvVendedorUbicacion.text = address
        binding.tvVendedorVentas.text = product.seller?.sellerReputation?.metric?.sales?.completed
                .toString()
        binding.tvVendedorGrado.text = status
    }

    private fun setQuestions(productQuestion: ProductQuestion?){
        if (productQuestion?.questions != null && productQuestion.questions.isNotEmpty()){
            val answered = ArrayList<Question>()
            var limit = 0
            for (question in productQuestion.questions)
                if (question.status == "ANSWERED" && limit < 5){
                    answered.add(question)
                    limit++
                }
            if (answered.isNotEmpty()){
                binding.tvSinPreguntas.visibility = View.INVISIBLE
                binding.rvPreguntas.visibility = View.VISIBLE
                questionAdapter.setQuestions(answered)
            } else {
                binding.tvSinPreguntas.visibility = View.VISIBLE
                binding.rvPreguntas.visibility = View.INVISIBLE
            }
        } else {
            binding.tvSinPreguntas.visibility = View.VISIBLE
            binding.rvPreguntas.visibility = View.INVISIBLE
        }
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