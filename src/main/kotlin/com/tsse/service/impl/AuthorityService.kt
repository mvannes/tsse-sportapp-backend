package com.tsse.service.impl

import com.tsse.domain.model.Authority
import com.tsse.repository.AuthorityRepository
import com.tsse.repository.ByNameApi
import com.tsse.service.AbstractResourceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by boydhogerheijde on 30/05/2017.
 */
@Service
class AuthorityService(private val repository: AuthorityRepository) : AbstractResourceService<Authority>() {

    override fun repository() = repository

    override fun findByNameApi() = repository
}
