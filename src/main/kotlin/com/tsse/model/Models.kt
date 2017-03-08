package com.tsse.model

import java.util.*
import javax.persistence.*

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
@Entity
data class User(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long,
                val username: String, val email: String, val password: String)

@Entity
data class Schedule(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long, val name: String,
                    val description: String, @OneToMany(mappedBy = "schedule") val workouts: List<Workout>,
                    val amountOfTrainingsPerWeek: Int)

@Entity
data class Workout(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long, val name: String,
                   val description: String,
                   @ElementCollection(targetClass = String::class) val exercises: List<Exercise>)

@Entity
data class Exercise(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long, val name: String,
                    val description: String, val category: Category,
                    @ElementCollection(targetClass = String::class) val mediaFiles: List<String>)

data class ExerciseInfo(val id: Long, val exercise: Exercise, val sets: Int, val reps: Int, val weight: Double)

data class PersonalExercise(val user: User, val workout: Workout, val exerciseInfo: ExerciseInfo)

data class PersonalSchedule(val user: User, val schedule: Schedule, val startDate: Date, val endDate: Date)

enum class Category