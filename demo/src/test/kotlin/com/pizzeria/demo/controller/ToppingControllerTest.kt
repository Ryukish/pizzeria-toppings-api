package com.pizzeria.demo.controller

import com.pizzeria.demo.entity.Customer
import com.pizzeria.demo.repository.CustomerRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.http.HttpStatus

class ToppingControllerTest {

    private val customerRepository = mock(CustomerRepository::class.java)
    private val toppingController = ToppingController(customerRepository)

    @Test
    fun `test submitToppings`() {
        val customer = Customer(
            email = "test@example.com",
            toppings = mutableSetOf("Pepperoni", "Mushrooms")
        )

        `when`(customerRepository.save(customer)).thenReturn(customer)

        val response = toppingController.submitToppings(customer)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals("Toppings submitted successfully.", response.body)
        verify(customerRepository, times(1)).save(customer)
    }

    @Test
    fun `test getToppings`() {
        val customer1 = Customer(
            email = "user1@example.com",
            toppings = mutableSetOf("Pepperoni", "Onions")
        )
        val customer2 = Customer(
            email = "user2@example.com",
            toppings = mutableSetOf("Pepperoni", "Mushrooms")
        )

        `when`(customerRepository.findAll()).thenReturn(listOf(customer1, customer2))

        val response = toppingController.getToppings()

        assertEquals(HttpStatus.OK, response.statusCode)
        val expectedResponse = listOf(
            ToppingResponse("Pepperoni", 2),
            ToppingResponse("Onions", 1),
            ToppingResponse("Mushrooms", 1)
        )
        assertEquals(expectedResponse.toSet(), response.body?.toSet())
        verify(customerRepository, times(1)).findAll()
    }
}
