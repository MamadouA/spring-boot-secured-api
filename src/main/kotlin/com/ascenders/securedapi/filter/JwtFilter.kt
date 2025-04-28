package com.ascenders.securedapi.filter

import com.ascenders.securedapi.util.JwtUtils
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter: OncePerRequestFilter(){

    @Autowired
    @Lazy
    lateinit var userDetailsService: UserDetailsService

    @Autowired
    lateinit var jwtUtils: JwtUtils

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse,
        filterChain: FilterChain) {
        lateinit var token: String;
        lateinit var userDetails: UserDetails
        val authorization = request.getHeader("Authorization")

        if(authorization != null && authorization.startsWith("Bearer")) {
            token = authorization.substring(7)

            val username = jwtUtils.extractUsername(token)

            if (username != null && SecurityContextHolder.getContext().authentication == null) {
                userDetails = userDetailsService.loadUserByUsername(username)


                if(jwtUtils.isValidToken(token, userDetails)) {
                    val authentication =
                        UsernamePasswordAuthenticationToken(userDetails.username,
                            userDetails.password, userDetails.authorities)

                    authentication.details = WebAuthenticationDetailsSource().buildDetails(request)

                    SecurityContextHolder.getContext().authentication = authentication
                }
            }
        }

        filterChain.doFilter(request, response)
    }
}