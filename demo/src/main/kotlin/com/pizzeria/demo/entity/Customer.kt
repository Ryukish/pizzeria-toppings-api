package com.pizzeria.demo.entity

import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

@Entity
data class Customer(
    @Id
    @field:Email(message = "Invalid email format")
    @field:NotEmpty(message = "Email cannot be empty")
    val email: String,

    @ElementCollection
    @field:Size(min = 1, message = "At least one topping must be selected")
    var toppings: MutableSet<@NotEmpty String> = mutableSetOf()
)
