package com.tsse.service.impl

import com.tsse.domain.model.Role
import com.tsse.repository.RoleRepository
import com.tsse.service.AbstractResourceService
import org.springframework.stereotype.Service

/**
 * Created by boydhogerheijde on 30/05/2017.
 */
@Service
class RoleService(private val repository: RoleRepository) : AbstractResourceService<Role>() {

    override fun repository() = repository

    override fun findByNameApi() = repository
}
