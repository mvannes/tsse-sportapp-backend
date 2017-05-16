package com.tsse.repository

import com.tsse.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Repository for users.
 *
 * @author Fabian de Almeida Ramos
 * @version 1.0.0
 */
@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByUsername(username: String): User?
}