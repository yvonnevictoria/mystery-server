package com.mystery.backend.mealDetails

import org.springframework.dao.InvalidDataAccessApiUsageException
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository

@Repository
class MealRepository(
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate,
) {
    fun addMeal(name: String, description: String): Meal {
        val keyHolder = GeneratedKeyHolder()
        namedParameterJdbcTemplate.update(
            """
            INSERT INTO meals (name, description) VALUES (:name, :description)
            RETURNING id
            """,
            MapSqlParameterSource()
                .addValue("name", name)
                .addValue("description", description),
            keyHolder,
            arrayOf("id")
        )
        return getMeal(keyHolder.key!!.toInt())
    }

    fun fillMeals(): Meal {
        val keyHolder1 = GeneratedKeyHolder()
        namedParameterJdbcTemplate.update(
            """
            INSERT INTO meals (name, description) VALUES (:name, :description)
            RETURNING id
            """,
            MapSqlParameterSource()
                .addValue("name", "Spag bol")
                .addValue("description", "Pasta"),
            keyHolder1,
            arrayOf("id")
        )

        val keyHolder2 = GeneratedKeyHolder()
        namedParameterJdbcTemplate.update(
            """
            INSERT INTO meals (name, description) VALUES (:name, :description)
            RETURNING id
            """,
            MapSqlParameterSource()
                .addValue("name", "Steak and veggies")
                .addValue("description", "Pub food"),
            keyHolder2,
            arrayOf("id")
        )

        val keyHolder3 = GeneratedKeyHolder()
        namedParameterJdbcTemplate.update(
            """
            INSERT INTO meals (name, description) VALUES (:name, :description)
            RETURNING id
            """,
            MapSqlParameterSource()
                .addValue("name", "Salmon Nigiri")
                .addValue("description", "Japanese sushi"),
            keyHolder3,
            arrayOf("id")
        )

        val keyHolder4 = GeneratedKeyHolder()
        namedParameterJdbcTemplate.update(
            """
            INSERT INTO meals (name, description) VALUES (:name, :description)
            RETURNING id
            """,
            MapSqlParameterSource()
                .addValue("name", "Salad")
                .addValue("description", "Healthy"),
            keyHolder4,
            arrayOf("id")
        )

        val keyHolder5 = GeneratedKeyHolder()
        namedParameterJdbcTemplate.update(
            """
            INSERT INTO meals (name, description) VALUES (:name, :description)
            RETURNING id
            """,
            MapSqlParameterSource()
                .addValue("name", "Rice and beans")
                .addValue("description", "Office food"),
            keyHolder5,
            arrayOf("id")
        )

        val keyHolder6 = GeneratedKeyHolder()
        namedParameterJdbcTemplate.update(
            """
            INSERT INTO meals (name, description) VALUES (:name, :description)
            RETURNING id
            """,
            MapSqlParameterSource()
                .addValue("name", "2 Minute Noodles with eggs and peas")
                .addValue("description", "Home food"),
            keyHolder6,
            arrayOf("id")
        )
        // return getMeals()
        // can supply multiple tuples, how do to with templating logic.
        // pass a list of items in as the template variables
        // or loop
        return getMeal(keyHolder1.key!!.toInt())
    }

    fun fillMealsUsingList(): List<Meal> {
        val meals = listOf(
            Meal("", "2 Minute Noodles with eggs and peas", "Home food"),
            Meal("", "Pasta with tomato sauce", "Italian classic"),
            Meal("", "Rice and beans", "Office food"),
            Meal("", "Salmon Nigiri", "Japanese sushi"),
            Meal("", "Steak and veggies", "Pub food"),
            Meal("", "Chicken skewers with garlic sauce", "Lebanese food"),
            Meal("", "Pasta with cream sauce", "Italian classic"),
            Meal("", "Meat and potatoes", "Office food"),
            Meal("", "Tuna hand roll", "Japanese sushi"),
            Meal("", "Steak and chips", "Pub food"),
            Meal("", "Gnocchi", "Italian classic"),
            Meal("", "Tabbouli", "Lebanese food"),
            Meal("", "Pizza with no cheese", "Italian classic"),
            Meal("", "Sandwich", "Office food"),
            Meal("", "Tempura prawn hand roll", "Japanese sushi"),
            Meal("", "Chicken schnitzel", "Pub food"),
            Meal("", "Falefal sandwich", "Lebanese food"),
            Meal("", "Salad", "Office food"),
            Meal("", "Chicken Katsu curry", "Japanese sushi"),
            Meal("", "Spag bol", "Italian classic")
        )

        val savedMeals = mutableListOf<Meal>()

        meals.forEach { meal ->
            val keyHolder = GeneratedKeyHolder()
            namedParameterJdbcTemplate.update(
                """
                    INSERT INTO meals (name, description) VALUES (:name, :description)
                    RETURNING id
                    """,
                MapSqlParameterSource()
                    .addValue("name", meal.name)
                    .addValue("description", meal.description),
                keyHolder,
                arrayOf("id")
            )
            savedMeals.add(Meal(keyHolder.key!!.toString(), meal.name, meal.description))
        }
        return savedMeals
    }

    fun getMeal(id: Number): Meal =
        namedParameterJdbcTemplate.query(
            """
            select id, name, description
            from meals
            where id = :id
        """, mapOf("id" to id)
        ) { rs, _ ->
            Meal(
                id = rs.getString(1),
                name = rs.getString(2),
                description = rs.getString(3)
            )
        }.first()

    fun getMeals(): List<Meal> =
        namedParameterJdbcTemplate.query(
            """
            SELECT * FROM meals
        """,
            emptyMap<String, Any>()
        ) { rs, _ ->
            Meal(
                id = rs.getString(1),
                name = rs.getString(2),
                description = rs.getString(3)
            )
        }

    fun getRandomMeals(): List<Meal> =
        namedParameterJdbcTemplate.query(
            """
            SELECT * FROM meals
            ORDER BY RANDOM()
            LIMIT 10
        """,
            emptyMap<String, Any>()
        ) { rs, _ ->
            Meal(
                id = rs.getString(1),
                name = rs.getString(2),
                description = rs.getString(3)
            )
        }
}
