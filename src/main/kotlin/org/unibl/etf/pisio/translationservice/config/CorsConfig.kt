package org.unibl.etf.pisio.translationservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.cors.CorsConfiguration

@Configuration
class CorsConfig {

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain? {
        configureCors(http)
        configureCsrf(http)

        return http.build()
    }

    private fun configureCsrf(http: ServerHttpSecurity) = http.csrf {
        it.disable()
    }


    private fun configureCors(http: ServerHttpSecurity) = http.cors {
        it.configurationSource {
            val corsConfig = CorsConfiguration()
            corsConfig.allowedOrigins = listOf("http://localhost:4200")
            corsConfig.allowedMethods = listOf(
                HttpMethod.POST.name()
            )
            corsConfig.allowedHeaders = listOf("*")
            corsConfig
        }
    }


}
