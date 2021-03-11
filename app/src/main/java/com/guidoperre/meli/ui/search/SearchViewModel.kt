package com.guidoperre.meli.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.guidoperre.meli.repositories.GoogleRepository
import kotlinx.coroutines.*

class SearchViewModel(
    private val repository: GoogleRepository
): ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val suggestsHandler = MutableLiveData<List<String>?>()

    fun getSuggests(query: String){
        uiScope.launch {
            val response = withContext(Dispatchers.IO){
                repository.getSuggestions(query)
            }
            suggestsHandler.value = response
        }
    }

}