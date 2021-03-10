package com.guidoperre.meli

import com.guidoperre.meli.repositories.GoogleRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.koin.test.inject
import org.koin.test.KoinTest

class GoogleRepositoryTest: KoinTest {

    private val repository: GoogleRepository by inject()

    @Test
    fun testSuggest() = runBlocking {
        val query = "gol p"
        val response = repository.getSuggestions(query)
        Assert.assertNotEquals(null,response)
    }

}