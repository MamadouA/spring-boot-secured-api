package com.ascenders.secured_api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
@EnableWebSecurity
class SecurityContig {

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .csrf { it.disable() }
            .authorizeHttpRequests { it.anyRequest().authenticated() }
            .httpBasic(Customizer.withDefaults())
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)}
            .build()
    }

    @Bean
    fun userDetailsService(passwordEncoder: BCryptPasswordEncoder): UserDetailsService {

        val userDetails =
            User.withUsername("admin")
                .password(passwordEncoder.encode("passer"))
                .roles("User")
                .build()

        return InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        val providerManager = authenticationConfiguration.authenticationManager
        return providerManager;
    }
}