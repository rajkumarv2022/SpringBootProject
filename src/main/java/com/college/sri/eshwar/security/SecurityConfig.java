package com.college.sri.eshwar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
//    {
//
//        http.csrf().disable().authorizeHttpRequests((authorize) -> authorize
//                .requestMatchers("/user/getAll").authenticated()
//                .anyRequest().permitAll()
//        ).httpBasic();
//
//
//
////        http.csrf(csrf -> csrf.disable()) // Disable CSRF
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/user/getAll").authenticated() // Secure this endpoint
////                        .anyRequest().permitAll() // Allow all other requests
////                )
////                .httpBasic(Customizer.withDefaults()); // Enable Basic Authentication
//
//        return http.build();


    @Autowired
    JwtFilter jwtFilter; // Inject JwtFilter


    @Autowired
    JwtUtil jwtUtil;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/user/update", "/user/create").authenticated() // Require authentication for these endpoints
                        .anyRequest().permitAll() // Allow all other requests
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Ensure stateless session management
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Specify the order here

        return http.build(); // Build the security filter chain
    }

    }

