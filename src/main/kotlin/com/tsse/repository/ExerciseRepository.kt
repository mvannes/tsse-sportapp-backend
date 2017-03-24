package com.tsse.repository

import com.tsse.domain.Category
import com.tsse.domain.Exercise
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Repository for exercises.
 *
 * @author Fabian de Almeida Ramos
 * @author Boyd Hogerheijde
 * @version 1.0.1
 */
@Repository
interface ExerciseRepository : JpaRepository<Exercise, Long> {

    fun findByName(name: String): Exercise?

    fun findByCategory(category: Category): List<Exercise>
}
