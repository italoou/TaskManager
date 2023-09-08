package br.italolima.taskmanager.config;

import java.io.IOException;
import java.security.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.italolima.taskmanager.repositories.UserRepository;
import br.italolima.taskmanager.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterToken extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token;

        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null) {

            token = authorizationHeader.replace("Bearer ", "");

            var subject = this.tokenService.getSubject(token);

            var user = this.userRepository.findByUsername(subject);

            var authentication = new UsernamePasswordAuthenticationToken(user.get(),
                    null, user.get().getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        filterChain.doFilter(request, response);

    }

}

