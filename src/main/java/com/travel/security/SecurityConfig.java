package com.travel.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.http.HttpMethod;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)

                // Bật CORS để frontend localhost:3000 / LAN gọi được backend
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // JWT dùng stateless session, backend không lưu session đăng nhập
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth

                        // Cho phép request preflight OPTIONS của browser
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Auth public
                        .requestMatchers("/api/auth/**").permitAll()

                        // Header / layout public
                        .requestMatchers("/api/layout/**").permitAll()

                        // Setup admin đã khóa lại
                        .requestMatchers("/api/setup/**").denyAll()

                        // Public APIs cho khách
                        .requestMatchers("/api/countries/**").permitAll()
                        .requestMatchers("/api/destinations/**").permitAll()
                        .requestMatchers("/api/tour-styles/**").permitAll()
                        .requestMatchers("/api/tour-collections/**").permitAll()
                        .requestMatchers("/api/tours/**").permitAll()
                        .requestMatchers("/api/banners/**").permitAll()
                        .requestMatchers("/api/testimonials/**").permitAll()
                        .requestMatchers("/api/blogs/**").permitAll()
                        .requestMatchers("/api/pages/**").permitAll()
                        .requestMatchers("/api/contact-messages/**").permitAll()

                        // Upload ảnh: tạm thời cho public để test
                        .requestMatchers("/api/upload/**").permitAll()

                        // Admin APIs cần đăng nhập quyền ADMIN
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")

                        // Các request khác tạm thời cho qua
                        .anyRequest().permitAll()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Origin là địa chỉ frontend, không phải backend
        configuration.setAllowedOriginPatterns(List.of("*"));


        configuration.setAllowedMethods(List.of(
                "GET",
                "POST",
                "PUT",
                "PATCH",
                "DELETE",
                "OPTIONS"
        ));

        configuration.setAllowedHeaders(List.of(
                "Authorization",
                "Content-Type",
                "Accept",
                "Origin",
                "X-Requested-With"
        ));

        configuration.setExposedHeaders(List.of(
                "Authorization"
        ));

        // Nếu bạn không dùng cookie/session thì false là ổn
        configuration.setAllowCredentials(false);

        // Cache preflight request 1 giờ
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}