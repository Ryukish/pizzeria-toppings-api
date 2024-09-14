package com.pizzeria.demo.controller

import com.pizzeria.demo.entity.Suggestion
import com.pizzeria.demo.repository.SuggestionRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.http.HttpStatus

class SuggestionControllerTest {

    private val suggestionRepository = mock(SuggestionRepository::class.java)
    private val suggestionController = SuggestionController(suggestionRepository)

    @Test
    fun `test suggestProducts`() {
        val suggestion = Suggestion(
            email = "test@example.com",
            suggestedProducts = mutableSetOf("Calzone", "Garlic Bread")
        )

        `when`(suggestionRepository.save(suggestion)).thenReturn(suggestion)

        val response = suggestionController.suggestProducts(suggestion)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals("Product suggestions submitted successfully.", response.body)
        verify(suggestionRepository, times(1)).save(suggestion)
    }

    @Test
    fun `test getSuggestions`() {
        val suggestion1 = Suggestion(
            email = "user1@example.com",
            suggestedProducts = mutableSetOf("Calzone")
        )
        val suggestion2 = Suggestion(
            email = "user2@example.com",
            suggestedProducts = mutableSetOf("Lasagna")
        )

        `when`(suggestionRepository.findAll()).thenReturn(listOf(suggestion1, suggestion2))

        val response = suggestionController.getSuggestions()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(listOf(suggestion1, suggestion2), response.body)
        verify(suggestionRepository, times(1)).findAll()
    }
}
