package com.tsse.domain.model

import javax.persistence.*

/**
 * Created by boydhogerheijde on 08/05/2017.
 */
@Entity
data class Workout(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long, val name: String,
                   val description: String,
                   @ElementCollection(targetClass = String::class) val exercises: List<Exercise>)
