package com.pizzeria.demo.repository

import com.pizzeria.demo.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, String>
