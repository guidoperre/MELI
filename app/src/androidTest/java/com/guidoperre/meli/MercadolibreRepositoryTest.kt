package com.guidoperre.meli

import com.guidoperre.meli.repositories.MercadolibreRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class MercadolibreRepositoryTest: KoinTest {

    private val repository: MercadolibreRepository by inject()

    @Test
    fun testProducts() = runBlocking {
        val query = "Motorola G6"
        val response = repository.getProducts("MLA", query)
        Assert.assertNotEquals(null, response)
    }

}