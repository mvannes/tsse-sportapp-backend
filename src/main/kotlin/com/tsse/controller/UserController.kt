package com.tsse.controller

import com.tsse.domain.model.User
import com.tsse.service.impl.UserService
import org.springframework.http.HttpStatus
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import java.security.Principal
import javax.validation.Valid

/**
 * Controller used for REST operations related to users.
 *
 * @author Fabian de Almeida Ramos
 * @author Boyd Hogerheijde
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/users")
class UserController(private val service: UserService) {

    /**
     * Controller method for creating a new user.
     * Method delegates further logic to the service layer.
     *
     * @param user Passed in user to be stored in the database.
     *
     * @return The newly created user.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@Valid @RequestBody user: User, errors: Errors) = service.create(user)

    /**
     * Controller method for getting all users from the database.
     *
     * @return A list of users.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllUsers() = service.findAll()

    /**
     * Controller method for getting a particular user via its identifier.
     * Method delegates further logic(i.e. checking existence) to the service layer.
     *
     * @param id Identifier of a user.
     *
     * @return User object retrieved via its identifier.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getUser(@PathVariable id: Long) = service.findOne(id)

    /**
     * Controller method for getting information about the current active user by using the principal.
     *
     * @return Principal object.
     */
    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    fun me(principal: Principal) = principal

    /**
     * Controller method used to update an existing user object.
     * This method delegates further logic(i.e. existence check) to the service layer.
     *
     * @param user User to be updated. Contains new values for its attributes.
     *
     * @return The updated user.
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateUser(@Valid @RequestBody user: User, @PathVariable id: Long, errors: Errors) = service.update(user, id)

    /**
     * Deletes a particular user.
     *
     * @param id Identifier of the user to be deleted.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id: Long) = service.delete(id)
}
