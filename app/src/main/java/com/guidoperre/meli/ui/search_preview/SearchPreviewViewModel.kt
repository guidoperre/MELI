package com.guidoperre.meli.ui.search_preview

import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.guidoperre.meli.entities.product.ProductSearch
import com.guidoperre.meli.entities.product.result.Description
import com.guidoperre.meli.entities.product.result.ProductResult
import com.guidoperre.meli.repositories.GoogleRepository
import com.guidoperre.meli.repositories.MercadolibreRepository
import kotlinx.coroutines.*

class SearchPreviewViewModel(
    private val repository: MercadolibreRepository
): ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val productsHandler = MutableLiveData<ProductSearch?>()

    var total = 0
    val offsetHandler = MutableLiveData<Int>()


    fun getProducts(siteId: String,query: String){
        uiScope.launch {
            val response = withContext(Dispatchers.IO){
                val offset = offsetHandler.value
                var tries = 0
                var products: ProductSearch? = null
                while (tries < 3 && products == null){
                    products = if (offset != null)
                        repository.getProducts(siteId,query, offset)
                    else
                        repository.getProducts(siteId,query, 0)
                    tries++
                }
                products

            }
            productsHandler.value = response
        }
    }

    fun goBack(loading: ProgressBar){
        if (loading.visibility == View.INVISIBLE)
            offsetHandler.value = offsetHandler.value?.minus(50)
    }

    fun goNext(loading: ProgressBar){
        if (loading.visibility == View.INVISIBLE)
            offsetHandler.value = offsetHandler.value?.plus(50)
    }

}