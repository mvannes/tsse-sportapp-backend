package com.tsse.spring.filter

import org.springframework.stereotype.Component
import javax.servlet.*
import javax.servlet.http.HttpServletResponse

/**
 * @author Boyd Hogerheijde
 */
@Component
class CorsFilter : Filter {

    override fun doFilter(servletRequest: ServletRequest?, servletResponse: ServletResponse?, filterChain: FilterChain?) {
        val response = servletResponse as HttpServletResponse
        response.setHeader("Access-Control-Allow-Origin", "*")
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH")
        response.setHeader("Access-Control-Max-Age", "3600")
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept")
        response.setHeader("Access-Control-Expose-Headers", "Location")
        filterChain?.doFilter(servletRequest, response)
    }

    override fun init(config: FilterConfig?) {
    }

    override fun destroy() {
    }
}