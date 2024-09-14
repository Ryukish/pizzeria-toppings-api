package com.pizzeria.demo.controller

import com.pizzeria.demo.repository.CustomerRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestParam
import io.swagger.v3.oas.annotations.Operation
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@RestController
class PersonalChoiceController(private val customerRepository: CustomerRepository) {

    private val logger: Logger = LoggerFactory.getLogger(PersonalChoiceController::class.java)

    @Operation(summary = "Get personal topping choices")
    @GetMapping("/toppings/my-choice")
    fun getMyToppingChoice(@RequestParam email: String): ResponseEntity<Any> {
        logger.info("Fetching personal topping choices for email: {}", email)
        val customerOptional = customerRepository.findById(email)
        return if (customerOptional.isPresent) {
            val toppings = customerOptional.get().toppings.toList()
            logger.debug("Personal toppings for {}: {}", email, toppings)
            ResponseEntity.ok(toppings)
        } else {
            logger.warn("Customer with email {} not found", email)
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Customer with email $email not found.")
        }
    }
}
