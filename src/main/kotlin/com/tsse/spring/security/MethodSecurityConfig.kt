package com.tsse.spring.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler

/**
 * TSSE Sport REST API
 *
 * @author Boyd Hogerheijde
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class MethodSecurityConfig : GlobalMethodSecurityConfiguration() {

    override fun createExpressionHandler(): MethodSecurityExpressionHandler = OAuth2MethodSecurityExpressionHandler()
}
