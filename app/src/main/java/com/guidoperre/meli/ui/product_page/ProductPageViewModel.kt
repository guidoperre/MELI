package com.guidoperre.meli.ui.product_page

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.guidoperre.meli.entities.product.ProductSearch
import com.guidoperre.meli.entities.product.result.*
import com.guidoperre.meli.repositories.MercadolibreRepository
import kotlinx.coroutines.*

class ProductPageViewModel(
    private val repository: MercadolibreRepository
): ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val picturesHandler = MutableLiveData<ProductPicture?>()
    val descriptionHandler = MutableLiveData<Description?>()
    val reviewsHandler = MutableLiveData<Review?>()
    val questionsHandler = MutableLiveData<ProductQuestion?>()

    fun getPictures(productId: String){
        uiScope.launch {
            val response = withContext(Dispatchers.IO){
                repository.getPictures(productId)
            }
            picturesHandler.value = response
        }
    }

    fun getDescription(productId: String){
        uiScope.launch {
            val response = withContext(Dispatchers.IO){
                repository.getDescription(productId)
            }
            descriptionHandler.value = response
        }
    }

    fun getReviews(productId: String){
        uiScope.launch {
            val response = withContext(Dispatchers.IO){
                repository.getReviews(productId)
            }
            reviewsHandler.value = response
        }
    }

    fun getQuestions(productId: String){
        uiScope.launch {
            val response = withContext(Dispatchers.IO){
                repository.getQuestions(productId, 4)
            }
            questionsHandler.value = response
        }
    }

}