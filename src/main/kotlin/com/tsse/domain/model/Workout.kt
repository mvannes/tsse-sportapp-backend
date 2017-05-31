package com.tsse.domain.model

import org.hibernate.validator.constraints.NotBlank
import java.util.*
import javax.persistence.*

/**
 * @author Floris van Lent
 * @version 1.0.2
 */
@Entity
class Workout() {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0
    @NotBlank(message = "Name cannot be empty.") lateinit var name: String
    lateinit var description: String
    @OneToMany(targetEntity = Exercise::class) var exercises: List<Exercise> = ArrayList()

    constructor(name: String, description: String, exercises: ArrayList<Exercise>) : this() {
        this.name = name
        this.description = description
        this.exercises = exercises
    }

    override fun equals(other: Any?): Boolean {
        return name == (other as Workout).name
    }
}
