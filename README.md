# Pizzeria Toppings API

This is a backend application built with Kotlin and Spring Boot that allows customers to submit their email addresses along with a list of pizza toppings they're interested in. It also provides endpoints for retrieving the list of toppings and the number of unique customers who have requested each topping.

## **Table of Contents**

- [Key Features](#key-features)
- [Prerequisites](#prerequisites)
- [Setup and Running the Application](#setup-and-running-the-application)
- [Accessing Swagger UI](#accessing-swagger-ui)
- [API Endpoints](#api-endpoints)
- [Authentication](#authentication)
- [Testing](#testing)
- [Additional Notes](#additional-notes)

## **Key Features**

- **Customers can submit their email and topping choices.**
- **Retrieve the list of toppings and unique customer counts.**
- **Special endpoint to list personal topping choices.**
- **Customers can suggest other products they'd like to order.**
- **Data persistence using H2 in-memory database.**
- **API documentation with Swagger/OpenAPI.**
- **Global exception handling for graceful error management.**
- **Logging implemented with SLF4J and Logback.**
- **Basic authentication for secured endpoints.**

## **Prerequisites**
- **Java Development Kit (JDK) 21** or higher
- **Gradle** (Optional if using the Gradle wrapper provided)
- **An IDE** like IntelliJ IDEA (Optional but is it really...)
## **Accessing Swagger UI:**
```bash
    http://localhost:8080/swagger-ui.html
```
## **Setup and Running the Application**

1. **Clone the repository:**

   ```bash
   git clone https://github.com/Ryukish/pizzeria-toppings-api.git
   cd pizzeria-toppings-api
    ```
2. **Build the project:**
    Using the Gradle wrapper:
    ```bash
    ./gradlew clean build
    ```
3. **Run the application:**

    Using the Gradle wrapper:

    ```bash
    ./gradlew bootRun
    ```
    The application will start on port 8080.
## **API Endpoints**
1. **Submit Toppings**
    * Endpoint: POST /toppings/submit
    * Description: Allows customers to submit their email and list of desired toppings.
    * Request Body:

    ```json
        {
            "email": "customer@example.com",
            "toppings": ["Pepperoni", "Mushrooms"]
        }
    ```
    * Response: 200 OK with message "Toppings submitted successfully."
2. **Get Toppings**
    * Endpoint: GET /toppings
    * Description: Retrieves the list of toppings along with the number of unique customers requesting each.
    * Response:

    ```json
    [
        {
            "topping": "Pepperoni",
            "customerCount": 10
        },
        {
            "topping": "Mushrooms",
            "customerCount": 8
        }
    ]
    ```
3. **Personal Topping Choice**
    * Endpoint: GET /toppings/my-choice
    * Description: Returns personal favorite toppings.
    * Response:
    ```json
    ["Pepperoni", "Mushrooms", "Onions"]
    ```
4. **Suggest Products (Secured Endpoint)**
    * Endpoint: POST /products/suggest
    * Description: Allows users to suggest other products they would like to order.
    * Authentication Required: Yes
    * Request Body:
    ```json
        {
            "email": "customer@example.com",
            "suggestedProducts": ["Calzone", "Garlic Bread"]
        }
    ```
    * Response: 200 OK with message "Product suggestions submitted successfully."
5. **Get Product Suggestions (Secured Endpoint)**
    * Endpoint: GET /products/suggestions
    * Description: Retrieves all product suggestions submitted by customers.
    * Authentication Required: Yes
    ### Authentication
        * Secured Endpoints: /products/**
        * Authentication Method: HTTP Basic Authentication
        *Credentials:
            *   Username: `user`
            *   Password: `password`
    * When accessing secured endpoints via Swagger UI, click on the Authorize button and enter the credentials.
## **Testing**
    * Basic unit tests

```bash
    ./gradlew test
```

    Test reports are generated in the `build/reports/tests/test/index.html` file
## Additional Notes
* Data Persistence:
    * The application uses an in-memory H2 database. Data will not persist between application restarts.

    * To enable persistent storage, configure the app to use a file-based database or an external database. I would use PostgreSQL.

* Exception Handling:
    * The application includes global exception handling to provide more meaningful error responses.

* Logging:
    * Logging is configured using Logback. Logs are output to the console by default.

* Swagger/OpenAPI:
    * The application uses springdoc-openapi for API documentation.

* Security Configuration:
    * Security is configured to secure the /products/** endpoints while allowing open access to others, including Swagger UI.

* Dependencies:
    * Kotlin
    * Spring Boot 3.x
    * Spring Data JPA
    * Spring Security
    * H2 Database
    * Swagger/OpenAPI (springdoc-openapi)
    * SLF4J and Logback for logging
    * JUnit 5 and Mockito for testing