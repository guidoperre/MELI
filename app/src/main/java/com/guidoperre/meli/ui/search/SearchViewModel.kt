package com.guidoperre.meli.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.guidoperre.meli.entities.search.Search
import com.guidoperre.meli.repositories.GoogleRepository
import com.guidoperre.meli.repositories.RecentSearchRepository
import kotlinx.coroutines.*

class SearchViewModel(
    private val googleRepository: GoogleRepository,
    private val recentSearchRepository: RecentSearchRepository
): ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val historyHandler = MutableLiveData<List<Search>?>()
    val suggestsHandler = MutableLiveData<List<Search>?>()

    fun getHistory(){
        uiScope.launch {
            val response = withContext(Dispatchers.IO){
                recentSearchRepository.getAllRecentSearches()
            }
            historyHandler.value = response
        }
    }

    fun insertSearch(search: Search){
        uiScope.launch {
            withContext(Dispatchers.IO){
                recentSearchRepository.insertSearch(search)
            }
        }
    }

    fun getSuggests(query: String){
        uiScope.launch {
            val response = withContext(Dispatchers.IO){
                googleRepository.getSuggestions(query)
            }
            if (response != null)
                suggestsHandler.value = response
            else
                suggestsHandler.value = ArrayList()
        }
    }

}