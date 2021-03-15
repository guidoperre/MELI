package com.guidoperre.meli.repositories

import com.guidoperre.meli.entities.sites.Site

interface SiteRepository {

    fun getSite(): Site?

    fun insertSite(site: Site): Long

    fun updateSite(site: Site)

}