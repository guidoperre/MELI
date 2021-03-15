package com.guidoperre.meli.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.guidoperre.meli.entities.sites.Site
import com.guidoperre.meli.repositories.SiteRepository
import com.guidoperre.meli.utils.TriggerLiveEvent
import kotlinx.coroutines.*

class HomeViewModel(
        private val siteRepository: SiteRepository
): ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val siteHandler = MutableLiveData<Site?>()

    val aboutMeTrigger = TriggerLiveEvent()

    fun getSite(){
        uiScope.launch {
            val response = withContext(Dispatchers.IO){
                siteRepository.getSite()
            }
            siteHandler.value = response
        }
    }

    fun openAboutMe() {
        aboutMeTrigger.call()
    }

}