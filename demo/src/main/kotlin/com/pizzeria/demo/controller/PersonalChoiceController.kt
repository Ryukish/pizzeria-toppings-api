package com.pizzeria.demo.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import io.swagger.v3.oas.annotations.Operation
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@RestController
class PersonalChoiceController {

    private val logger: Logger = LoggerFactory.getLogger(PersonalChoiceController::class.java)

    @Operation(summary = "Get Personal topping choices")
    @GetMapping("/toppings/my-choice")
    fun getMyToppingChoice(): ResponseEntity<List<String>> {
        logger.info("Fetching personal topping choices")
        val myFavoriteToppings = listOf("Pepperoni", "Mushrooms", "Onions")
        logger.debug("Personal toppings: {}", myFavoriteToppings)
        return ResponseEntity.ok(myFavoriteToppings)
    }
}
