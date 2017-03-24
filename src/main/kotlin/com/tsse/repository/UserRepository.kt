package com.tsse.repository

import com.tsse.domain.User
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

/**
 * Repository for users.
 *
 * @author Fabian de Almeida Ramos
 *
 * @version 1.0.0
 */
@RepositoryRestResource
interface UserRepository : CrudRepository<User, Long>