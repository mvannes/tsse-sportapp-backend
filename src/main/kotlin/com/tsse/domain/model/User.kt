package com.tsse.domain.model

import javax.persistence.*

/**
 * @author Boyd Hogerheijde
 */
@Entity
class User() {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    var id: Long? = null

    @Column(unique = true, nullable = false)
    var name: String? = null

    @Column(unique = true)
    var email: String? = null

    @Column(nullable = false)
    var password: String? = null

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = arrayOf(JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID"))
    )
    var roles: Set<Role>? = null

    constructor(name: String, password: String, roles: Set<Role>) : this() {
        this.name = name
        this.password = password
        this.roles = roles
    }

    constructor(name: String, email: String, password: String, roles: Set<Role>) : this(name, password, roles) {
        this.email = email
    }

    override fun toString(): String {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}'
    }
}
