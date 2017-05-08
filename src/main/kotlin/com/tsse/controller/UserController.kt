package com.tsse.controller

import com.tsse.domain.invalidFormException
import com.tsse.domain.model.User
import com.tsse.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 *
 *
 * @author Fabian de Almeida Ramos
 * @author Boyd Hogerheijde
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/users")
class UserController(val service: UserService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@Valid @RequestBody user: User, errors: Errors): User {
        validateRequest(errors)

        return service.saveUser(user)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getUser(@PathVariable id: Long) = service.getUser(id)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllUsers() = service.getAllUsers()

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateUser(@Valid @RequestBody user: User, errors: Errors): User {
        validateRequest(errors)

        return service.updateUser(user)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id: Long) = service.deleteUser(id)

    private fun validateRequest(errors: Errors) {
        if (errors.hasErrors()) {

            val errorMessages = errors.allErrors.map { it.defaultMessage }.toString()
            throw invalidFormException(errorMessages)

        }
    }

}