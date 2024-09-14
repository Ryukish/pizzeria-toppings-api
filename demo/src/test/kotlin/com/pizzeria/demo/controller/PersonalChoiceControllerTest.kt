package com.pizzeria.demo.controller

import com.pizzeria.demo.entity.Customer
import com.pizzeria.demo.repository.CustomerRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.http.HttpStatus
import java.util.Optional

class PersonalChoiceControllerTest {

    private val customerRepository = mock(CustomerRepository::class.java)
    private val personalChoiceController = PersonalChoiceController(customerRepository)

    @Test
    fun `test getMyToppingChoice when customer exists`() {
        val email = "customer@example.com"
        val customer = Customer(
            email = email,
            toppings = mutableSetOf("Pepperoni", "Mushrooms")
        )

        // Mock the repository to return the customer when findById is called
        `when`(customerRepository.findById(email)).thenReturn(Optional.of(customer))

        val response = personalChoiceController.getMyToppingChoice(email)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(listOf("Pepperoni", "Mushrooms"), response.body)
    }

    @Test
    fun `test getMyToppingChoice when customer does not exist`() {
        val email = "nonexistent@example.com"

        // Mock the repository to return empty when findById is called
        `when`(customerRepository.findById(email)).thenReturn(Optional.empty())

        val response = personalChoiceController.getMyToppingChoice(email)

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
        assertEquals("Customer with email $email not found.", response.body)
    }
}
