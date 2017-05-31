package com.tsse.service

import com.tsse.domain.UserNotFoundException
import com.tsse.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

/**
 * Created by boydhogerheijde on 16/05/2017.
 */
@Service
class UserDetailsServiceImpl(private val repository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails
            = repository.findByUsername(username) ?: throw UserNotFoundException(username)
}