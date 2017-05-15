package com.tsse.domain.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotBlank
import org.springframework.security.core.GrantedAuthority
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
class User : UserDetails {

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
    private lateinit var birthdate: Date

    @Column(name = "displayName", nullable = false)
    @JsonProperty("displayName")
    @NotBlank(message = "Display name cannot be empty!")
    private lateinit var displayName: String

    @Column(name = "firstName", nullable = false)
    @JsonProperty("firstName")
    @NotBlank(message = "First name cannot be empty!")
    private lateinit var firstName: String

    @Column(name = "lastName", nullable = false)
    @JsonProperty("lastName")
    @NotBlank(message = "Last name cannot be empty!")
    private lateinit var lastName: String

    @Column(name = "status")
    private var status: String = ""

    constructor()

    constructor(username: String, password: String, enabled: Boolean,
                birthdate: Date, displayName: String, firstName: String,
                lastName: String, status: String) {
        this.username = username
        this.password = password
        this.enabled = enabled
        this.birthdate = birthdate
        this.displayName = displayName
        this.firstName = firstName
        this.lastName = lastName
        this.status = status
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = ArrayList()

    override fun isEnabled(): Boolean = enabled

    override fun getUsername(): String = username

    override fun isCredentialsNonExpired(): Boolean = true

    override fun getPassword(): String = password

    fun getBirthdate(): Date = birthdate

    fun getDisplayname(): String = displayName

    fun getFirstName(): String = firstName

    fun getLastName(): String = lastName

    fun getStatus(): String = status

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true


    override fun equals(other: Any?): Boolean {
        return username == (other as User).username
    }
}