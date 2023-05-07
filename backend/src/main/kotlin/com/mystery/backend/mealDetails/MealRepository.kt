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
                name = rs.getString(2)
            )
        }
}