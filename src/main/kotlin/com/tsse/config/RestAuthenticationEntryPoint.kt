package com.tsse.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by boydhogerheijde on 16/05/2017.
 */
@Component
class RestAuthenticationEntryPoint : BasicAuthenticationEntryPoint() {

    private val log: Logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint::class.java)

    override fun commence(request: HttpServletRequest?,
                          response: HttpServletResponse?,
                          authException: AuthenticationException?) {
        response?.apply {
            addHeader("WWW-Authenticate", "Basic realm=$realmName")
            status = HttpServletResponse.SC_UNAUTHORIZED
        }

        log.error(authException?.message)
    }

    override fun afterPropertiesSet() {
        realmName = "Dev"
        super.afterPropertiesSet()
    }
}