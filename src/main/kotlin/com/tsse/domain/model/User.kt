package com.tsse.domain.model

import org.hibernate.validator.constraints.NotBlank
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*

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
    private lateinit var username: String

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password cannot be empty!")
    private lateinit var password: String

    @Column(name = "enabled", nullable = false)
    var enabled: Boolean = false

    constructor()

    constructor(username: String, password: String, enabled: Boolean) {
        this.username = username
        this.password = password
        this.enabled = enabled
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = ArrayList()

    override fun isEnabled(): Boolean = enabled

    override fun getUsername(): String = username

    override fun isCredentialsNonExpired(): Boolean = true

    override fun getPassword(): String = password

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun equals(other: Any?): Boolean {
        return username == (other as User).username
    }
}