package com.tsse.domain.model

import javax.persistence.*

/**
 * @author Boyd Hogerheijde
 */
@Entity
class Role() {

    @Id
    @GeneratedValue
    @Column(name = "ROLE_ID")
    var id: Long? = null

    @Column(unique = true, nullable = false)
    var name: String? = null

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = arrayOf(JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "AUTHORITY_ID"))
    )
    var authorities: Set<Authority>? = null

    constructor(name: String) : this() {
        this.name = name
    }

    constructor(name: String, authorities: Set<Authority>) : this(name) {
        this.authorities = authorities
    }

    override fun toString(): String {
        return "Role{" +
                "name='" + name + '\'' +
                ", authorities=" + authorities +
                '}'
    }
}
