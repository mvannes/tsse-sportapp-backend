package com.tsse.service

/**
 * Created by boydhogerheijde on 31/05/2017.
 */
interface ResourceService<T> {

    fun create(resource: T): T

    fun create(resource: List<T>): List<T>

    fun findAll(): List<T>

    fun findOne(id: Long): T

    fun update(resource: T, id: Long)

    fun deleteAll()

    fun delete(id: Long)
}
