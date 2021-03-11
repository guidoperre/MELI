package com.guidoperre.meli.ui.search_preview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guidoperre.meli.R
import com.guidoperre.meli.databinding.ActivitySearchPreviewBinding
import com.guidoperre.meli.entities.product.result.ProductResult
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

        initList()
        setObservables()
        setQuery()
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
    }

    private fun setObservables() {
        model.productsHandler.observe(this, {
            binding.srlSearch.isRefreshing = false
            binding.pbLoading.visibility = View.INVISIBLE
            if (it != null) {
                if (it.results != null && it.results.isNotEmpty())
                    setItems(it.results)
                else
                    binding.tvSinResultados.visibility = View.VISIBLE
            } else
                Toast.makeText(
                    this,
                    "Ocurrio un error, realize otra busqueda e intente de vuelta.",
                    Toast.LENGTH_SHORT
                ).show()
        })
    }

    private fun setQuery(){
        val query = intent.getStringExtra("query")
        if (query != null){
            //TODO: Cambiar siteId a codigo de pais cuando este implementado
            model.getProducts("MLA",query)
            binding.tvSearch.text = query
            binding.rvSearch.visibility = View.INVISIBLE
            binding.tvSinResultados.visibility = View.INVISIBLE
            binding.pbLoading.visibility = View.VISIBLE
        } else
            Toast.makeText(
                    this,
                    "Ocurrio un error, realize otra busqueda e intente de vuelta.",
                    Toast.LENGTH_SHORT
            ).show()
    }

    private fun setItems(products: List<ProductResult>){
        val resultados = "${products.size} resultados"
        binding.rvSearch.visibility = View.VISIBLE
        binding.tvSinResultados.visibility = View.INVISIBLE
        binding.tvResultados.text = resultados
        adapter.setProducts(products)
    }

    private fun pullRefresh() {
        binding.srlSearch.setColorSchemeColors(
            ContextCompat.getColor(
            this,
            R.color.amarillo_500)
        )
        binding.srlSearch.setOnRefreshListener {
            setQuery()
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

    }

}