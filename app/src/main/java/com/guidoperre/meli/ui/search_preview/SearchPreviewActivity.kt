package com.guidoperre.meli.ui.search_preview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.guidoperre.meli.R
import com.guidoperre.meli.databinding.ActivitySearchPreviewBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SearchPreviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchPreviewBinding
    private val model: SearchPreviewViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_preview)
        binding.lifecycleOwner = this
        binding.activity = this

        setObservables()
        setQuery()
    }

    private fun setObservables() {
        model.productsHandler.observe(this, {

        })
    }

    private fun setQuery(){
        val query = intent.getStringExtra("query")
        if (query != null){
            binding.tvSearch.text = query
            //TODO: Cambiar siteId a codigo de pais cuando este implementado
            model.getProducts("MLA",query)
        } else
            Toast.makeText(
                    this,
                    "Ocurrio un error, realize otra busqueda e intente de vuelta.",
                    Toast.LENGTH_SHORT
            ).show()

    }


}