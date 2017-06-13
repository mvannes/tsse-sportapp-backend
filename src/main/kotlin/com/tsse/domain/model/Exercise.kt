package com.tsse.domain.model

import org.hibernate.validator.constraints.NotBlank
import javax.persistence.*

/**
 * @author Boyd Hogerheijde
 */
@Entity
class Exercise() {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EXERCISE_ID")
    val id: Long = 0

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    var muscleGroup: MuscleGroup = MuscleGroup.DEFAULT

    @ElementCollection(targetClass = String::class)
    var mediaFiles: List<String> = arrayListOf()

    @NotBlank(message = "Name cannot be empty.")
    @Column(unique = true, nullable = false)
    lateinit var name: String

    @NotBlank(message = "Description cannot be empty.")
    @Column(nullable = false)
    lateinit var description: String

    constructor(name: String, description: String) : this() {
        this.name = name
        this.description = description
    }

    override fun equals(other: Any?): Boolean {
        return name == (other as Exercise).name
    }

    override fun toString(): String {
        return "Exercise(id=$id, muscleGroup=$muscleGroup, mediaFiles=$mediaFiles, name='$name', description='$description')"
    }
}