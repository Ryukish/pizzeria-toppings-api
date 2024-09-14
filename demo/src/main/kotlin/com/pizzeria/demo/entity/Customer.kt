package com.pizzeria.demo.entity

import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

@Entity
data class Customer(
    @Id
    @Email
    @NotEmpty(message = "Email cannot be empty")
    val email: String,

    @ElementCollection
    @NotEmpty(message = "Toppings list cannot be empty")    
    var toppings: MutableSet<String> = mutableSetOf()
)
