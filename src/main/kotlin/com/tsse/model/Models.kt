package com.tsse.model

import java.util.*

/**
 * File containing all models contained in the database.
 *
 * The unit of {@link ExerciseInfo.weight} is in kilograms.
 *
 * @author Fabian de Almeida Ramos
 * @author Boyd Hogerheijde
 *
 * @version 1.0.0
 */
data class User(val id: Long, val username: String, val email: String, val password: String)

data class Schedule(val id: Long, val name: String,
                    val description: String, val workouts: List<Workout>,
                    val amountOfTrainingsPerWeek: Int)

data class Workout(val id: Long, val name: String, val description: String, val exercises: List<Exercise>)

data class Exercise(val id: Long, val name: String,
                    val description: String, val category: Category,
                    val mediaFiles: List<String>)

data class ExerciseInfo(val id: Long, val exercise: Exercise, val sets: Int, val reps: Int, val weight: Double)

data class PersonalExercise(val user: User, val workout: Workout, val exerciseInfo: ExerciseInfo)

data class PersonalSchedule(val user: User, val schedule: Schedule, val startDate: Date, val endDate: Date)

enum class Category