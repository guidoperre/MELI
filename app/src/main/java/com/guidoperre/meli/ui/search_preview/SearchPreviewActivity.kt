package com.guidoperre.meli.ui.search_preview

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.guidoperre.meli.R
import com.guidoperre.meli.databinding.ActivitySearchPreviewBinding
import com.guidoperre.meli.entities.product.ProductSearch
import com.guidoperre.meli.entities.product.result.ProductResult
import com.guidoperre.meli.ui.home.HomeActivity
import com.guidoperre.meli.ui.product_page.ProductPageActivity
import com.guidoperre.meli.ui.search.SearchActivity
import com.guidoperre.meli.ui.search_preview.adapter.ProductAdapter
import com.guidoperre.meli.utils.MyItemClickListener
import org.koin.android.viewmodel.ext.android.viewModel

class SearchPreviewActivity : AppCompatActivity(), MyItemClickListener {

    private lateinit var binding: ActivitySearchPreviewBinding
    private val model: SearchPreviewViewModel by viewModel()

    private val adapter = ProductAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_preview)
        binding.lifecycleOwner = this
        binding.activity = this
        binding.model = model
        binding.loading = binding.pbLoading

        setObservables()
        switchListener()
        initList()
        pullRefresh()
    }

    private fun initList(){
        val list = binding.rvSearch
        list.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
        list.adapter = adapter
        list.setHasFixedSize(true)
        model.offsetHandler.value = 0
    }

    private fun setObservables() {
        model.productsHandler.observe(this, {
            binding.srlSearch.isRefreshing = false
            binding.pbLoading.visibility = View.INVISIBLE
            binding.swtEnvio.isClickable = true
            if (it != null)
                setItems(it)
            else
                Toast.makeText(
                    this,
                    "Ocurrio un error, realize otra busqueda o intente de vuelta.",
                    Toast.LENGTH_SHORT
                ).show()
        })

        /*
       El offest establece la pagina de referencia
       */
        model.offsetHandler.observe(this, {
            adapter.setProducts(ArrayList())
            setQuery()
            setPage(it)
            if (it > 0)
                binding.ivBackPage.visibility = View.VISIBLE
            else
                binding.ivBackPage.visibility = View.INVISIBLE
            if (model.total - it > 50 || model.total == 0)
                binding.ivNextPage.visibility = View.VISIBLE
            else
                binding.ivNextPage.visibility = View.INVISIBLE
        })
    }

    private fun switchListener(){
        binding.swtEnvio.setOnCheckedChangeListener { _, isChecked ->
            val products = model.productsHandler.value?.results
            if (products != null) {
                if (isChecked)
                    adapter.setProducts(getEnvioGratis(products))
                else
                    adapter.setProducts(products)
            } else
                Toast.makeText(
                    this,
                    "Ocurrio un error, intente de vuelta.",
                    Toast.LENGTH_SHORT
                ).show()
        }
    }

    private fun setQuery(){
        val query = intent.getStringExtra("query")
        if (query != null){
            //TODO: Cambiar siteId a codigo de pais cuando este implementado
            model.getProducts("MLA", query)
            binding.tvSearch.text = query
            binding.tvSinResultados.visibility = View.INVISIBLE
            binding.pbLoading.visibility = View.VISIBLE
            binding.swtEnvio.isClickable = false
        } else
            Toast.makeText(
                this,
                "Ocurrio un error, realize otra busqueda o intente de vuelta.",
                Toast.LENGTH_SHORT
            ).show()
    }

    private fun setPage(offset: Int){
        val page = (offset / 50) + 1
        binding.tvNumeroPagina.text = page.toString()
    }

    private fun setItems(products: ProductSearch){
        if (products.results != null && products.results.isNotEmpty()){
            if (products.paging?.total != null) {
                val resultados = "${products.paging.total} resultados"
                model.total = products.paging.total
                binding.tvResultados.text = resultados
                if (model.total < 50)
                    binding.ivNextPage.visibility = View.INVISIBLE
            }
            binding.tvSinResultados.visibility = View.INVISIBLE
            if (binding.swtEnvio.isChecked)
                adapter.setProducts(getEnvioGratis(products.results))
            else
                adapter.setProducts(products.results)
        } else {
            adapter.setProducts(ArrayList())
            binding.tvSinResultados.visibility = View.VISIBLE
        }
    }

    private fun getEnvioGratis(products: List<ProductResult>): List<ProductResult>{
        val envioGratis = ArrayList<ProductResult>()
        for (product in products)
            if (product.shipping?.freeShipping != null && product.shipping.freeShipping)
                envioGratis.add(product)
        return envioGratis
    }

    private fun pullRefresh() {
        binding.srlSearch.setColorSchemeColors(
            ContextCompat.getColor(
                this,
                R.color.amarillo_500
            )
        )
        binding.srlSearch.setOnRefreshListener {
            setQuery()
            binding.pbLoading.visibility = View.INVISIBLE
        }
    }

    fun goSearch() {
        val query = intent.getStringExtra("query")
        val intent = Intent(this, SearchActivity::class.java)
        intent.putExtra("query", query)
        startActivity(intent)
        overridePendingTransition(R.transition.fade_in, R.transition.fade_out)
    }

    override fun onItemClick(item: Any, position: Int) {
        val product = Gson().toJson(item as ProductResult)
        val query = intent.getStringExtra("query")
        val intent = Intent(this, ProductPageActivity::class.java)
        intent.putExtra("query", query)
        intent.putExtra("product", product)
        startActivity(intent)
        overridePendingTransition(R.transition.fade_in, R.transition.fade_out)
        finish()
    }

    override fun onBackPressed() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.transition.fade_in, R.transition.fade_out)
        finish()
    }

}