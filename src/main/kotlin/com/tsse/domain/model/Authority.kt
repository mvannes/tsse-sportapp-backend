package com.tsse.domain.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * @author Boyd Hogerheijde
 */
@Entity
class Authority() {

    @Id
    @GeneratedValue
    @Column(name = "AUTHORITY_ID")
    var id: Long? = null

    @Column(unique = true, nullable = false)
    var name: String? = null

    constructor(name: String) : this() {
        this.name = name
    }

    override fun toString(): String {
        return "Authority{" +
                "name='" + name + '\'' +
                '}'
    }
}
