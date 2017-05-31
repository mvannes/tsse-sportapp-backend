package com.tsse.controller

import com.tsse.domain.model.User
import com.tsse.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * Controller used for REST operations related to users.
 *
 * @author Fabian de Almeida Ramos
 * @author Boyd Hogerheijde
 * @version 1.0.0
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
class UserController(val service: UserService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@Valid @RequestBody user: User, errors: Errors) = service.create(user)

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getUser(@PathVariable id: Long) = service.findOne(id)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllUsers() = service.findAll()

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateUser(@Valid @RequestBody user: User, @PathVariable id: Long, errors: Errors) = service.update(user, id)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id: Long) = service.delete(id)

}