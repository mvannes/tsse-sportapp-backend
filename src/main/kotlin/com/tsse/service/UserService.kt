package com.tsse.service

import com.tsse.domain.model.User

/**
 *
 *
 * @author Fabian de Almeida Ramos
 * @version 1.0.0
 */
interface UserService {

    fun saveUser(user: User): User

    fun getUser(id: Long): User

    fun getAllUsers(): List<User>

    fun updateUser(user: User): User

    fun deleteUser(id: Long)

}