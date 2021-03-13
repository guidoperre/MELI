package com.guidoperre.meli.ui.product_page

import android.graphics.drawable.Drawable
import android.media.Image
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.guidoperre.meli.entities.product.ProductSearch
import com.guidoperre.meli.entities.product.result.*
import com.guidoperre.meli.repositories.MercadolibreRepository
import kotlinx.coroutines.*
import java.io.InputStream
import java.lang.Exception
import java.net.URL

class ProductPageViewModel(
    private val repository: MercadolibreRepository
): ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val picturesHandler = MutableLiveData<List<Drawable>?>()
    val descriptionHandler = MutableLiveData<Description?>()
    val reviewsHandler = MutableLiveData<Review?>()
    val questionsHandler = MutableLiveData<ProductQuestion?>()

    fun getPictures(productId: String){
        uiScope.launch {
            val response = withContext(Dispatchers.IO){
                val drawables = ArrayList<Drawable>()
                val pictures = repository.getPictures(productId)
                var limit = 0
                if (pictures?.pictures != null)
                    for (picture in pictures.pictures){
                        if (limit < 6)
                            try {
                                val input = URL(picture.url).content as InputStream
                                drawables.add(Drawable.createFromStream(input, "src name"))
                                limit++
                            } catch (e: Exception) {
                                Log.i("Get picture","Error getting image")
                            }
                        else
                            break
                    }
                drawables
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