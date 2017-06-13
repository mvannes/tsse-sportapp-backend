package com.tsse.spring.security

import com.tsse.spring.filter.CorsFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

/**
 * TSSE Sport REST API
 * </p>
 * Class is used to configure web security and resource server properties.
 *
 * @author Boyd Hogerheijde
 */
@Configuration
@EnableResourceServer
@EnableWebSecurity
class ResourceServerConfig(private val userDetailsService: UserDetailsService,
                           private val corsFilter: CorsFilter) : ResourceServerConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity?) {
        // Configuration of Spring security, all requests need authentication.
        // To adhere to all REST constraints Spring is configured to not create sessions,
        // all principal info is contained within the JWT tokens.
        // @formatter:off
        http?.let {
            http
                .authorizeRequests()
                    .anyRequest()
                    .authenticated()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .csrf()
                    .disable()
                    .addFilterBefore(corsFilter, BasicAuthenticationFilter::class.java)
        }
        // @formatter:on
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setUserDetailsService(userDetailsService)
        authenticationProvider.setPasswordEncoder(passwordEncoder())
        return authenticationProvider
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Autowired
    fun configureGlobal(builder: AuthenticationManagerBuilder) {
        builder.authenticationProvider(authenticationProvider())
    }
}
