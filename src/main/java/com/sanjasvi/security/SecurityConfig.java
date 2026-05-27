package com.sanjasvi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http

            .csrf(csrf -> csrf.disable())

            .cors(cors -> {})

            .authorizeHttpRequests(auth -> auth

                // public routes
                .requestMatchers(
                        "/users/register",
                        "/users/login",
                        "/products/**"
                ).permitAll()

                // protected routes
                .requestMatchers("/cart/**", "/orders/**").hasAnyAuthority("USER", "ADMIN")

                .requestMatchers("/manage-orders/**", "/manage-products/**").hasAuthority("ADMIN")
                

                .anyRequest().authenticated()
            )

            .sessionManagement(session ->
                    session.sessionCreationPolicy(
                            SessionCreationPolicy.STATELESS
                    )
            )

            .formLogin(form -> form.disable())

            .httpBasic(basic -> basic.disable());

        // JWT filter
        http.addFilterBefore(
                new JwtFilter(),
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }
}