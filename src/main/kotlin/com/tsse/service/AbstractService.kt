package com.tsse.service

import com.tsse.domain.ResourceNotFoundException
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by boydhogerheijde on 31/05/2017.
 */
abstract class AbstractService<T> : ResourceService<T> {

    override fun create(resource: T): T {
        return repository().save(resource)
    }

    override fun create(resource: List<T>): List<T> {
        return repository().save(resource)
    }

    override fun findAll(): List<T> {
        return repository().findAll()
    }

    override fun findOne(id: Long): T {
        return repository().findOne(id) ?: throw ResourceNotFoundException(id)
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
}