package com.pizzeria.demo.controller

import com.pizzeria.demo.entity.Suggestion
import com.pizzeria.demo.repository.SuggestionRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.parameters.RequestBody

@RestController
@RequestMapping("/products")
class SuggestionController(private val suggestionRepository: SuggestionRepository) {

    @Operation(summary = "Endpoint to submit product suggestions")
    @PostMapping("/suggest")
    fun suggestProducts(@Valid @RequestBody suggestion: Suggestion): ResponseEntity<String> {
        suggestionRepository.save(suggestion)
        return ResponseEntity.ok("Product suggestions submitted successfully.")
    }

    @Operation(summary = "Endpoint to get all suggestions")
    @GetMapping("/suggestions")
    fun getSuggestions(): ResponseEntity<List<Suggestion>> {
        val suggestions = suggestionRepository.findAll()
        return ResponseEntity.ok(suggestions)
    }
}
