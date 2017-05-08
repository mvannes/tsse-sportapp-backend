package com.tsse.domain.model

import org.hibernate.validator.constraints.NotBlank
import org.hibernate.validator.constraints.Range
import java.util.*
import javax.persistence.*

/**
 * Created by boydhogerheijde on 08/05/2017.
 */
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

    override fun equals(other: Any?): Boolean {
        return name == (other as Schedule).name
    }
}