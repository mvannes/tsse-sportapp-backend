package com.tsse.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder

/**
 * Created by boydhogerheijde on 16/05/2017.
 */
@Configuration
class AuthenticationConfig : GlobalAuthenticationConfigurerAdapter() {

    @Autowired
    lateinit var userDetailsService: UserDetailsService

    @Autowired
    lateinit var encoder: PasswordEncoder

    override fun init(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)
                ?.passwordEncoder(encoder)
    }
}