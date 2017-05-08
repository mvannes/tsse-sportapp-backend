package com.tsse.domain

import com.tsse.domain.model.Exercise
import com.tsse.domain.model.Schedule
import com.tsse.domain.model.Workout
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * File containing all models contained in the database.
 *
 * The unit of {@link ExerciseInfo.weight} is in kilograms.
 *
 * @author Fabian de Almeida Ramos
 * @author Boyd Hogerheijde
 * @version 1.0.1
 */
@Entity
data class User(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long,
                val username: String, val email: String, val password: String)

data class ExerciseInfo(val id: Long, val exercise: Exercise, val sets: Int, val reps: Int, val weight: Double)

data class PersonalExercise(val user: User, val workout: Workout, val exerciseInfo: ExerciseInfo)

data class PersonalSchedule(val user: User, val schedule: Schedule, val startDate: Date, val endDate: Date)