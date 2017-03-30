package com.tsse.domain

import org.hibernate.validator.constraints.NotBlank
import org.hibernate.validator.constraints.Range
import java.util.*
import javax.persistence.*

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

@Entity
class Schedule() {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0
    @NotBlank(message = "Schedule name cannot be empty!") lateinit var name: String
    lateinit var description: String
    @OneToMany(targetEntity = Workout::class) var workouts: List<Workout> = ArrayList()
    @Range(min = 1, message = "Schedule needs at least one training per week!") var amountOfTrainingsPerWeek: Int = 1

    constructor(name: String, description: String, workouts: ArrayList<Workout>, amountOfTrainingsPerWeek: Int) : this() {
        this.name = name
        this.description = description
        this.workouts = workouts
        this.amountOfTrainingsPerWeek = amountOfTrainingsPerWeek

    }

}

@Entity
data class Workout(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long, val name: String,
                   val description: String,
                   @ElementCollection(targetClass = String::class) val exercises: List<Exercise>)

@Entity
class Exercise() {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0
    @Enumerated(EnumType.ORDINAL) var category: Category = Category.DEFAULT
    @ElementCollection(targetClass = String::class) var mediaFiles: List<String> = arrayListOf()

    @NotBlank(message = "Name cannot be empty.") lateinit var name: String
    @NotBlank(message = "Description cannot be empty.") lateinit var description: String

    constructor(name: String, description: String): this() {
        this.name = name
        this.description = description
    }

    override fun equals(other: Any?): Boolean {
        return name == (other as Exercise).name
    }

    override fun toString(): String {
        return "Exercise(id=$id, category=$category, mediaFiles=$mediaFiles, name='$name', description='$description')"
    }
}

data class ExerciseInfo(val id: Long, val exercise: Exercise, val sets: Int, val reps: Int, val weight: Double)

data class PersonalExercise(val user: User, val workout: Workout, val exerciseInfo: ExerciseInfo)

data class PersonalSchedule(val user: User, val schedule: Schedule, val startDate: Date, val endDate: Date)

enum class Category {
    DEFAULT
}