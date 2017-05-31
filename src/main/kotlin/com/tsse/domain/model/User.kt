package com.tsse.domain.model

import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotBlank
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

/**
 *
 *
 * @author Fabian de Almeida Ramos
 * @version 1.0.0
 */
@Entity
@Table(name = "users")
class User() : UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false, updatable = false)
    var id: Long = 0L

    @Column(name = "username", nullable = false, unique = true)
    @NotBlank(message = "Username cannot be empty!")
    @Email(message = "Invalid email entered.")
    private lateinit var username: String

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password cannot be empty!")
    private lateinit var password: String

    @Column(name = "enabled", nullable = false)
    var enabled: Boolean = false

    @Column(name = "birthdate", nullable = false)
    @NotNull(message = "Birthdate cannot be empty!")
    lateinit var birthdate: Date

    @Column(name = "displayName", nullable = false)
    @NotBlank(message = "Display name cannot be empty!")
    lateinit var displayName: String

    @Column(name = "firstName", nullable = false)
    @NotBlank(message = "First name cannot be empty!")
    lateinit var firstName: String

    @Column(name = "lastName", nullable = false)
    @NotBlank(message = "Last name cannot be empty!")
    lateinit var lastName: String

    @Column(name = "status")
    var status: String = ""

    @Column(name = "role", nullable = false, updatable = true)
    @NotBlank(message = "Role cannot be empty!")
    lateinit var role: String

    constructor(username: String, password: String, enabled: Boolean,
                birthdate: Date, displayName: String, firstName: String,
                lastName: String, status: String, role: String) : this() {
        this.username = username
        this.password = password
        this.enabled = enabled
        this.birthdate = birthdate
        this.displayName = displayName
        this.firstName = firstName
        this.lastName = lastName
        this.status = status
        this.role = role
    }

    override fun getAuthorities() = AuthorityUtils.createAuthorityList(role)

    override fun isEnabled(): Boolean = enabled

    override fun getUsername(): String = username

    override fun isCredentialsNonExpired(): Boolean = true

    override fun getPassword(): String = password

    fun setPassword(password: String) {
        this.password = password
    }

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun equals(other: Any?): Boolean {
        return username == (other as User).username
    }
}