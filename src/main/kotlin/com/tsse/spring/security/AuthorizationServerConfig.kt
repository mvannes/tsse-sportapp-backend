package com.tsse.spring.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory

/**
 * TSSE Sport REST API
 *
 * The purpose of this configuration class is to configure the authorization server for OAuth2.
 *
 * @author Boyd Hogerheijde
 */
@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig(private val authenticationManager: AuthenticationManager,
                                private val userDetailsService: UserDetailsService) : AuthorizationServerConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        // @formatter:off
        // Configuration of OAuth2's endpoints to use our own beans as well as to accept GET and POST requests to get an access token from the server
        endpoints?.apply {
            tokenStore(tokenStore())
            .accessTokenConverter(accessTokenConverter())
            .authenticationManager(authenticationManager)
            .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
        }
        // @formatter:on
    }

    @Throws(Exception::class)
    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        // @formatter:off
        // Configuring two separate in-memory OAuth2 client applications.
        clients?.apply {
                inMemory()
                // Web client configuration
                .withClient("web")
                .secret("web-secret")
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("web", "read", "write", "trust")
                .autoApprove(true)
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(3600 * 24)

                .and()
                // Android client configuration
                .withClient("android")
                .secret("android-secret")
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("android", "read")
                .autoApprove(true)
                .accessTokenValiditySeconds(3600 * 24 * 30)
                .refreshTokenValiditySeconds(3600 * 24 * 60)
        }
        // @formatter:on
    }

    @Throws(Exception::class)
    override fun configure(security: AuthorizationServerSecurityConfigurer?) {
        // Check token endpoint is configured to be publicly available with the usage of SpEL.
        security?.checkTokenAccess("permitAll()")
    }

    @Bean
    fun accessTokenConverter(): JwtAccessTokenConverter {
        val tokenConverter = JwtAccessTokenConverter()

        // Creating a key store factory object which looks up the passed in resource, in this case our key store.
        // This is used to asynchronously sign our JWT tokens.
        val keyStoreKeyFactory = KeyStoreKeyFactory(ClassPathResource("jwt.jks"), "tssesport".toCharArray())
        tokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt"))

        return tokenConverter
    }

    @Bean
    fun tokenStore(): TokenStore {
        return JwtTokenStore(accessTokenConverter())
    }

    @Bean
    @Primary
    fun tokenServices(): DefaultTokenServices {
        val tokenServices = DefaultTokenServices()

        // Configuring token services to work with our JWT(JSON Web Token) token store and for it to work with refresh tokens.
        tokenServices.setTokenStore(tokenStore())
        tokenServices.setSupportRefreshToken(true)

        return tokenServices
    }
}
