package com.tsse.service

import com.tsse.domain.model.User
import com.tsse.repository.UserRepository
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.User as SpringUser

/**
 * Created by boydhogerheijde on 16/05/2017.
 */
@Service
class UserDetailsServiceImpl(private val repository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user: User = repository.findByUsername(username) ?: throw UsernameNotFoundException("User not found for username \'$username\'")

        return SpringUser(user.username, user.password, AuthorityUtils.createAuthorityList(user.role))
    }

}