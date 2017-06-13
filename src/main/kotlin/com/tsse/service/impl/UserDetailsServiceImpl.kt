package com.tsse.service.impl

import com.tsse.domain.model.Authority
import com.tsse.domain.model.Role
import com.tsse.domain.model.User
import com.tsse.repository.UserRepository
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors
import org.springframework.security.core.userdetails.User as SpringUser

/**
 * @author Boyd Hogerheijde
 */
@Service
class UserDetailsServiceImpl(private val repository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        // Getting user by his/her username, if one is not found an exception will be thrown to notify spring security.
        val user: User = repository.findByName(username) ?: throw UsernameNotFoundException("User not found for name \'$username\'")

        // Retrieving a user his/her roles to eventually get its authorities.
        val roles: Set<Role> = user.roles ?: HashSet<Role>()
        val authorities = HashSet<Authority>()

        for (role in roles) {
            authorities.addAll(role.authorities!!.toList())
        }

        val authorityStrings = authorities
                .stream()
                .map { it.toString() }
                .collect(Collectors.toList<String>())
                .toTypedArray()

        // Creating a list of authorities based on the authorities a role of a user has.
        val grantedAuthorities = AuthorityUtils.createAuthorityList(*authorityStrings)

        return SpringUser(user.name, user.password, grantedAuthorities)
    }
}
