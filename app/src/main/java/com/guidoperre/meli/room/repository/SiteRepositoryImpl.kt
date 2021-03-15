package com.guidoperre.meli.room.repository

import com.guidoperre.meli.entities.sites.Site
import com.guidoperre.meli.repositories.SiteRepository
import com.guidoperre.meli.room.AppDatabase
import com.guidoperre.meli.room.dao.SiteDAO

class SiteRepositoryImpl(
    private val roomDatabase: AppDatabase,
    private val siteDAO: SiteDAO
): SiteRepository {

    override fun getSite(): Site? {
        return siteDAO.get()
    }

    override fun insertSite(site: Site): Long {
        return siteDAO.insert(site)
    }

    override fun updateSite(site: Site) {
       roomDatabase.runInTransaction {
           siteDAO.delete()
           siteDAO.insert(site)
       }
    }

}