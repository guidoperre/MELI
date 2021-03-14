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
    val defaultPicture = MutableLiveData<Drawable?>()

    fun getPictures(productId: String){
        uiScope.launch {
            val response = withContext(Dispatchers.IO){
                var tries = 0
                var pictures: ProductPicture? = null
                while (tries < 3 && pictures == null){
                    pictures = repository.getPictures(productId)
                    tries++
                }
                convertDrawables(pictures?.pictures)
            }
            picturesHandler.value = response
        }
    }

    fun getDescription(productId: String){
        uiScope.launch {
            val response = withContext(Dispatchers.IO){
                var tries = 0
                var description: Description? = null
                while (tries < 3 && description == null){
                    description = repository.getDescription(productId)
                    tries++
                }
                description
            }
            descriptionHandler.value = response
        }
    }

    fun getReviews(productId: String){
        uiScope.launch {
            val response = withContext(Dispatchers.IO){
                var tries = 0
                var review: Review? = null
                while (tries < 3 && review == null){
                    review = repository.getReviews(productId)
                    tries++
                }
                review
            }
            reviewsHandler.value = response
        }
    }

    fun getQuestions(productId: String){
        uiScope.launch {
            val response = withContext(Dispatchers.IO){
                var tries = 0
                var questions: ProductQuestion? = null
                while (tries < 3 && questions == null){
                    questions = repository.getQuestions(productId,4)
                    tries++
                }
                questions
            }
            questionsHandler.value = response
        }
    }

    fun getDefaultPicture(url: String){
        uiScope.launch {
            val response = withContext(Dispatchers.IO){
                try {
                    val input = URL(url).content as InputStream
                    Drawable.createFromStream(input, "src name")
                } catch (e: Exception) {
                    Log.i("Get picture","Error getting image")
                    null
                }
            }
            defaultPicture.value = response
        }
    }

    private fun convertDrawables(pictures: List<Picture>?): List<Drawable>? {
        val drawables = ArrayList<Drawable>()
        var limit = 0
        if (pictures != null)
            for (picture in pictures){
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
        return drawables
    }

}