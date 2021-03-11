package com.guidoperre.meli.ui.search_preview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.guidoperre.meli.entities.product.ProductSearch
import com.guidoperre.meli.repositories.GoogleRepository
import com.guidoperre.meli.repositories.MercadolibreRepository
import kotlinx.coroutines.*

class SearchPreviewViewModel(
    private val repository: MercadolibreRepository
): ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val productsHandler = MutableLiveData<ProductSearch?>()

    fun getProducts(siteId: String,query: String){
        uiScope.launch {
            val response = withContext(Dispatchers.IO){
                repository.getProducts(siteId,query)
            }
            productsHandler.value = response
        }
    }

}