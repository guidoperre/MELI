package com.guidoperre.meli.ui.select_country

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.guidoperre.meli.entities.sites.Site
import com.guidoperre.meli.repositories.MercadolibreRepository
import com.guidoperre.meli.repositories.SiteRepository
import com.guidoperre.meli.ui.select_country.adapter.SiteViewHolder
import com.guidoperre.meli.utils.TriggerLiveEvent
import kotlinx.coroutines.*

class SelectCountryViewModel(
    private val mercadolibreRepository: MercadolibreRepository,
    private val siteRepository: SiteRepository
): ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val sitesHandler = MutableLiveData<List<Site>?>()
    val actualSiteHandler = MutableLiveData<Site?>()

    val commitTrigger = TriggerLiveEvent()
    val noSelectionTrigger = TriggerLiveEvent()

    fun getSites(){
        uiScope.launch {
            val response = withContext(Dispatchers.IO){
                var tries = 0
                var sites: List<Site>? = null
                while (tries < 3 && sites == null){
                    sites = mercadolibreRepository.getSites()
                    tries++
                }
                sites
            }
            sitesHandler.value = response
        }
    }

    fun getActualSite(){
        uiScope.launch {
            val response = withContext(Dispatchers.IO){
                siteRepository.getSite()
            }
            actualSiteHandler.value = response
        }
    }

    fun updateSite() {
        uiScope.launch {
            if (SiteViewHolder.selectedSiteId != "") {
                withContext(Dispatchers.IO){
                    siteRepository.updateSite(
                        Site(
                            SiteViewHolder.selectedSiteId,
                            ""
                        )
                    )
                }
                commitTrigger.call()
            } else
                noSelectionTrigger.call()
        }
    }

}