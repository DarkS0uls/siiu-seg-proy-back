package com.myorg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@SuppressWarnings("PMD")
public class OAuth2ResourceServerSecurityConfig {


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        Customizer<CsrfConfigurer<HttpSecurity>> csrfCustomizer = csrf -> csrf.disable();


        http
                .csrf(csrfCustomizer)
                .authorizeRequests(
                        authorizeRequests -> authorizeRequests
                                .requestMatchers("/**").permitAll()
                ).
                exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .authenticationEntryPoint(new CustomAutehenticationEntryPointHandler())
                                .accessDeniedHandler(new CustomAccessDeniedHandler())


                );
        return http.build();
    }


}
