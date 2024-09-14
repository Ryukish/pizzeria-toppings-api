package com.pizzeria.demo.controller

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class PersonalChoiceControllerTest {

    private val personalChoiceController = PersonalChoiceController()

    @Test
    fun `test getMyToppingChoice`() {
        val response = personalChoiceController.getMyToppingChoice()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(listOf("Pepperoni", "Mushrooms", "Onions"), response.body)
    }
}
