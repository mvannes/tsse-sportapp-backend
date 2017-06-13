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
        val user: User = repository.findByName(username) ?: throw UsernameNotFoundException("User not found for name \'$username\'")

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

        val grantedAuthorities = AuthorityUtils.createAuthorityList(*authorityStrings)

        return SpringUser(user.name, user.password, grantedAuthorities)
    }
}
