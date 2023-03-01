package com.mystery.backend.mealDetails

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
class MealController {
    @QueryMapping
    fun mealById(@Argument id: String?): Meal? {
        return Meal.getById(id)
    }

    // Custom data fetcher for description field. Whenever a meal object gets returned, run this for the desc
    @SchemaMapping
    fun description(meal: Meal): String {
        return """${meal.name} food :)"""
    }

    @MutationMapping
    fun addMeal(@Argument name: String): List<Meal> {
        return listOf(Meal("meal-123", name))
    }

    // find a docker file and look at config
}
