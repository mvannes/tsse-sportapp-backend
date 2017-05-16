package com.tsse.service

import com.tsse.domain.UserAlreadyExistsException
import com.tsse.domain.UserNotFoundException
import com.tsse.domain.model.User
import com.tsse.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

/**
 *
 *
 * @author Fabian de Almeida Ramos
 * @version 1.0.0
 */
@Service
class UserServiceImpl(val repository: UserRepository, val encoder: PasswordEncoder) : UserService {

    override fun saveUser(user: User): User {
        if (repository.findByUsername(user.username) != null) {
            throw UserAlreadyExistsException(user)
        }

        user.password = encoder.encode(user.password)

        return repository.save(user)
    }

    override fun getUser(id: Long) = repository.getOne(id) ?: throw UserNotFoundException(id)

    override fun getAllUsers() = repository.findAll()

    override fun updateUser(user: User): User {
        getUser(user.id)

        user.password = encoder.encode(user.password)
        return repository.save(user)
    }

    override fun deleteUser(id: Long) = repository.delete(id)
}