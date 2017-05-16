package com.tsse.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
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
            http
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/api/users").permitAll()
                    .antMatchers("/api/users").hasAuthority("ADMIN")
                    .antMatchers(HttpMethod.DELETE).hasAuthority("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .httpBasic().authenticationEntryPoint(authEntryPoint)
                    .and()
                    .csrf().disable()
        }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}