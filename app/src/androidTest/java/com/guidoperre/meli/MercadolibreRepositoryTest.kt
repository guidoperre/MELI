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
        val response = repository.getProducts("MLA", query, 0)
        Assert.assertNotEquals(null, response)
    }

    @Test
    fun testPictures() = runBlocking {
        val response = repository.getPictures("MLA679795871")
        Assert.assertNotEquals(null, response)
    }

    @Test
    fun testDescription() = runBlocking {
        val response = repository.getDescription("MLA679795871")
        Assert.assertNotEquals(null, response)
    }

    @Test
    fun testReviews() = runBlocking {
        val response = repository.getReviews("MLA679795871")
        Assert.assertNotEquals(null, response)
    }

    @Test
    fun testQuestions() = runBlocking {
        val response = repository.getQuestions("MLA679795871", 4)
        Assert.assertNotEquals(null, response)
    }

    @Test
    fun testSites() = runBlocking {
        val response = repository.getSites()
        Assert.assertNotEquals(null, response)
    }

}