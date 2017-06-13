package com.tsse.service

import com.tsse.domain.ResourceNotFoundException
import com.tsse.repository.ByNameApi
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional

/**
 * @author Boyd Hogerheijde
 */
@Transactional
abstract class AbstractResourceService<T> : ResourceService<T> {

    override fun create(resource: T): T {
        return repository().save(resource)
    }

    @Transactional(readOnly = true)
    override fun findAll(): List<T> {
        return repository().findAll()
    }

    @Transactional(readOnly = true)
    override fun findOne(id: Long): T {
        return repository().findOne(id) ?: throw ResourceNotFoundException(id)
    }

    override fun findByName(name: String): T? {
        return findByNameApi()?.findByName(name)
    }

    override fun update(resource: T, id: Long) {
        repository().save(resource)
    }

    override fun deleteAll() {
        repository().deleteAll()
    }

    override fun delete(id: Long) {
        repository().delete(id)
    }

    abstract fun repository(): JpaRepository<T, Long>

    abstract fun findByNameApi(): ByNameApi<T>?
}