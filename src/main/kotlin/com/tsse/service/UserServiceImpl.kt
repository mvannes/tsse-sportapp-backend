package com.tsse.service

import com.tsse.domain.UserNotFoundException
import com.tsse.domain.model.User
import com.tsse.repository.UserRepository
import org.springframework.stereotype.Service

/**
 *
 *
 * @author Fabian de Almeida Ramos
 * @version 1.0.0
 */
@Service
class UserServiceImpl(val repository: UserRepository) : UserService {

    override fun saveUser(user: User) = repository.save(user)

    override fun getUser(id: Long) = repository.getOne(id) ?: throw UserNotFoundException(id)

    override fun getAllUsers() = repository.findAll()

    override fun updateUser(user: User): User {
        getUser(user.id)
        return repository.save(user)
    }

    override fun deleteUser(id: Long) = repository.delete(id)
}