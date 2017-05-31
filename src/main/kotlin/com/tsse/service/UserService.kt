package com.tsse.service

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
class UserService(val repository: UserRepository, val encoder: PasswordEncoder) : AbstractService<User>() {

    override fun repository() = repository

    override fun create(resource: User): User {
        resource.password = encoder.encode(resource.password)

        return super.create(resource)
    }

    override fun update(resource: User, id: Long) {
        findOne(id)

        resource.password = encoder.encode(resource.password)

        super.update(resource, id)
    }
}