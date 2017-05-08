package com.tsse.domain.model

import org.hibernate.validator.constraints.NotBlank
import javax.persistence.*

/**
 * Created by boydhogerheijde on 08/05/2017.
 */
@Entity
class Exercise() {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0
    @Enumerated(EnumType.ORDINAL) var category: Category = Category.DEFAULT
    @ElementCollection(targetClass = String::class) var mediaFiles: List<String> = arrayListOf()

    @NotBlank(message = "Name cannot be empty.") lateinit var name: String
    @NotBlank(message = "Description cannot be empty.") lateinit var description: String

    constructor(name: String, description: String) : this() {
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