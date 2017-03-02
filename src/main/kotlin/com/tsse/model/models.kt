package com.tsse.model

import java.util.*

/**
 * @author Boyd Hogerheijde
 */
data class User(val id: Long, val username: String, val password: String)

open class Schedule(val id: Long, val name: String,
                    val description: String, val workouts: List<Workout>,
                    val amountOfTrainingsPerWeek: Int)

data class Workout(val id: Long, val name: String, val description: String, val exercises: List<Exercise>)

data class Exercise(val id: Long, val name: String,
                    val description: String, val category: Category,
                    val mediaFiles: List<String>, var exerciseInfo: ExerciseInfo)

data class ExerciseInfo(val id: Long, val exercise: Exercise, val sets: Int, val reps: Int, val weight: Double)

data class PersonalExercise(val user: User, val workout: Workout, val exerciseInfo: ExerciseInfo)

data class PersonalSchedule(val user: User, val schedule: Schedule, val startDate: Date, val endDate: Date)

enum class Category


