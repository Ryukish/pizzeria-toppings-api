package com.pizzeria.demo.controller

import com.pizzeria.demo.entity.Customer
import com.pizzeria.demo.repository.CustomerRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.parameters.RequestBody
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@RestController
@RequestMapping("/toppings")
class ToppingController(private val customerRepository: CustomerRepository) {
    private val logger: Logger = LoggerFactory.getLogger(ToppingController::class.java)

    @Operation(summary = "Submit email and toppings")
    @PostMapping("/submit")
    fun submitToppings(@Valid @RequestBody customer: Customer): ResponseEntity<String> {
        logger.info("Received toppings submission from email: {}", customer.email)
        customerRepository.save(customer)
        logger.debug("Customer data saved: {}", customer)
        return ResponseEntity.ok("Toppings submitted successfully.")
    }

    @Operation(summary = "Get toppings and unique customer counts")
    @GetMapping
    fun getToppings(): ResponseEntity<List<ToppingResponse>> {
        val toppingsMap = mutableMapOf<String, MutableSet<String>>()
        logger.info("Fetching toppings and customer counts")
        customerRepository.findAll().forEach { customer ->
            customer.toppings.forEach { topping ->
                toppingsMap.computeIfAbsent(topping) { _: String -> mutableSetOf<String>() }.add(customer.email)
            }
        }

        val response = toppingsMap.map { (topping, emails) ->
            ToppingResponse(topping, emails.size)
        }
        logger.debug("Toppings response: {}", response)
        return ResponseEntity.ok(response)
    }
}

data class ToppingResponse(
    val topping: String,
    val customerCount: Int
)
