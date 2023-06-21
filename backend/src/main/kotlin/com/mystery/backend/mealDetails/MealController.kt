package com.mystery.backend.mealDetails

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller

@Controller
class MealController(val mealRepository: MealRepository) {
    @QueryMapping
    fun mealById(@Argument id: String): Meal? {
        return mealRepository.getMeal(id.toInt())
    }

    // Custom data fetcher for description field. Whenever a meal object gets returned, run this for the desc
    //    @SchemaMapping
    //    fun description(meal: Meal): String {
    //        return """${meal.name} food"""
    //    }

    @QueryMapping
    fun meals(): List<Meal> {
        return mealRepository.getMeals()
    }

    @QueryMapping
    fun randomMeals(): List<Meal> {
        return mealRepository.getRandomMeals()
    }

    @MutationMapping
    fun addMeal(@Argument name: String): Meal {
        return mealRepository.addMeal(name, "description")
    }

    @MutationMapping
    fun fillMeals(): Meal {
        return mealRepository.fillMeals()
    }

    @MutationMapping
    fun fillMealsUsingList(): List<Meal> {
        return mealRepository.fillMealsUsingList()
    }
}
