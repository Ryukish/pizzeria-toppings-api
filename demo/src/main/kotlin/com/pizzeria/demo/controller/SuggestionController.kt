package com.pizzeria.demo.controller

import com.pizzeria.demo.entity.Suggestion
import com.pizzeria.demo.repository.SuggestionRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid
import io.swagger.v3.oas.annotations.Operation
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@RestController
@RequestMapping("/products")
class SuggestionController(private val suggestionRepository: SuggestionRepository) {

    private val logger: Logger = LoggerFactory.getLogger(SuggestionController::class.java)

    @Operation(summary = "Endpoint to submit product suggestions")
    @PostMapping("/suggest")
    fun suggestProducts(@Valid @RequestBody suggestion: Suggestion): ResponseEntity<String> {
        logger.info("Received product suggestion from email: {}", suggestion.email)
        suggestionRepository.save(suggestion)
        logger.debug("Suggestion data saved: {}", suggestion)
        return ResponseEntity.ok("Product suggestions submitted successfully.")
    }

    @Operation(summary = "Endpoint to get all suggestions")
    @GetMapping("/suggestions")
    fun getSuggestions(): ResponseEntity<List<Suggestion>> {
        logger.info("Fetching all product suggestions")
        val suggestions = suggestionRepository.findAll()
        logger.debug("Suggestions retrieved: {}", suggestions)
        return ResponseEntity.ok(suggestions)
    }
}
