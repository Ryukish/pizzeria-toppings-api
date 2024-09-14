package com.pizzeria.demo.repository

import com.pizzeria.demo.entity.Suggestion
import org.springframework.data.jpa.repository.JpaRepository

interface SuggestionRepository : JpaRepository<Suggestion, String>
