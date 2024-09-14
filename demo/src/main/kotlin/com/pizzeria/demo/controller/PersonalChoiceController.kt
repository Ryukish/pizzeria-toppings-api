package com.pizzeria.demo.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.parameters.RequestBody

@RestController
class PersonalChoiceController {

    @Operation(summary = "Get Personal topping choices")
    @GetMapping("/toppings/my-choice")
    fun getMyToppingChoice(): ResponseEntity<List<String>> {
        val myFavoriteToppings = listOf("Pepperoni", "Mushrooms", "Onions")
        return ResponseEntity.ok(myFavoriteToppings)
    }
}
