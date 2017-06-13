package com.tsse.service.impl

import com.tsse.domain.model.User
import com.tsse.repository.UserRepository
import com.tsse.service.AbstractResourceService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

/**
 * @author Fabian de Almeida Ramos
 * @version 1.0.0
 */
@Service
class UserService(private val repository: UserRepository,
                  private val encoder: PasswordEncoder) : AbstractResourceService<User>() {

    override fun create(resource: User): User {
        resource.password = encoder.encode(resource.password)

        return super.create(resource)
    }

    override fun update(resource: User, id: Long) {
        findOne(id)

        resource.password = encoder.encode(resource.password)

        super.update(resource, id)
    }

    override fun repository() = repository

    override fun findByNameApi() = repository
}