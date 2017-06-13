package com.tsse.repository

/**
 * Created by boydhogerheijde on 30/05/2017.
 */
interface ByNameApi<T> {

    fun findByName(name: String): T?
}
