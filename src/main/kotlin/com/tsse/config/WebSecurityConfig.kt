package com.tsse.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.AuthenticationEntryPoint

/**
 * Created by boydhogerheijde on 16/05/2017.
 */
@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var authEntryPoint: AuthenticationEntryPoint

    override fun configure(http: HttpSecurity?) {
        http?.let {
            http.csrf().disable()
            http.authorizeRequests()
                    .anyRequest().authenticated()
                    .and().httpBasic()
                    .authenticationEntryPoint(authEntryPoint)
        }
    }

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication().withUser("tsse").password("sport").roles("USER")
    }
}