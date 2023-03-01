package com.mystery.backend.mealDetails

class Meal(val id: String, val name: String) {
    companion object {
        private val meals: List<Meal> = listOf(
            Meal("meal-1", "Noodles with carrot"),
            Meal("meal-2", "Chicken Tagine")
        )

        fun getById(id: String?): Meal? {
            return meals.firstOrNull { it.id == id }
        }
    }
}
