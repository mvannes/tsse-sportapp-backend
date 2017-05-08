package com.tsse.controller

import com.tsse.domain.model.User
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

/**
 *
 *
 * @author Fabian de Almeida Ramos
 * @author Boyd Hogerheijde
 * @version 1.0.0
 */
@RestController
@RequestMapping("/users")
class UserController {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun createUser(user: User) {

    }

//    fun getUser(id: Long): User {
//
//    }
//
//    fun getAllUsers(): List<User> {
//
//    }
//
//    fun updateUser(user: User) {
//
//    }
//
//    fun deleteUser(id: Long) {
//
//    }

}