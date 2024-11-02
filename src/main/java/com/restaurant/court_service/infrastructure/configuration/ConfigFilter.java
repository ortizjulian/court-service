package com.restaurant.court_service.infrastructure.configuration;


import com.restaurant.court_service.infrastructure.output.security.jwt.JwtAuthenticationFilter;
import com.restaurant.court_service.utils.SecurityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigFilter {
    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/restaurant/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/dish/**").permitAll()
                                .requestMatchers("/restaurant/**").hasAuthority(SecurityConstants.ROLE_ADMIN)
                                .requestMatchers("/dish/**").hasAuthority(SecurityConstants.ROLE_OWNER)
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}