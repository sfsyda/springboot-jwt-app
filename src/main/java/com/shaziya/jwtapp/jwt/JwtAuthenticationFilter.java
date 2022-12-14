package com.shaziya.jwtapp.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER = "Bearer ";
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    //Calls only once per request
    //This filter will be called by spring before controller is executed
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //get the jwt token
        //validate jwt token
        final String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        
        log.info("Bearer token = {}", bearerToken);
        
        if(bearerToken != null && bearerToken.startsWith(BEARER)) {
            //extract jwt token from bearer token
            final String jwtToken = bearerToken.substring(7);
            log.info("Jwt token = {}", jwtToken);
            final String username = jwtUtil.extractUsername(jwtToken);
            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            
            //Security checks
            if(userDetails == null) {
                throw new RuntimeException("Cannot find user for username "+username);
            }
            if(SecurityContextHolder.getContext().getAuthentication() == null) {
                //Write our logic to create authentication object
                UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userDetails, null, Collections.emptyList());
                upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(upat);
            }
        } else {
            log.info("Bearker token is null");
        }
        filterChain.doFilter(request, response);
    }
    
}
