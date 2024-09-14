package com.pizzeria.demo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories

@Configuration
class SecurityConfig {

    // Define a PasswordEncoder bean
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    // In-memory user details service with one user
    @Bean
    fun userDetailsService(): InMemoryUserDetailsManager {
        val encoder = passwordEncoder()
        val user: UserDetails = User.builder()
            .passwordEncoder(encoder::encode)
            .username("user")
            .password("password")
            .roles("USER")
            .build()
        return InMemoryUserDetailsManager(user)
    }

    // Security filter chain configuration
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            // Disable CSRF (not needed for stateless REST APIs)
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { authz ->
                authz
                    // Permit all requests to Swagger UI and OpenAPI endpoints
                    .requestMatchers(
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/actuator/**"
                    ).permitAll()
                    // Require authentication for /products/**
                    .requestMatchers("/products/**").authenticated()
                    // Permit all other requests
                    .anyRequest().permitAll()
            }
            // Enable HTTP Basic authentication
            .httpBasic(withDefaults())
        return http.build()
    }
}
