package com.pizzeria.demo.entity

import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

@Entity
data class Suggestion(
    @Id
    @Email
    val email: String,

    @ElementCollection
    @NotEmpty
    var suggestedProducts: MutableSet<@NotEmpty String> = mutableSetOf()
)